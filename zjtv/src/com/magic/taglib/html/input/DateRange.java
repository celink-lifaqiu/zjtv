package com.magic.taglib.html.input;

import com.magic.commons.utils.TagUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-28
 * Time: 上午8:19
 * To change this template use File | Settings | File Templates.
 */
public class DateRange extends InputTag {
    private String minDate = "";
    private String maxDate = "";
    private String format = "";
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

//    @Override
//    protected String getCssClass() {
//        return "span12 " + super.getCssClass();
//    }

    @Override
    public int doEndTag() throws JspException {
        StringBuffer inputAttr = new StringBuffer();
        try {
            StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">\n");
            stringBuffer.append("$(document).ready(function(){\n");
            stringBuffer.append("$('#"+getId()+"').daterangepicker({\n");
            if(!StringUtils.isEmpty(minDate))
            stringBuffer.append("       minDate: '"+minDate+"',\n");
            if (!StringUtils.isEmpty(maxDate))
            stringBuffer.append("       maxDate: '"+maxDate+"',\n");
            if (!StringUtils.isEmpty(format))
            stringBuffer.append("       format:'"+format+"'\n");
            stringBuffer.append("       showDropdowns: true});\n");
            stringBuffer.append("});");
            stringBuffer.append("</script>\n");
            TagUtils.write(pageContext.getOut(), stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }
}
