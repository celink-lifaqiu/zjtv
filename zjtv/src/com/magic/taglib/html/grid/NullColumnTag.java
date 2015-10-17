package com.magic.taglib.html.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-22
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public class NullColumnTag extends TagSupport {
    @Override
    public int doEndTag() throws JspException {
        DataGridTag dataGridTag = (DataGridTag) getParent();
        dataGridTag.addColumn(null);
        return EVAL_PAGE;
    }
}
