package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.gdata.PercentEscaper;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 * Base class for the ChargeBee model objects
 *
 * @param <T> The concrete type of the model
 */
public class Resource<T> {

    private static final Logger logger = Logger.getLogger(Resource.class.getName());

    public JSONObject jsonObj;
    
    private static final String unknown = "_UNKNOWN";

    public Resource(JSONObject jsonObj){
        this.jsonObj = jsonObj;
    }

    public Resource(String jsonStr){
        try {
            jsonObj = new JSONObject(jsonStr);
        } catch(JSONException jexp) {
            throw new RuntimeException(jexp);
        }
    }

    public Resource(InputStream is) throws IOException {
        this(new BufferedReader(new InputStreamReader(is)));
    }

    public Resource(BufferedReader rd) throws IOException {
        this(getAsString(rd));
    }

    private static String getAsString(BufferedReader rd) throws IOException {
        StringBuilder buf = new StringBuilder();
        String line = null;
        while((line = rd.readLine()) != null) {
            buf.append(line);
        }
        return buf.toString();
    }

    public  String reqString(String key) {
        return assertReqProp(key, optString(key));
    }

    public String optString(String key) {
        return optional(key, String.class);
    }

    public Boolean reqBoolean(String key) {
        return assertReqProp(key, optBoolean(key));
    }

    public Boolean optBoolean(String key) {
        return optional(key, Boolean.class);
    }

    public Integer reqInteger(String key) {
        return assertReqProp(key, optInteger( key));
    }

    public Integer optInteger(String key) {
        Integer value = optional( key, Integer.class);
        try{
            return (value != null)? value : null;
        }catch(Exception ex){
            throw conversionException(key);
        }
    }
    
    public BigDecimal reqBigDecimal(String key) {
        return assertReqProp(key, optBigDecimal( key));
    }

    public BigDecimal optBigDecimal(String key) {
        BigDecimal value = optional( key, BigDecimal.class);
        try{
            return (value != null)? value : null;
        }catch(Exception ex){
            throw conversionException(key);
        }
    }

    public Double reqDouble(String key) {
        return assertReqProp(key, optDouble(key));
    }

    public Double optDouble(String key) {
        Double value = optional( key, Double.class);
        try{
            return (value != null)? value : null;
        }catch(Exception ex){
            throw conversionException(key);
        }
    }

    public Long reqLong(String key) {
        return assertReqProp(key, optLong( key));
    }

    public Long optLong(String key) {
        Object val = jsonObj.opt(key);
        if(val == null) {
            return null;
        }
        // special handling for Long. Accepting both Long and Integer values !!
        if(val instanceof Long) {
            return (Long)val;
        } else if(val instanceof Integer) {
            return ((Integer)val).longValue();
        } else {
            throw new RuntimeException("Wrong type. Expecing Long type but got "
                    + val.getClass().getSimpleName());
        }
    }

    public JSONObject reqJSONObject(String key){
        return assertReqProp(key, optJSONObject(key));
    }

    public JSONObject optJSONObject(String key){
        return optional(key,JSONObject.class);
    }
    
    public JSONArray reqJSONArray(String key){
        return assertReqProp(key, optJSONArray(key));
    }
    
    public JSONArray optJSONArray(String key){
        return optional(key,JSONArray.class);
    }

    public Timestamp reqTimestamp(String key) {
        return assertReqProp(key, optTimestamp( key));
    }

    public Timestamp optTimestamp(String key) {
        Long unxTime = optLong(key);
        return (unxTime != null)? new Timestamp(unxTime * 1000l) : null;
    }

    public <E extends Enum> E reqEnum(String key, Class<E> enumClass) {
        return assertReqProp(key, optEnum(key, enumClass));
    }

    public <E extends Enum> E optEnum(String key, Class<E> enumClass) {
        String value = optString(key);
        if(value == null) {
            return null;
        }
        try {
           return (E) Enum.valueOf(enumClass, value.toUpperCase());
        }catch(Exception ex){
           logger.log(Level.SEVERE, " The property {0} has unexpected value {1}", new Object[]{key, value});
           return (E) Enum.valueOf(enumClass, unknown);
        }
    }

