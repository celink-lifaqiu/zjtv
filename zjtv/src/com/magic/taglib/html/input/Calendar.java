package com.magic.taglib.html.input;

import com.magic.commons.utils.TagUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-28
 * Time: 上午8:11
 * To change this template use File | Settings | File Templates.
 */
public class Calendar extends InputTag {
    private String format = "YYYY年MM月DD日";
    private String formatTime = "YYYY年MM月DD日 HH:mm";
    private String formatTimeWithSecond = "YYYY年MM月DD日 HH:mm:ss";
    private Boolean formatWithTime = false;
    private Boolean timeOnly = false;
    private Boolean includeTime = false;

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag("div");
        tagWriter.writeAttribute("id", getId()+"_panel");
        tagWriter.writeAttribute("class", "input-group");
        super.writeTagContent(tagWriter);
        tagWriter.startTag("span");
        tagWriter.writeAttribute("class", "input-group-addon");
        tagWriter.startTag("i");
        tagWriter.writeAttribute("class", "icon-calendar");
        tagWriter.writeAttribute("data-date-icon","icon-calendar");
        tagWriter.endTag(true);
        tagWriter.endTag(true);
        tagWriter.endTag();
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
//        StringBuffer inputAttr = new StringBuffer();
        try {
            StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">\n");
            stringBuffer.append("$(document).ready(function(){\n" +
                                "    $('#"+getId()+"_panel').datetimepicker({\n" +
                                "           language: 'zh-CN',\n" +
                                "            format: '"+getFormat()+"',\n");
            if (formatWithTime){
                stringBuffer.append("       pickTime: true,\n");
                stringBuffer.append("       pickDate: true,\n");
                stringBuffer.append("       pick12HourFormat: false,\n");
                if(includeTime)
                	stringBuffer.append("		useSeconds: true,\n");
            }else{
                stringBuffer.append("       pickTime: false,\n");
                stringBuffer.append("       pickDate: true,\n");
            }
            if (timeOnly){
                stringBuffer.append("       pickTime: true,\n");
                stringBuffer.append("       pickDate: false,\n");
                stringBuffer.append("       pick12HourFormat: false,\n");
                if(includeTime)
                	stringBuffer.append("		useSeconds: true,\n");
            }
            stringBuffer.append("           icons: {\n" +
                                "                time: 'icon-time',\n" +
                                "                date: 'icon-calendar',\n" +
                                "                up: 'icon-chevron-up',\n" +
                                "                down: 'icon-chevron-down'\n" +
                                "            }\n" +
                                "        })});\n");
            stringBuffer.append("</script>\n");
            TagUtils.write(pageContext.getOut(), stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return super.doEndTag();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Boolean getFormatWithTime() {
        return formatWithTime;
    }

    public void setFormatWithTime(Boolean formatWithTime) {
        this.formatWithTime = formatWithTime;
    }

    public String getFormat() {
        if (formatWithTime){
        	if(includeTime)
        		return formatTimeWithSecond;
        	return formatTime;
        }
        return format;
    }

    public Boolean getTimeOnly() {
        return timeOnly;
    }

    public void setTimeOnly(Boolean timeOnly) {
        this.timeOnly = timeOnly;
    }

	public Boolean getIncludeTime() {
		return includeTime;
	}

	public void setIncludeTime(Boolean includeTime) {
		this.includeTime = includeTime;
	}

    
//    public void setFormat(String format) {
//        this.format = format;
//    }
}
