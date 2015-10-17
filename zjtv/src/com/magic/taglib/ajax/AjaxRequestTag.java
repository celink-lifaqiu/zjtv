package com.magic.taglib.ajax;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-23
 * Time: 上午2:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class AjaxRequestTag extends TagSupport {
    static enum TYPE{FORM, REFRESH, SIMPLE};
    private String action;
    private String formId;
    private String onComplete;
    private String onSuccess;
    private String onFailure;
    private Boolean async = true;
    private Map<String, Object> props = new HashMap<String, Object>();

    @Override
    public int doStartTag() throws JspException {
        addProp("action", getAction());
        addProp("onComplete", getOnComplete());
        addProp("onSuccess", getOnSuccess());
        addProp("onFailure", getOnFailure());
        addProp("async", getAsync());
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        execute();
        return super.doEndTag();
    }

    private void execute(){
        switch (getType()){
            case FORM:

                break;
            case REFRESH:
                break;
            case SIMPLE:
                break;
        }
    }

    protected abstract TYPE getType();

    protected void addProp(String key, Object value){
        props.put(key, value);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOnComplete() {
        return onComplete;
    }

    public void setOnComplete(String onComplete) {
        this.onComplete = onComplete;
    }

    public String getOnSuccess() {
        return onSuccess;
    }

    public void setOnSuccess(String onSuccess) {
        this.onSuccess = onSuccess;
    }

    public String getOnFailure() {
        return onFailure;
    }

    public void setOnFailure(String onFailure) {
        this.onFailure = onFailure;
    }

    public Boolean getAsync() {
        return async;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }
}
