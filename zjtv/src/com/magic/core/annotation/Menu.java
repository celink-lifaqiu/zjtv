package com.magic.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-7
 * Time: 上午10:22
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Menu {
    String id() default "";
    String label() default "";
    String icon() default "";
    int serialNumber() default 0;
    boolean visible() default true;
    public static final String ICON_EDIT = "icon-edit";
    public static final String ICON_STAR = "icon-star";
    public static final String ICON_CERTIFICATE = "icon-certificate";
    public static final String ICON_STAR_EMPTY = "icon-star-empty";
    public static final String ICON_LIST = "icon-list";
    public static final String ICON_LIST_ALT = "icon-list-alt";
    public static final String ICON_PRINT = "icon-print";
    public static final String ICON_TAGS = "icon-tags";
    public static final String ICON_TH_LARGE = "icon-th-large";
    public static final String ICON_USER = "icon-user";
    public static final String ICON_CREDIT_CARD = "icon-credit-card";
    public static final String ICON_REORDER = "icon-reorder";
}