    /**
     * @param <S> The sub-resource type
     */
    public <S extends Resource> List<S> reqList(String key,Class<S> claz) {
        List<S> list = optList(key, claz);
        if(list.isEmpty()) {
            throw new RuntimeException("The sub-resource [" + key + "] is not present");
        }
        return list;
    }

    /**
     * @param <S> The sub-resource type
     */
    public <S> List<S> optList(String key, Class<S> claz){
        JSONArray arr = jsonObj.optJSONArray(key);
        if(arr == null){
            return Collections.EMPTY_LIST;
        }

        List<S> toRet = new ArrayList<S>(arr.length());
        for (int i = 0; i < arr.length(); i++) {
            if(claz == String.class){
              toRet.add((S)arr.optString(i));
            }else if(Number.class.isAssignableFrom(claz)){
              String s = arr.optString(i);
              if(s == null){
                  toRet.add(null);
              }else{
                  toRet.add(ClazzUtil.createNumberInstance(claz,s));
              }
            }else{
              JSONObject json = arr.optJSONObject(i);
              toRet.add(ClazzUtil.createInstance(claz, json));
            }
        }
        return toRet;
    }

    public <S extends Resource> S reqSubResource(String key, Class<S> claz) {
        return assertReqProp(key, optSubResource(key, claz));
    }

    public <S extends Resource> S optSubResource(String key, Class<S> claz){
        JSONObject resAsJson = jsonObj.optJSONObject(key);
        if(resAsJson == null){
            return null;
        }

        return ClazzUtil.createInstance(claz, resAsJson);
    }


    private <T> T optional(String key, Class<T> type) {
        Object val = jsonObj.opt(key);
        if(val == null) {
            return null;
        }
        if(!type.isAssignableFrom(val.getClass())){
            //JSON returns Integer values if the string format is without decimal points
            // Like 10  instead of 10.0 
            if(BigDecimal.class == type && val instanceof Number){
                return (T) new BigDecimal(String.valueOf(val));
            }
            if(Double.class == type && val instanceof Number){
                return (T) new Double(((Number)val).doubleValue());
            }
            if(Float.class == type && val instanceof Number){
                return (T) new Float(((Number)val).floatValue());
            }
            throw new RuntimeException("Type mismatch for property " + key
                    + " . Expected " + type.getName() + " but contains " + val.getClass().getName());
        }
        return (T)val;
    }

    public String toJson() {
        return jsonObj.toString();
    }

    @Override
    public String toString() {
        try {
            return jsonObj.toString(2);
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    // util methods
    //=============

    private <T> T assertReqProp(String key,T val) {
        if(val == null){
            throw new RuntimeException("The property [" + key + "] is not present ");
        }
        return val;
    }

    private RuntimeException conversionException(String key){
        return new RuntimeException("The property " + key + " not in the required format");
    }

    protected static String nullCheck(String id) {
        if(id == null || id.isEmpty()) {
            throw new RuntimeException("id cannot be null/empty");
        }
        return id;
    }

    protected static String uri(String ... paths){
        StringBuilder strBuf = new StringBuilder();
        for (String path : paths) {
            try {//Using URLEncoder is wrong as it encodes for form. Replace it with Google's CharEscapers.java
                strBuf.append('/').append(new PercentEscaper(PercentEscaper.SAFEPATHCHARS_URLENCODER, false) .escape(path));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return strBuf.toString();
    }   
    
    protected static void apiVersionCheck(JSONObject jsonObj) {
        if (!jsonObj.has("api_version")) {
            return;
        }
        String apiVersion = jsonObj.optString("api_version");
        if (apiVersion != null && !jsonObj.optString("api_version").equalsIgnoreCase(Environment.API_VERSION)) {
            throw new RuntimeException("API version [" + apiVersion.toUpperCase() + "] in response does not match "
                    + "with client library API version [" + Environment.API_VERSION.toUpperCase() + "]");
        }
    }

}
