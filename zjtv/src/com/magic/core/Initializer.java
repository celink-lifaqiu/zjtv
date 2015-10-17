package com.magic.core;

import com.magic.core.springframework.JFengWebAppContext;
import com.magic.core.web.tiles.JFengTilesDecorationFilter;
import com.magic.core.web.tiles.LayoutConstants;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 14-3-4
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class Initializer implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final JFengWebAppContext context = new JFengWebAppContext();

        context.scan("com.magic.**.config");

        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addListener(new RequestContextListener());

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

//        Download Servlet
        ServletRegistration.Dynamic downloadServlet = servletContext.addServlet("downloadServlet", new DownloadServlet());
        downloadServlet.setInitParameter("enc", "UTF-8");
        downloadServlet.addMapping("/download/*");

        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding","utf-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null,true,"/*");


//      Tiles Filter
        FilterRegistration.Dynamic tilesFileter = servletContext.addFilter("tilesFilter", new JFengTilesDecorationFilter());
        tilesFileter.setInitParameter("definition", LayoutConstants.default_layout);    //the key "definition" is default tiles template key in class TilesDecorationFilter
        tilesFileter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.FORWARD),true,"*.jsp");

//      让Hibernate Session的scope在整个request内
        FilterRegistration.Dynamic openEneityManagerFilter = servletContext.addFilter("openEneityManagerFilter", new OpenEntityManagerInViewFilter());
        openEneityManagerFilter.setInitParameter("entityManagerFactoryBeanName", "entityManagerFactory");
        openEneityManagerFilter.addMappingForUrlPatterns(null,true,"/*");

//      Spring Security Filter
        FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        springSecurityFilterChain.addMappingForUrlPatterns(null,true,"/*");
    }
}
