package com.magic.taglib.html.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-21
 * Time: 下午4:47
 * To change this template use File | Settings | File Templates.
 * ============================================================
 * function ( source, type, val ) {
 *      {array|object} The data source for the row
 *      {string} The type call data requested - this will be 'set' when setting data or 'filter', 'display', 'type', 'sort' or undefined when gathering data. Note that when undefined is given for the type DataTables expects to get the raw data for the object back
 *      {*} Data to set when the second parameter is 'set'..
 *      return ...;
 * }
 */
public class DataScriptTag extends BodyTagSupport {

    @Override
    public int doAfterBody() throws JspException {
        ColumnTag columnTag = (ColumnTag) getParent();
        columnTag.setDataScript(getBodyContent().getString());
        return super.doAfterBody();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
