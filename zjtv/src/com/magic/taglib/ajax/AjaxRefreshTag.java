package com.magic.taglib.ajax;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-23
 * Time: 上午2:04
 * To change this template use File | Settings | File Templates.
 */
public class AjaxRefreshTag extends AjaxRequestTag {
    @Override
    protected TYPE getType() {
        return TYPE.REFRESH;
    }
}
