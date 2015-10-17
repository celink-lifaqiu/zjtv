package com.magic.taglib.html.input;

import com.magic.commons.utils.TagUtils;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-16
 * Time: 上午4:25
 * To change this template use File | Settings | File Templates.
 */
public class InputMoney extends org.springframework.web.servlet.tags.form.InputTag {
    private String symbol = "";
    private String thousands = ",";
    private String decimal = ".";
    private Boolean symbolStay = true;
    private Boolean allowZero = true;
    private Boolean allowNegative = true;
    private Boolean defaultZero = true;

    @Override
    public int doEndTag() throws JspException {
        try {
            StringBuffer stringBuffer = new StringBuffer("<script type=\"text/javascript\">\n");
            stringBuffer.append("$(document).ready(function(){\n");
            stringBuffer.append("$(\"#"+getId()+"\").maskMoney({symbol:'"+getSymbol()+"',thousands:'"+getThousands()+"'," +
                    "decimal:'"+getDecimal()+"',symbolStay:"+getSymbolStay()+",allowZero:"+getAllowZero()+"," +
                    "allowNegative:"+getAllowNegative()+",defaultZero:"+getDefaultZero()+"});\n");
            stringBuffer.append("});");
            stringBuffer.append("</script>\n");
            TagUtils.write(pageContext.getOut(), stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getThousands() {
        return thousands;
    }

    public void setThousands(String thousands) {
        this.thousands = thousands;
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    public Boolean getSymbolStay() {
        return symbolStay;
    }

    public void setSymbolStay(Boolean symbolStay) {
        this.symbolStay = symbolStay;
    }

    public Boolean getAllowZero() {
        return allowZero;
    }

    public void setAllowZero(Boolean allowZero) {
        this.allowZero = allowZero;
    }

    public Boolean getAllowNegative() {
        return allowNegative;
    }

    public void setAllowNegative(Boolean allowNegative) {
        this.allowNegative = allowNegative;
    }

    public Boolean getDefaultZero() {
        return defaultZero;
    }

    public void setDefaultZero(Boolean defaultZero) {
        this.defaultZero = defaultZero;
    }
}
