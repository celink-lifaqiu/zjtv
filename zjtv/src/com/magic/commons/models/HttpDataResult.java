package com.magic.commons.models;

/**
 * Created by Yin Jian Feng on 14-3-22.
 */
public class HttpDataResult extends BaseHttpResult {
    Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
