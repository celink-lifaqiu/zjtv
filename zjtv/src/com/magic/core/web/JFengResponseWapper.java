package com.magic.core.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Author: Yin Jian Feng
 * Date: 13-3-30
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
public class JFengResponseWapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream output;
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @throws IllegalArgumentException
     *          if the response is null
     */
    public JFengResponseWapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        output.close();
    }

    public String getContent(){
        try {
            output.flush();
            String str = output.toString("UTF-8");
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "UnsupportedEncoding";
    }
}
