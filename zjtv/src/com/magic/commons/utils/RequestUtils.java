package com.magic.commons.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	public static String getBasePath(HttpServletRequest request){
//		String path = request.getContextPath();
//	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//	    return basePath;
		PropertiesUtils propertiesUtils = PropertiesUtils.getInstance("config");
		return propertiesUtils.getValue("com.magic.hbzh.host");
	}
}
