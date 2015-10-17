package com.magic.core.springframework;

import com.magic.app.zjtv.common.Messages;
import com.magic.commons.helper.HttpResultHelper;
import com.magic.commons.models.HttpDataResult;
import com.magic.commons.models.HttpListResult;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.NoAuth;
import com.magic.core.annotation.layout.Layout;
import com.magic.core.annotation.layout.LayoutMenuHorizontal;
import com.magic.core.annotation.layout.LayoutNoMenu;
import com.magic.core.annotation.layout.LayoutNone;
import com.magic.core.compontents.menu.MenuContainer;
import com.magic.core.web.tiles.LayoutConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class CustomerHandlerInterceptor extends HandlerInterceptorAdapter {
    Log log = LogFactory.getLog(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            NoAuth noAuth = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NoAuth.class);
            if (noAuth == null) {
                if(!checkAuthorization(request, response, handlerMethod)){
                    return false;
                }
            }

            Menu headMenu = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Menu.class);
            if (headMenu != null) {
                request.setAttribute("moduleTitle",headMenu.label());
            }
            Menu subMenu = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Menu.class);
            if (subMenu != null) {
                request.setAttribute("funcTitle", subMenu.label());
            }
        }
        return true;
    }

    private boolean checkAuthorization(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler){
            log.debug("checkAuthorization......");
//        String token = request.getHeader("Pragma-Token");
//        Method method = handler.getMethod();
//        if("com.magic.commons.models.HttpDataResult".equals(method.getReturnType().getName()) ||
//                "com.magic.commons.models.HttpListResult".equals(method.getReturnType().getName())){
//            try {
//                if(com.magic.commons.utils.StringUtils.isNotEmpty(token)){
//                    return true;
//                }else{
//                    if ("com.magic.commons.models.HttpDataResult".equals(method.getReturnType().getName())) {
//                        response.setCharacterEncoding("utf8");
//                        response.setContentType("application/json");
//                        PrintWriter out = response.getWriter();
//                        HttpDataResult result = new HttpDataResult();
//                        result = (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USER_TOKEN_ERR);
//                        JSONObject obj = JSONObject.fromObject(result);
//                        out.print(obj);
//                    } else if ("com.magic.commons.models.HttpListResult".equals(method.getReturnType().getName())) {
//                        response.setCharacterEncoding("utf8");
//                        response.setContentType("application/json");
//                        PrintWriter out = response.getWriter();
//                        HttpListResult result = new HttpListResult();
//                        result = (HttpListResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USER_TOKEN_ERR);
//                        JSONObject obj = JSONObject.fromObject(result);
//                        out.print(obj);
//                    }
//                    return false;//如果Token是空，说明没有登录 需要登录
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return true;//如果不是客户端api不做登录判断
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
    		ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ResponseBody.class);
        	if(responseBody != null) return;
            handleMenu(request, handlerMethod);
            handleView(request, handlerMethod, modelAndView);
            handleLayout(request, handlerMethod, modelAndView);
        }
        log.debug("This is postHandle...");
    }

    /**
     * @param request
     * @param handlerMethod
     * Detect which menu is actived.
     */
    private void handleMenu(HttpServletRequest request,HandlerMethod handlerMethod){
        HttpSession session = request.getSession();
        JFengWebAppContext appContext = (JFengWebAppContext) WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        MenuContainer menuContainer = appContext.getMenuContainer();
        Menu classAnno = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Menu.class);
        String headMenuId = "";
        if (classAnno!=null)
            headMenuId = classAnno.id();
        session.setAttribute("headMenuList", menuContainer.getMenuItemList());
        session.setAttribute("headMenu", menuContainer.getMenuItem(headMenuId)); //will retrieve sub menu list from this headMenu
        Menu methodAnno = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Menu.class);
        if (methodAnno != null) {
            menuContainer.activeMemu(methodAnno.id());
        }else{
//          active current module's headMenu, if have.
            if (classAnno != null) {
                menuContainer.activeMemu(headMenuId);
            }
        }
    }

    /*
    private void handleBreadcrumb(HttpServletRequest request){
        JFengWebAppContext appContext = (JFengWebAppContext) WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        MenuContainer menuContainer = appContext.getMenuContainer();
    }
    */

    private void handleView(HttpServletRequest request,HandlerMethod handlerMethod, ModelAndView modelAndView){
        if (modelAndView == null) {
            return;
        }
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), RequestMapping.class);
        String moduleUri = "";
        if (requestMapping != null) {
            moduleUri = requestMapping.value()[0];
        }
        String viewName = modelAndView.getViewName();
        if (viewName.startsWith(UrlBasedViewResolver.FORWARD_URL_PREFIX)||
                viewName.startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX))
            return;

        if (!StringUtils.isEmpty(moduleUri)&&!moduleUri.endsWith("/"))
            moduleUri += "/";
        modelAndView.setViewName("/jsp/"+moduleUri+modelAndView.getViewName());
    }
    private void handleLayout(HttpServletRequest request,HandlerMethod handlerMethod, ModelAndView modelAndView){
        String layoutName =   LayoutConstants.default_layout;
        Layout layout = AnnotationUtils.findAnnotation(handlerMethod.getMethod(),Layout.class);
        LayoutMenuHorizontal layoutMenuHorizontal = AnnotationUtils.findAnnotation(handlerMethod.getMethod(),LayoutMenuHorizontal.class);
        LayoutNoMenu layoutNoMenu = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), LayoutNoMenu.class);
        LayoutNone layoutNone = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), LayoutNone.class);
        if (layout != null) {
            layoutName = layout.value();
        }else if(layoutMenuHorizontal != null){
            layoutName = "jfeng.layout.main-menu-horizontal";
        }else if(layoutNoMenu != null){
            layoutName = "jfeng.layout.main-no-menu";
        }else if(layoutNone != null){
            layoutName = "jfeng.layout.none";
        }
        request.setAttribute("tiles_layout_name", layoutName);
    }
}
