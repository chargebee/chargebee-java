/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chargebee;

import com.chargebee.models.*;

/**
 *
 * @author rr
 */
public class Snippets {

    public static void main(String[] args) throws Exception {
        Environment.configure("fd_pre_production-test", "nXCFiuuDju8YnhL7G1BjJrF61cdY3xe2S");
        Result result = Subscription.retrieve("26618").request();
        Subscription subscription = result.subscription();
        Customer customer = result.customer();
        Card card = result.card();
        System.out.println(subscription);
    }
}
