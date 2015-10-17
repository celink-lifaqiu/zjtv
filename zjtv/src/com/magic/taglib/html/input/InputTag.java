package com.magic.taglib.html.input;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-28
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
public class InputTag extends org.springframework.web.servlet.tags.form.InputTag {
    private Boolean rule_required = false;
    private Boolean rule_email = false;
    private Boolean rule_url = false;
    private Boolean rule_date = false;
    private Boolean rule_number = false;
    private Boolean rule_digits = false;
    private String rule_equalTo = null;
    private String rule_maxlength = null;
    private String rule_minlength = null;
    private String rule_rangelength = null;
    private String rule_range = null;
    private String rule_max = null;
    private String rule_min = null;
    @Override
    protected void writeOptionalAttributes(TagWriter tagWriter) throws JspException {
        super.writeOptionalAttributes(tagWriter);
        if (rule_required) tagWriter.writeAttribute("data-rule-required", String.valueOf(rule_required));
        if(rule_email) tagWriter.writeAttribute("data-rule-email", String.valueOf(rule_email));
        if(rule_url) tagWriter.writeAttribute("data-rule-url", String.valueOf(rule_url));
        if(rule_date) tagWriter.writeAttribute("data-rule-date", String.valueOf(rule_date));
        if(rule_number) tagWriter.writeAttribute("data-rule-number", String.valueOf(rule_number));
        if(rule_digits) tagWriter.writeAttribute("data-rule-digits", String.valueOf(rule_digits));
        if(!StringUtils.isEmpty(rule_equalTo)) tagWriter.writeAttribute("data-rule-equalTo", rule_equalTo);
        if(!StringUtils.isEmpty(rule_maxlength)) tagWriter.writeAttribute("data-rule-maxlength", rule_maxlength);
        if(!StringUtils.isEmpty(rule_minlength)) tagWriter.writeAttribute("data-rule-minlength", rule_minlength);
        if(!StringUtils.isEmpty(rule_rangelength)) tagWriter.writeAttribute("data-rule-rangelength", rule_rangelength);
        if(!StringUtils.isEmpty(rule_range)) tagWriter.writeAttribute("data-rule-range", rule_range);
        if(!StringUtils.isEmpty(rule_max)) tagWriter.writeAttribute("data-rule-max", rule_max);
        if(!StringUtils.isEmpty(rule_min)) tagWriter.writeAttribute("data-rule-", rule_min);
    }

    public Boolean getRule_required() {
        return rule_required;
    }

    public void setRule_required(Boolean rule_required) {
        this.rule_required = rule_required;
    }

    public Boolean getRule_email() {
        return rule_email;
    }

    public void setRule_email(Boolean rule_email) {
        this.rule_email = rule_email;
    }

    public Boolean getRule_url() {
        return rule_url;
    }

    public void setRule_url(Boolean rule_url) {
        this.rule_url = rule_url;
    }

    public Boolean getRule_date() {
        return rule_date;
    }

    public void setRule_date(Boolean rule_date) {
        this.rule_date = rule_date;
    }

    public Boolean getRule_number() {
        return rule_number;
    }

    public void setRule_number(Boolean rule_number) {
        this.rule_number = rule_number;
    }

    public Boolean getRule_digits() {
        return rule_digits;
    }

    public void setRule_digits(Boolean rule_digits) {
        this.rule_digits = rule_digits;
    }

    public String getRule_equalTo() {
        return rule_equalTo;
    }

    public void setRule_equalTo(String rule_equalTo) {
        this.rule_equalTo = rule_equalTo;
    }

    public String getRule_maxlength() {
        return rule_maxlength;
    }

    public void setRule_maxlength(String rule_maxlength) {
        this.rule_maxlength = rule_maxlength;
    }

    public String getRule_minlength() {
        return rule_minlength;
    }

    public void setRule_minlength(String rule_minlength) {
        this.rule_minlength = rule_minlength;
    }

    public String getRule_rangelength() {
        return rule_rangelength;
    }

    public void setRule_rangelength(String rule_rangelength) {
        this.rule_rangelength = rule_rangelength;
    }

    public String getRule_range() {
        return rule_range;
    }

    public void setRule_range(String rule_range) {
        this.rule_range = rule_range;
    }

    public String getRule_max() {
        return rule_max;
    }

    public void setRule_max(String rule_max) {
        this.rule_max = rule_max;
    }

    public String getRule_min() {
        return rule_min;
    }

    public void setRule_min(String rule_min) {
        this.rule_min = rule_min;
    }
}
