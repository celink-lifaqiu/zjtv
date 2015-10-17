package com.magic.taglib.html.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-21
 * Time: 下午5:35
 * To change this template use File | Settings | File Templates.
 */
public class CreatedCellFuncTag extends BodyTagSupport {

    @Override
    public int doAfterBody() throws JspException {
        ColumnTag columnTag = (ColumnTag) getParent();
        columnTag.setCreatedCellFunc(getBodyContent().getString());
        return super.doAfterBody();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
