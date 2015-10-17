package com.magic.core.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-14
 * Time: 下午10:07
 * To change this template use File | Settings | File Templates.
 */
public class MenuException extends Exception {
    public MenuException(String message){
        super("Invalid Menu Configuration:"+message);
    }
}
