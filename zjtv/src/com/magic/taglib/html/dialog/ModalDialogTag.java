package com.magic.taglib.html.dialog;

import com.magic.commons.utils.TagUtils;
import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-9
 * Time: 下午10:49
 * To change this template use File | Settings | File Templates.
 */
public class ModalDialogTag extends BodyTagSupport {
    private String triggerLabel = "";
    private String triggerStyleClass = "";
    private String onshow;
    private String onshown;
    private String onhide;
    private String onhidden;
    private String icon;
    private Boolean visible = true;
    private Boolean checkable = false;
    private Dialog dialog;

    public ModalDialogTag() {
        dialog = new Dialog();
    }

    @Override
    public int doStartTag() throws JspException {
//        String trigger = "<button id=\""+getId()+"Button\" type=\"button\" class=\"btn "+getTriggerStyleClass()+"\" data-toggle=\"modal\" data-target=\"#"+getId()+"\">"+getTriggerLabel()+"</button>\n";
//        try {
//            TagUtils.write(pageContext.getOut(),trigger);
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        return super.doStartTag();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            StringBuffer stringBuffer = new StringBuffer("<div id=\""+getId()+"\" class=\"modal fade\" role=\"dialog\" aria-hidden=\"true\">\n");
            stringBuffer.append("<div class=\"modal-dialog\">\n")
                        .append("   <div class=\"modal-content\">\n");
            if (!StringUtils.isEmpty(dialog.getHeader())){
                stringBuffer.append("<div class=\"modal-header\">\n");
                stringBuffer.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>\n");
                stringBuffer.append("<h4 class=\"modal-title\">"+dialog.getHeader()+"</h4>\n");
                stringBuffer.append("</div>\n");
            }
//            if (!StringUtils.isEmpty(dialog.getBody())){
                stringBuffer.append("<div id=\""+getId()+"Body\" class=\"modal-body\">\n");
                stringBuffer.append(dialog.getBody()+"\n");
                stringBuffer.append("</div>\n");
//            }
            if (!StringUtils.isEmpty(dialog.getFooter())){
                stringBuffer.append("<div class=\"modal-footer\">\n");
                if (checkable)
                stringBuffer.append("<label class=\"checkbox inline\"><input id=\""+getId()+"Ck\" type=\"checkbox\"/>继续新建？</label>");
                stringBuffer.append(dialog.getFooter()+"\n");
                stringBuffer.append("<button class=\"btn btn-default\" data-dismiss=\"modal\" aria-hidden=\"true\">关闭</button>\n");
                stringBuffer.append("</div>\n");
            }
            stringBuffer.append("</div>\n</div>\n</div>\n");
            TagUtils.write(getBodyContent().getEnclosingWriter(), stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">\n");
        stringBuffer.append("$(document).ready(function(){\n");
        if(!StringUtils.isEmpty(getOnshow())){
            stringBuffer.append("$('#"+getId()+"').on('show',function(){"+getOnshow()+"});\n");
        }
        if(!StringUtils.isEmpty(getOnshown())){
            stringBuffer.append("$('#"+getId()+"').on('shown',function(){"+getOnshown()+"});\n");
        }
        if(!StringUtils.isEmpty(getOnhide())){
            stringBuffer.append("$('#"+getId()+"').on('hide',function(){"+getOnhide()+"});\n");
        }
        stringBuffer.append("$('#"+getId()+"').on('hidden',function(){" +
                                "$(this).removeData('modal');\n");
        if(!StringUtils.isEmpty(getOnhidden())){
            stringBuffer.append(getOnhidden()+"\n");
        }
        stringBuffer.append("});\n");
        stringBuffer.append("});\n");
        stringBuffer.append("</script>\n");
        try {
            TagUtils.write(pageContext.getOut(),stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return super.doEndTag();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public String getOnshow() {
        return onshow;
    }

    public void setOnshow(String onshow) {
        this.onshow = onshow;
    }

    public String getOnshown() {
        return onshown;
    }

    public void setOnshown(String onshown) {
        this.onshown = onshown;
    }

    public String getOnhide() {
        return onhide;
    }

    public void setOnhide(String onhide) {
        this.onhide = onhide;
    }

    public String getOnhidden() {
        return onhidden;
    }

    public void setOnhidden(String onhidden) {
        this.onhidden = onhidden;
    }

    public String getTriggerLabel() {
        return triggerLabel;
    }

    public void setTriggerLabel(String triggerLabel) {
        this.triggerLabel = triggerLabel;
    }

    public String getButtonContent(){
        String str = "";
        if (!StringUtils.isEmpty(getIcon())){
            str = "<i class=\"icon-"+getIcon()+"\"></i> "+getTriggerLabel();
        }else{
            str = getTriggerLabel();
        }
        return str;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTriggerStyleClass() {
        return triggerStyleClass;
    }

    public void setTriggerStyleClass(String triggerStyleClass) {
        this.triggerStyleClass = triggerStyleClass;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getCheckable() {
        return checkable;
    }

    public void setCheckable(Boolean checkable) {
        this.checkable = checkable;
    }
}
