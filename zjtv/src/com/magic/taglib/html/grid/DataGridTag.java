package com.magic.taglib.html.grid;

import com.magic.commons.utils.TagUtils;
import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-21
 * Time: 上午1:40
 * To change this template use File | Settings | File Templates.
 */
public class DataGridTag extends BodyTagSupport {
    private String action;
    private Boolean serverSide = false;
    private Boolean scrollable = false;
    private String gridHead;
    private List<Column> columnList = new ArrayList<Column>();

    @Override
    public int doStartTag() throws JspException {
        columnList = new ArrayList<Column>();
        return super.doStartTag();
    }

    @Override
    public int doAfterBody() throws JspException {
        StringBuffer table = new StringBuffer("<table class=\"table table-striped table-bordered\" id=\""+getId()+"\">\n");
        if (!StringUtils.isEmpty(getGridHead())){
            table.append("<thead>\n");
            table.append(getGridHead()+"\n");
            table.append("</thead>");
        }
        table.append("<tbody></tbody>\n");
        table.append("</table>\n");

        try {
            TagUtils.write(getBodyContent().getEnclosingWriter(), table.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        String contextName = TagUtils.getContextName(this.pageContext);
        String str =    "<script type=\"text/javascript\">\n" +
                        "    $(document).ready(function(){\n" +
                        "dataTable_"+getId()+" = $('#"+getId()+"').dataTable({\n" +
                        "           \"oLanguage\": {\n" +
                        "           \"sUrl\" : \""+contextName+"/res/i18n/dataTables.chinese.txt\"\n" +
                        "           },\n" +
                        "           \"sDom\": \"<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>\",\n" +
                        "           \"sPaginationType\": \"bootstrap\",\n";
        if(scrollable){
            str +=      "           \"sScrollY\": 200,\n" +
                        "           \"sScrollX\": \"100%\",\n" +
                        "           \"sScrollXInner\": \"110%\",\n";
        }
        if(!columnList.isEmpty()){
            str +=      "           \"aoColumns\": [\n";
            for (Column column : columnList) {
                if (column == null) {
                    str += "null,\n"; continue;
                }
                str += "{";
                if (!StringUtils.isEmpty(column.getCellType()))
                    str +=  "\"sCellType\":  \""+column.getCellType()+"\",";
                if (!StringUtils.isEmpty(column.getWidth()))
                    str +=  "\"sWidth\": \""+column.getWidth()+"\",";
                if (!StringUtils.isEmpty(column.getStyleClass()))
                    str +=  "\"sClass\": \""+column.getStyleClass()+"\",";
                if (!StringUtils.isEmpty(column.getCreatedCellFunc())) {
                    str +=  "\"fnCreatedCell\": function (nTd, sData, oData, iRow, iCol) {\n";
                    str +=                  column.getCreatedCellFunc()+"\n";
                    str +=  "},";
                }
                if (!StringUtils.isEmpty(column.getData())) {
                    str +=  "\"mData\": \""+column.getData()+"\",";
                }
                if (!StringUtils.isEmpty(column.getDataScript())) {
                    str +=  "\"mData\": function ( source, type, val ) {\n";
                    str +=      column.getDataScript()+"\n";
                    str +=  "},";
                }
                if (!StringUtils.isEmpty(column.getRender())) {
                    str +=  "\"mRender\": \""+column.getRender()+"\",";
                }
                if (!StringUtils.isEmpty(column.getRenderScript())) {
                    str +=  "\"mRender\": function ( data, type, row ) {\n";
                    str +=      column.getRenderScript()+"\n";
                    str +=  "},";
                }
                if (!StringUtils.isEmpty(column.getDefaultContent())) {
                    str +=  "\"sDefaultContent\": \""+column.getDefaultContent()+"\",";
                }
                if (!StringUtils.isEmpty(column.getTitle())) {
                    str +=  "\"sTitle\": \""+column.getTitle()+"\",";
                }
                if (!StringUtils.isEmpty(column.getSortable())) {
                    str +=  "\"bSortable\": "+column.getSortable()+",";
                }
                if (!StringUtils.isEmpty(column.getVisible())) {
                    str +=  "\"bVisible\": "+column.getVisible()+",";
                }
                str = str.substring(0, str.length()-1);
                str += "},";
            }
            str = str.substring(0, str.length()-1);
            str +=      "],\n";
        }
        if(getServerSide())
            str +=      "           \"bServerSide\": true,\n";
            str +=      "           \"bProcessing\" : true,\n" +
                        "           \"sAjaxSource\" : '"+getAction()+"',\n" +
                        "           \"bRetrieve\"   : true " +
                        "        });\n" +
                        "    });\n" +
                        "</script>";
        try {
            TagUtils.write(pageContext.getOut(),str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }

    public String getAction() {
        String contextName = TagUtils.getContextName(this.pageContext);
        if (!action.startsWith("/")){
            action = "/"+action;
        }
        return contextName+action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getServerSide() {
        return serverSide;
    }

    public void setServerSide(Boolean serverSide) {
        this.serverSide = serverSide;
    }

    public String getGridHead() {
        return gridHead;
    }

    public void setGridHead(String gridHead) {
        this.gridHead = gridHead;
    }

    public void addColumn(Column column){
        columnList.add(column);
    }

    public Boolean getScrollable() {
        return scrollable;
    }

    public void setScrollable(Boolean scrollable) {
        this.scrollable = scrollable;
    }
}
