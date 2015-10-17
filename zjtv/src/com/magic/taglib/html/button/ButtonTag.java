package com.magic.taglib.html.button;

import com.magic.commons.utils.TagUtils;
import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-9
 * Time: 上午2:05
 * To change this template use File | Settings | File Templates.
 */
public class ButtonTag extends TagSupport implements DynamicAttributes {
    Map<String, Object> dynamicAttributes;
    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (this.dynamicAttributes == null) {
            this.dynamicAttributes = new HashMap<String, Object>();
        }
//        if (!isValidDynamicAttribute(localName, value)) {
//            throw new IllegalArgumentException(
//                    "Attribute " + localName + "=\"" + value + "\" is not allowed");
//        }
        dynamicAttributes.put(localName, value);
    }

    enum TYPE{PRIMARY,INFO,SUCCESS,WARNING,DANGER,INVERSE,LINK,DEFAULT};
    private String label;
    private String icon;
    private String dataToggle;
    private String onclick;
    private Boolean small = false;
    private Boolean mini = false;
    @Override
    public int doEndTag() throws JspException {
        StringBuffer buttonStr = new StringBuffer();
        String styleClass = "btn";
        TYPE buttonType = getButtonType();
        switch (buttonType){
            case PRIMARY: styleClass += " btn-primary";break;
            case INFO: styleClass += " btn-info";break;
            case SUCCESS: styleClass += " btn-success";break;
            case WARNING: styleClass += " btn-warning";break;
            case DANGER: styleClass += " btn-danger";break;
            case INVERSE: styleClass += " btn-inverse";break;
            case LINK: styleClass += " btn-link";break;
            default: styleClass += " btn-default";
        }
        if (small)
            styleClass += " btn-sm";
        if (mini)
            styleClass += " btn-xs";
        buttonStr.append("<button type=\"button\" class=\""+styleClass+"\"");
        if (getDataToggle() != null) {
            buttonStr.append(" data-toggle=\""+getDataToggle());
        }
        buttonStr.append("\" onclick=\""+getOnclick()+"\" "+getDynamicAttributes()+">"+getButtonContent()+"</button>");
        try {
            TagUtils.write(this.pageContext.getOut(), buttonStr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }

    private String  getDynamicAttributes(){
        StringBuffer attr = new StringBuffer();
        if (dynamicAttributes != null) {
            for (Iterator<Map.Entry<String, Object>> iterator = dynamicAttributes.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Object> entry = iterator.next();
                attr.append(entry.getKey()+"="+entry.getValue().toString());
            }
        }
        return attr.toString();
    }

    private String getButtonContent(){
        String str = "";
        if (!StringUtils.isEmpty(getIcon())){
            str = "<i class=\"icon-"+getIcon()+"\"></i> "+getLabel();
        }else{
            str = getLabel();
        }
        return str;
    }

    protected TYPE getButtonType(){
        return TYPE.DEFAULT;
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

    public String getLabel() {
        return label==null?"":label;
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

    public String getDataToggle() {
        return dataToggle;
    }

    public void setDataToggle(String dataToggle) {
        this.dataToggle = dataToggle;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }
}
