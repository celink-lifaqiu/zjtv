package com.magic.core.web;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Yin Jian Feng
 * Date: 13-3-30
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
public class JspHandler {
    public static String getJspContent(ServletRequest request, ServletResponse response, String path) throws ServletException, IOException {
        JFengResponseWapper responseWapper = new JFengResponseWapper((HttpServletResponse)response);
        request.getRequestDispatcher(path).include(request, responseWapper);
        return responseWapper.getContent();
    }
}
