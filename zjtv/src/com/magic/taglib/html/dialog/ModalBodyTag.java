package com.magic.taglib.html.dialog;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-9
 * Time: 下午10:52
 * To change this template use File | Settings | File Templates.
 */
public class ModalBodyTag extends BodyTagSupport {
    private String action;
    @Override
    public int doAfterBody() throws JspException {
        ModalDialogTag parent = (ModalDialogTag) getParent();
        String body = getBodyContent().getString();
        parent.getDialog().setBody(body);
        return super.doAfterBody();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
