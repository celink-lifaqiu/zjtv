package com.celink.out.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.celink.out.service.HessianService;
import com.magic.app.zjtv.common.Messages;
import com.magic.app.zjtv.common.Result;
import com.magic.app.zjtv.model.User;
import com.magic.app.zjtv.services.UserService;
import com.magic.commons.helper.HttpResultHelper;
import com.magic.commons.models.HttpDataResult;
import com.magic.commons.utils.ValidationUtils;
@Controller
public class HessianServiceImpl implements
		HessianService {

	@Autowired
	UserService userService;
	
	@Override
	public String test(String jsonData) {
		return jsonData;
	}

	@Override
	public String register(String account, String password) {
		Result result = new Result();
		if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
			result.setCode(100);
			result.setErr("参数不能为空");
			return result.toJson();
		}	
    	account = account.trim();
    	password = password.trim();
    	
    	if(!ValidationUtils.validateMoblie(account)){
    		result.setCode(101);
			result.setErr("账号必须是手机号码");
			return result.toJson();
    	}
    	
    	if(password.trim().length()<6 || password.trim().length()>16){
    		result.setCode(102);
			result.setErr("密码长度必须在6到16位之间");
			return result.toJson();
    	}
    	System.out.println(userService+"---------------");
    	// 根据用户名查找用户，如果存在，看其密码是否为null，如果是则表示用户从划一划游戏那里来的
    	User user = userService.findUserByAccount(account);
    	if(user!=null){
    		result.setCode(103);
			result.setErr("账号已存在");
			return result.toJson();
    	}else{
        	user = userService.saveUser(account, password);
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(user != null){
    		map.put("state", true);
    	}else{
    		map.put("state", false);
    	}
		result.setResult(map); //将返回的对象塞到result里面，它会自动被转换成JSON送给客户端
		return result.toJson();
	}

	@Override
	public String login(String account, String password) {
		Result result = new Result();
		if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
			result.setCode(100);
			result.setErr("参数不能为空");
			return result.toJson();
		}
    	
    	account = account.trim();
    	password = password.trim();
    	
    	// 检查用户名和密码是否正确
    	User user = userService.checkLogin(account, password);
    	if(user == null){
    		result.setCode(104);
			result.setErr("错误的账号或者密码");
			return result.toJson();
    	}
    	
		result.setResult(user); //将返回的对象塞到result里面，它会自动被转换成JSON送给客户端
		return result.toJson();
	}

}
