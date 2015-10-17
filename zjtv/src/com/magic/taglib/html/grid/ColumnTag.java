package com.magic.taglib.html.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-21
 * Time: 上午3:51
 * To change this template use File | Settings | File Templates.
 */
public class ColumnTag extends BodyTagSupport {
    private Column column;

    public ColumnTag(){
        super();
        if (column == null) {
            column = new Column();
        }
    }

    @Override
    public int doEndTag() throws JspException {
        DataGridTag dataGridTag = (DataGridTag) getParent();
        dataGridTag.addColumn(column);
        column = new Column();
        return super.doEndTag();
    }

    public String getCellType() {
        return column.getCellType();
    }

    public void setCellType(String cellType) {
        column.setCellType(cellType);
    }

    public String getWidth() {
        return column.getWidth();
    }

    public void setWidth(String width) {
        column.setWidth(width);
    }

    public String getStyleClass() {
        return column.getStyleClass();
    }

    public void setStyleClass(String styleClass) {
        column.setStyleClass(styleClass);
    }

    public String getCreatedCellFunc() {
        return column.getCreatedCellFunc();
    }

    public void setCreatedCellFunc(String createdCellFunc) {
        column.setCreatedCellFunc(createdCellFunc);
    }

    public String getData() {
        return column.getData();
    }

    public void setData(String data) {
        column.setData(data);
    }

    public String getDataScript() {
        return column.getDataScript();
    }

    public void setDataScript(String dataScript) {
        column.setDataScript(dataScript);
    }

    public String getRender() {
        return column.getRender();
    }

    public void setRender(String render) {
        column.setRender(render);
    }

    public String getRenderScript() {
        return column.getRenderScript();
    }

    public void setRenderScript(String renderScript) {
        column.setRenderScript(renderScript);
    }

    public String getDefaultContent() {
        return column.getDefaultContent();
    }

    public void setDefaultContent(String defaultContent) {
        column.setDefaultContent(defaultContent);
    }

    public String getTitle() {
        return column.getTitle();
    }

    public void setTitle(String title) {
        column.setTitle(title);
    }

    public Boolean getSortable() {
        return column.getSortable();
    }

    public void setSortable(Boolean sortable) {
        column.setSortable(sortable);
    }

    public Boolean getVisible() {
        return column.getVisible();
    }

    public void setVisible(Boolean visible) {
        column.setVisible(visible);
    }
}
