package com.magic.taglib.html.dialog;

import com.magic.commons.utils.TagUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-16
 * Time: 上午2:38
 * To change this template use File | Settings | File Templates.
 */
public class DialogTrigger extends SimpleTagSupport {
    private String target;
    private String action;
    private String cssClass = "";
    private String label;
    private String icon;
    private Boolean showAsLabel = false;
    private Boolean small = false;
    private Boolean mini = false;

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) this.getJspContext();
        StringBuffer stringBuffer = new StringBuffer();
        if (!showAsLabel){
            stringBuffer.append("<button type=\"button\" data-toggle=\"modal\" class=\"btn "+getCssClass()+"\" ");
        }else{
            stringBuffer.append("<a data-toggle=\"modal\" ");
        }

        if (StringUtils.isNotEmpty(getAction())){
            stringBuffer.append("href=\""+getAction()+"\" ");
        }
        if (StringUtils.isNotEmpty(getTarget())){
            stringBuffer.append("data-target=\"#"+getTarget()+"\" ");
        }
        stringBuffer.append(">");
        if (StringUtils.isNotEmpty(getIcon())){
            stringBuffer.append("<i class=\"icon-"+getIcon()+"\"></i> ");
        }
        if (StringUtils.isNotEmpty(getLabel())){
            stringBuffer.append(getLabel()+"");
        }
        if (!showAsLabel)
            stringBuffer.append("</button>");
        else
            stringBuffer.append("</a>");
        TagUtils.write(pageContext.getOut(), stringBuffer.toString());
        super.doTag();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCssClass() {
        if (small)
            return "btn-sm " + cssClass;
        if (mini)
            return "btn-xs " + cssClass;
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getShowAsLabel() {
        return showAsLabel;
    }

    public void setShowAsLabel(Boolean showAsLabel) {
        this.showAsLabel = showAsLabel;
    }

    public Boolean getSmall() {
        return small;
    }

    public void setSmall(Boolean small) {
        this.small = small;
    }

    public Boolean getMini() {
        return mini;
    }

    public void setMini(Boolean mini) {
        this.mini = mini;
    }
}
