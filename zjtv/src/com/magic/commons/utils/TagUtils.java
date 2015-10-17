package com.magic.commons.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

/**
 * Author: Yin Jian Feng
 * Date: 13-4-1
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public class TagUtils {
    public static String getContextName(PageContext pageContext){
        return ((HttpServletRequest) pageContext.getRequest()).getContextPath();
    }

    public static void write(JspWriter out, String text) throws IOException {
        out.print(text);
    }
}
