package com.magic.taglib;

import com.magic.commons.utils.TagUtils;
import com.magic.core.compontents.menu.MenuContainer;
import com.magic.core.compontents.menu.MenuItem;
import com.magic.core.springframework.JFengWebAppContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-11
 * Time: 下午1:38
 * To change this template use File | Settings | File Templates.
 */
public class BreadcrumbTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) this.getJspContext();
        String contextPath = TagUtils.getContextName(pageContext);
        StringBuffer breadcrumb = new StringBuffer("<div id=\"breadcrumbContainer\" class=\"row-fluid\">\n");
        breadcrumb.append("    <ul class=\"breadcrumb\">\n");
        breadcrumb.append("        <li><a href=\""+contextPath+"/home\"><i class=\"icon-home\"></i>首页</a></li>\n");
        ServletContext servletContext = pageContext.getServletContext();
        JFengWebAppContext appContext = (JFengWebAppContext) WebApplicationContextUtils.getWebApplicationContext(servletContext);
        MenuContainer menuContainer = appContext.getMenuContainer();
        LinkedList<MenuItem> menuItems = menuContainer.getBreadcrumb();
        while(!menuItems.isEmpty()){
            MenuItem menuItem = menuItems.pop();
            if (menuItems.isEmpty()){
                breadcrumb.append("        <li class=\"active\">"+menuItem.getLabel()+"</li>\n");
            }else{
                breadcrumb.append("        <li><a href=\""+contextPath+menuItem.getUri()+"\">"+menuItem.getLabel()+"</a></li>\n");
            }
        }
        breadcrumb.append("    </ul>\n</div>\n");
        TagUtils.write(getJspContext().getOut(), breadcrumb.toString());
        super.doTag();
    }
}
