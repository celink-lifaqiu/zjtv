package com.magic.taglib;

import com.magic.commons.utils.TagUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Author: Yin Jian Feng
 * Date: 13-4-1
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
public class AppContextTag extends TagSupport {
	private String var;
    public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	@Override
    public int doStartTag() throws JspException {
        String contextPath = TagUtils.getContextName(this.pageContext);
        try {
        	if(StringUtils.isEmpty(var))
        		TagUtils.write(this.pageContext.getOut(), contextPath);
        	else
        		pageContext.setAttribute(var, contextPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
}
