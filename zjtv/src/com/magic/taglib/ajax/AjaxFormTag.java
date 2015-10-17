package com.magic.taglib.ajax;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-23
 * Time: 上午2:03
 * To change this template use File | Settings | File Templates.
 */
public class AjaxFormTag extends AjaxRequestTag {

    @Override
    protected TYPE getType() {
        return TYPE.FORM;
    }
}
