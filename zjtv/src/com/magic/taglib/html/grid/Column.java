package com.magic.taglib.html.grid;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-21
 * Time: 下午5:44
 * To change this template use File | Settings | File Templates.
 */
public class Column {
    private String cellType;        //set column as td or th
    private String width;           //width of column
    private String styleClass;          //cell class style
    /**
     * function that is called whenever a cell is created or processed for input.
     * function (nTd, sData, oData, iRow, iCol) {
     * }
     */
    private String createdCellFunc;
    private String data;
    /**
     * script when render data of column.
     *
     * function ( source, type, val ) {
     *      {array|object} The data source for the row
     *      {string} The type call data requested - this will be 'set' when setting data or 'filter', 'display', 'type', 'sort' or undefined when gathering data. Note that when undefined is given for the type DataTables expects to get the raw data for the object back
     *      {*} Data to set when the second parameter is 'set'..
     *      return ...;
     * }
     */
    private String dataScript;

    private String render;
    /*
     * cell render script, you can define cell as a button input
     * function ( data, type, full ) {
     *      {array|object} The data source for the row (based on mData)
     *      {string} The type call data requested - this will be 'filter', 'display', 'type' or 'sort'.
     *      {array|object} The full data source for the row (not based on mData)
     *      return some_string;
     * }
     */
    private String renderScript;
    private String defaultContent; //display when data is null
    private String title;           //Column head title
    private Boolean sortable;
    private Boolean visible;

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getCreatedCellFunc() {
        return createdCellFunc;
    }

    public void setCreatedCellFunc(String createdCellFunc) {
        this.createdCellFunc = createdCellFunc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataScript() {
        return dataScript;
    }

    public void setDataScript(String dataScript) {
        this.dataScript = dataScript;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getRenderScript() {
        return renderScript;
    }

    public void setRenderScript(String renderScript) {
        this.renderScript = renderScript;
    }

    public String getDefaultContent() {
        return defaultContent;
    }

    public void setDefaultContent(String defaultContent) {
        this.defaultContent = defaultContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
