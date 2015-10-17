package com.magic.commons.models;

/**
 * Created by Yin Jian Feng on 14-3-22.
 */
public abstract class BaseHttpResult {
    private boolean state = true;
    private int errcode;
    private String error;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
