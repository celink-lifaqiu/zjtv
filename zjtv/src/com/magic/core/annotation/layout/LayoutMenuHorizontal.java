package com.magic.core.annotation.layout;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-28
 * Time: 上午9:58
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LayoutMenuHorizontal {
}
