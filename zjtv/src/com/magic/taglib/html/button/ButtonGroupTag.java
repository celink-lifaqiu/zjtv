package com.magic.taglib.html.button;

import com.magic.commons.utils.TagUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-6-12
 * Time: 下午2:14
 * To change this template use File | Settings | File Templates.
 */
public class ButtonGroupTag extends BodyTagSupport {
    @Override
    public int doAfterBody() throws JspException {
        StringBuffer stringBuffer = new StringBuffer("<div class=\"btn-group\">\n");
        stringBuffer.append(getBodyContent().getString());
        stringBuffer.append("</div>");
        try {
            TagUtils.write(getBodyContent().getEnclosingWriter(), stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return super.doAfterBody();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
