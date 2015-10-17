package com.magic.core.springframework;

import com.magic.commons.StringPrintWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 14-3-5
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class CustomerExceptionResolver implements HandlerExceptionResolver {
    Log log = LogFactory.getLog(getClass());
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("Catch Exception: ",ex);
        Map<String,Object> map = new HashMap<String,Object>();
        StringPrintWriter strintPrintWriter = new StringPrintWriter();
        ex.printStackTrace(strintPrintWriter);
        map.put("errorMsg", strintPrintWriter.toString());
//        forward to CoreController, then can let HandlerInterceptor execute
        return new ModelAndView(UrlBasedViewResolver.FORWARD_URL_PREFIX+"/toErrorPage",map);
    }
}
