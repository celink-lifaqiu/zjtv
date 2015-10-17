package com.magic.taglib.html.button;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-9
 * Time: 上午2:08
 * To change this template use File | Settings | File Templates.
 */
public class PrimaryButtonTag extends ButtonTag {
    @Override
    protected TYPE getButtonType() {
        return TYPE.PRIMARY;
    }
}
