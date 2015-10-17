package com.magic.taglib.html.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-21
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 * ==============================================================
 * Cell render script, you can define cell as a button input
 * function ( data, type, full ) {
 *      {array|object} The data source for the row (based on mData)
 *      {string} The type call data requested - this will be 'filter', 'display', 'type' or 'sort'.
 *      {array|object} The full data source for the row (not based on mData)
 *      return some_string;
 * }
 */
public class ColumnRenderScriptTag extends BodyTagSupport {

    @Override
    public int doAfterBody() throws JspException {
        ColumnTag columnTag = (ColumnTag) getParent();
        columnTag.setRenderScript(getBodyContent().getString());
        return super.doAfterBody();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
