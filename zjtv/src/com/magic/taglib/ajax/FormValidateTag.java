package com.magic.taglib.ajax;

import com.magic.commons.utils.TagUtils;
import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-30
 * Time: 下午12:58
 * To change this template use File | Settings | File Templates.
 */
public class FormValidateTag extends SimpleTagSupport {
    private String formId;
    private String callback;
    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) this.getJspContext();
        StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">\n");
        stringBuffer.append("$(document).ready(function(){" +
                "$('#" + getFormId() + "').validate({\n" +
                "        submitHandler:function(form){\n" +
                "            $(form).ajaxSubmit({\n" +
                "                success: function(){\n");
        if(!StringUtils.isEmpty(callback)){
        stringBuffer.append("   if("+callback+" != undefined)\n" +
                "                        "+callback+"();\n");
        }
        stringBuffer.append("   }\n" +
                "            });\n" +
                "        }\n" +
                "    });"+
                "});\n");
        stringBuffer.append("$(document).ajaxSuccess(function(){" +
                "$('#" + getFormId() + "').validate({\n" +
                "        submitHandler:function(form){\n" +
                "            $(form).ajaxSubmit({\n" +
                "                success: function(){\n");
        if(!StringUtils.isEmpty(callback)){
            stringBuffer.append("   if("+callback+" != undefined)\n" +
                "                        "+callback+"();\n");
        }
        stringBuffer.append("   }\n" +
                "            });\n" +
                "        }\n" +
                "    });"+
                "});\n");
        stringBuffer.append("</script>");
        TagUtils.write(pageContext.getOut(), stringBuffer.toString());
        super.doTag();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
