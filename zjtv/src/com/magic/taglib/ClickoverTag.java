package com.magic.taglib;

import com.magic.commons.utils.TagUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: YinJianFeng
 * Date: 13-8-10
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class ClickoverTag extends BodyTagSupport {
    private String id;
    private String cssClass;
    private String placement = "bottom";
    private String width = "532";
    private String action;
    private String icon;
    private String label;
    private String title;

    @Override
    public int doAfterBody() throws JspException {
        String body = getBodyContent().getString();
        StringBuffer stringBuffer = new StringBuffer("<a href=\"javascript:void(0)\" id=\""+getId()+"\" rel=\""+getId()+"\" ");
        if (StringUtils.isNotEmpty(getTitle())){
            stringBuffer.append(" title=\"" + getTitle() + "\" ");
        }
        if (StringUtils.isNotEmpty(getCssClass())){
            stringBuffer.append(" class=\""+getCssClass()+"\" ");
        }
        if (StringUtils.isNotEmpty(body)){
            stringBuffer.append("data-content='"+body+"' ");
        }
        if (StringUtils.isNotEmpty(getAction())){
            stringBuffer.append("data-action=\""+getAction()+"\" ");
        }
        stringBuffer.append(">\n");
        if (StringUtils.isNotEmpty(getIcon())) {
            stringBuffer.append("<i class=\"icon-"+getIcon()+"\"></i> ");
        }
        stringBuffer.append(getLabel());
        stringBuffer.append("</a>\n");
        try {
            TagUtils.write(getBodyContent().getEnclosingWriter(), stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">\n");
        stringBuffer.append("function "+getId()+"() {\n")
                    .append("$('[rel=\""+getId()+"\"]').clickover({\n" +
                            "           width: "+getWidth()+",\n" +
                            "           html:true,\n");
        if (StringUtils.isNotEmpty(getAction())) {
            stringBuffer.append("       onShown: function(){\n" +
                                "       var action = this.options.action;\n" +
                                "       var target = $(\"div.clickover\").find(\"div.popover-content\");\n" +
                                "       target.html(\"内容加载中...\");\n" +
                                "       $.ajax({\n" +
                                "           type:\"POST\",\n" +
                                "           url:action,\n" +
                                "           data:\"\",\n" +
                                "           async:true,\n" +
                                "           success:function(response){\n" +
                                "               target.html(response);\n" +
                                "           }\n" +
                                "       });},\n");
        }
        stringBuffer.append("   placement: \""+ getPlacement() +"\"\n");
        stringBuffer.append("});}\n");
        stringBuffer.append("$(document).ready(function(){" + getId() + "();});\n");
        stringBuffer.append("</script>");
        try {
            TagUtils.write(pageContext.getOut(),stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
