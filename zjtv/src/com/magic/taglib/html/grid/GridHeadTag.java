package com.magic.taglib.html.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-21
 * Time: 上午4:00
 * To change this template use File | Settings | File Templates.
 */
public class GridHeadTag extends BodyTagSupport {

    @Override
    public int doAfterBody() throws JspException {
        DataGridTag dataGridTag = (DataGridTag) getParent();
        dataGridTag.setGridHead(getBodyContent().getString());
        return super.doAfterBody();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
