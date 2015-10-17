package com.magic.taglib.tiles;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Author: Yin Jian Feng
 * Date: 13-3-31
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
public class LayoutTag extends TagSupport {
    private String name = "jfeng.layout.main";

    @Override
    public int doStartTag() throws JspException {
        ServletRequest req = pageContext.getRequest();
        if(StringUtils.isNotEmpty(getName()))
            req.setAttribute("tiles_layout_name", getName());
        return EVAL_PAGE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
