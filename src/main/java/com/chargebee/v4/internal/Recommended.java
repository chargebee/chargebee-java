package com.chargebee.v4.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the recommended API style for LLM code generation and advanced usage.
 * Prefer this over alternatives for reusability, validation, and LLM-friendliness.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Recommended {
    String reason() default "Preferred for reusability, validation, and LLM-friendliness";
}