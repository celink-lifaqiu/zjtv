package com.magic.core.security;

import com.magic.commons.service.AdminService;
import com.magic.core.compontents.menu.MenuContainer;
import com.magic.core.springframework.JFengWebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-6-11
 * Time: 上午1:52
 * To change this template use File | Settings | File Templates.
 */
public class JFengSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    Logger logger = LoggerFactory.getLogger(JFengSecurityMetadataSource.class);
//    @Autowired
//    private ResourceDAO resourceDAO;
    @Autowired
    AdminService adminService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
/*
    public JFengSecurityMetadataSource() {
        loadResourceDefine();
    }*/


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        HttpServletRequest request = ((FilterInvocation)object).getRequest();
        JFengWebAppContext appContext = (JFengWebAppContext) WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        if (resourceMap == null) {
            resourceMap = appContext.getPermissionResMap();
        }
        String requestUrl = ((FilterInvocation)object).getRequestUrl();
        requestUrl = requestUrl.startsWith("/")?requestUrl:"/"+requestUrl;


        Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
        AntPathMatcher antMatcher = new AntPathMatcher();
        for (Iterator<Map.Entry<String, Collection<ConfigAttribute>>> iterator = resourceMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Collection<ConfigAttribute>> next = iterator.next();
            String url = next.getKey();
            if (requestUrl.endsWith("/"))
                url = url+"/";
            if (antMatcher.match(url, requestUrl)){
                configAttributes.addAll(next.getValue());
            }
        }
        if (configAttributes == null || configAttributes.isEmpty()) {
            ConfigAttribute[] array = new ConfigAttribute[]{new SecurityConfig("P_COMMON")};
            return Arrays.asList(array);
        }
        return configAttributes;

       /* HttpServletRequest request = ((FilterInvocation)object).getRequest();
        String requestUrl = ((FilterInvocation)object).getRequestUrl();
        requestUrl = requestUrl.startsWith("/")?requestUrl:"/"+requestUrl;
        logger.debug("RequestUrl:"+requestUrl);
        if (resourceMap == null) {
            JFengWebAppContext appContext = (JFengWebAppContext) WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            resourceMap = appContext.getPermissionResMap();
        }
        Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
        AntPathMatcher antMatcher = new AntPathMatcher();
        for (Iterator<Map.Entry<String, Collection<ConfigAttribute>>> iterator = resourceMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Collection<ConfigAttribute>> next = iterator.next();
            String url = next.getKey();
            if (antMatcher.match(url, requestUrl)){
                configAttributes.addAll(next.getValue());
            }
        }
        if (configAttributes == null || configAttributes.isEmpty()) {
            ConfigAttribute[] array = new ConfigAttribute[]{new SecurityConfig("P_NONE")};
            return Arrays.asList(array);
        }
        return configAttributes;*/
//        return new ArrayList<ConfigAttribute>();
    }
//    //加载所有资源与权限的关系
//    public void loadResourceDefine() {
//        List<Resource> resources = adminService.getAllResources();
//        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
//        for (Resource resource : resources) {
//            String url = resource.getUrl();
//            url = url.startsWith("/")?url:"/"+url;
//            String permissionCode = resource.getPermissionCode();
//            Collection<ConfigAttribute> list = resourceMap.get(url);
//            if (list == null) {
//                list = new ArrayList<ConfigAttribute>();
//                resourceMap.put(url, list);
//            }
//            list.add(new SecurityConfig(permissionCode));
//        }
//    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
