package com.magic.core.controller;

import com.magic.commons.service.CommonService;
import com.magic.core.annotation.layout.LayoutNone;
import com.magic.core.compontents.menu.MenuContainer;
import com.magic.core.springframework.JFengWebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yin Jian Feng on 14-3-23.
 */
@Controller
public class CoreController {

    @Autowired
    CommonService commonService;

    @RequestMapping("/")
    @LayoutNone
    public String index(){
        return "login";
    }

    @RequestMapping("/home")
    public String main(HttpServletRequest request){
//        return UrlBasedViewResolver.REDIRECT_URL_PREFIX+"/user";
        JFengWebAppContext appContext = (JFengWebAppContext) WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        MenuContainer menuContainer = appContext.getMenuContainer();
        menuContainer.clear();
        return "welcome";
    }
}
