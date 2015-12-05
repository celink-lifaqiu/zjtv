package com.magic.app.zjtv.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.app.zjtv.common.Messages;
import com.magic.app.zjtv.model.Address;
import com.magic.app.zjtv.model.Comment;
import com.magic.app.zjtv.model.Order;
import com.magic.app.zjtv.model.OrderPackageService;
import com.magic.app.zjtv.model.Packages;
import com.magic.app.zjtv.model.User;
import com.magic.app.zjtv.model.UserCoupon;
import com.magic.app.zjtv.services.UserService;
import com.magic.commons.helper.HttpResultHelper;
import com.magic.commons.models.HttpDataResult;
import com.magic.commons.models.HttpListResult;
import com.magic.commons.service.CommonService;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.DateUtils;
import com.magic.commons.utils.JsonUtils;
import com.magic.commons.utils.PropertiesUtils;
import com.magic.commons.utils.ValidationUtils;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.NoAuth;
import com.magic.core.annotation.Permission;
import com.magic.core.annotation.layout.LayoutMenuHorizontal;
import com.magic.core.annotation.layout.LayoutNone;

@Controller
@RequestMapping("/user")
@Menu(id = "userId", label = "用户中心", icon = Menu.ICON_EDIT, serialNumber = 0)
@com.magic.core.annotation.Permission("P_USER_MGN")
public class UserController {
    Log log = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;

	@Autowired
	CommonService commonService;
	
	
    @RequestMapping
    @LayoutMenuHorizontal
    public String index(){
        return "index";
    }
    

	@RequestMapping("/user")
    @Menu(id = "user", label = "用户列表", icon = Menu.ICON_EDIT, serialNumber = 1)
    @Permission("P_USER_USER")
    @LayoutMenuHorizontal
    public String user(){
        return "user";
    }
	
	

	
    @RequestMapping("/loadUsers")
    @ResponseBody
    public String loadUsers(){
    	List<User> list = userService.findAllUsers();
    	return JsonUtils.aaData(list);
    }
    
	
	
    @RequestMapping("/updatePassword/{userId}")
    @LayoutNone
    public String updatePassword(HttpServletRequest request, @PathVariable int userId){
    	User  user= userService.findUserByUserId(userId);
    	request.setAttribute("user", user);
    	return "updatePassword";
    }
    

    @RequestMapping("/updateUserPassword")
    @ResponseBody
    public String updateUserPassword(@RequestParam int userId, @RequestParam String password){
    	userService.updateUserPassword(userId, password);
        return "success";
    }
    

    @RequestMapping("/editUser/{userId}")
    public String editUser(@PathVariable int userId, @ModelAttribute User user, HttpServletRequest request){
    	user.setId(null);
        if (userId != 0){
            User userTmp = userService.findUserByUserId(userId);
            BeanUtils.copyProperties(userTmp, user);
        }
        return "user_edit";
    }
    
    
	

	@RequestMapping("/saveOrUpdateUser")
	public String saveOrUpdateUser(
			@ModelAttribute("user") User user,
			HttpSession session) {
		user = userService.saveOrUpdatUser(user);
		return "redirect:/user/editUser/"+user.getId();
	}
	

    
    @RequestMapping("/register")
	@ResponseBody
    @NoAuth
	public HttpDataResult register(HttpServletRequest request,@RequestParam String account,@RequestParam String password, HttpDataResult result){
        log.debug("This is UserController.register.");
    	if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
//			这里的ID是必选项目，枚举类Messages下面包含了预定义好的各种错误信息，具体信息定义在messages.properties文件中
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	account = account.trim();
    	password = password.trim();
    	//String nickName = nickName.trim();
    	
    	if(!ValidationUtils.validateMoblie(account)){
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERNAME_NOT_LEGAL);
    	}
    	
    	if(password.trim().length()<6 || password.trim().length()>16){
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PASSWORD_ERR);
    	}
    	
    	// 根据用户名查找用户，如果存在，看其密码是否为null，如果是则表示用户从划一划游戏那里来的
    	User user = userService.findUserByAccount(account);
    	if(user!=null){
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERNAME_EXISTS);
    	}else{
        	user = userService.saveUser(account, password);
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(user != null){
    		map.put("state", true);
    	}else{
    		map.put("state", false);
    	}
		result.setData(map); //将返回的对象塞到result里面，它会自动被转换成JSON送给客户端
		return result;
	}
    
    @RequestMapping("/login")
    @ResponseBody
    @NoAuth
	public HttpDataResult login(HttpServletRequest request,@RequestParam String account,@RequestParam String password, HttpDataResult result){
    	if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
//			这里的ID是必选项目，枚举类Messages下面包含了预定义好的各种错误信息，具体信息定义在messages.properties文件中
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	
    	account = account.trim();
    	password = password.trim();
    	
    	// 检查用户名和密码是否正确
    	User user = userService.checkLogin(account, password);
    	if(user == null){
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_LOGIN_ERR);    		
    	}
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	returnMap.put("userId", user.getId());
    	returnMap.put("account", user.getAccount());
    	returnMap.put("nickName", user.getNickName());
    	returnMap.put("icon", user.getIcon());
    	returnMap.put("email", user.getEmail()==null?"":user.getEmail());
    	returnMap.put("address", user.getAddress()==null?"":user.getAddress());
    	returnMap.put("birthday", user.getBirthday());
    	returnMap.put("pwdAnswer", user.getPwdAnswer());
		result.setData(returnMap); //将返回的对象塞到result里面，它会自动被转换成JSON送给客户端
		return result;
	}

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    @NoAuth
	public HttpDataResult updateUserInfo(HttpServletRequest request,@RequestParam String string, HttpDataResult result){
    	JSONObject parameter = null;
    	try {
    		parameter = JSONObject.fromObject(string);
		} catch (Exception e) {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	if (!parameter.containsKey("userId")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.USERID_NOT_EXSIT_ERR);
		}
    	int userId = parameter.getInt("userId");
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	
    	if (parameter.containsKey("birthday")) {
    		user.setBirthday(parameter.getLong("birthday"));
		}
    	if (parameter.containsKey("icon")) {
    		user.setIcon(parameter.getString("icon"));
    	}
    	if (parameter.containsKey("nickName")) {
    		user.setNickName(parameter.getString("nickName"));
    	}
    	if (parameter.containsKey("address")) {
    		user.setAddress(parameter.getString("address"));
    	}
    	
    	if (parameter.containsKey("pwdAnswer")) {
    		user.setPwdAnswer(parameter.getLong("pwdAnswer"));
    	}
		user = userService.updateUser(user);  
		if (user == null) {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_OPERATION_ERR);
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
    	returnMap.put("userId", user.getId());
    	returnMap.put("account", user.getAccount());
    	returnMap.put("nickName", user.getNickName());
    	returnMap.put("icon", user.getIcon());
    	returnMap.put("email", user.getEmail()==null?"":user.getEmail());
    	returnMap.put("address", user.getAddress()==null?"":user.getAddress());
    	returnMap.put("birthday", user.getBirthday());
    	returnMap.put("pwdAnswer", user.getPwdAnswer());
		result.setData(returnMap); //将返回的对象塞到result里面，它会自动被转换成JSON送给客户端
		return result;
	}
    
    
    @RequestMapping("/updatePassword")
    @ResponseBody
    @NoAuth
	public HttpDataResult updatePassword(HttpServletRequest request,@RequestParam String string, HttpDataResult result){
    	JSONObject parameter = null;
    	try {
    		parameter = JSONObject.fromObject(string);
		} catch (Exception e) {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	if (!parameter.containsKey("userId") || !parameter.containsKey("pwdAnswer") || !parameter.containsKey("newPassword")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	int userId = parameter.getInt("userId");
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	
    	if (!(DateUtils.transferLongToDate("yyyy-MM-dd", user.getPwdAnswer()).equals(DateUtils.transferLongToDate("yyyy-MM-dd", parameter.getLong("pwdAnswer"))))) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ANSWER_ERR);
		}
    	String newPassword = parameter.getString("newPassword");
    	if(newPassword.trim().length()<6 || newPassword.trim().length()>16){
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_NEWPASSWORD_ERR);
    	}
    	
    	userService.updateUserPassword(userId, newPassword);
    	
    	Map<Object, Object> returnMap = new HashMap<Object, Object>();
		returnMap.put("state",true);
		result.setData(returnMap); //将返回的对象塞到result里面，它会自动被转换成JSON送给客户端
		return result;
	}
    
    
    @RequestMapping("/addUserServiceAddress")
    @ResponseBody
    @NoAuth
	public HttpDataResult addUserServiceAddress(HttpServletRequest request,@RequestParam String string,  HttpDataResult result){
    	JSONObject parameter = null;
    	try {
    		parameter = JSONObject.fromObject(string);
		} catch (Exception e) {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	if (!parameter.containsKey("userId")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	if (!parameter.containsKey("reservation")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	if (!parameter.containsKey("phone")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	if (!parameter.containsKey("districtInformation")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	if (!parameter.containsKey("address")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	if (!parameter.containsKey("idDefaultServiceAddress")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	int userId = parameter.getInt("userId");
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	int idDefaultServiceAddress = 0;
    	try {
    		idDefaultServiceAddress = parameter.getInt("idDefaultServiceAddress");
		} catch (Exception e) {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	String reservation = parameter.getString("reservation");
    	String phone = parameter.getString("phone");
    	String districtInformation = parameter.getString("districtInformation");
    	String addressStr = parameter.getString("address");
    	
    	if (idDefaultServiceAddress == 1) {
			// 修改其他地址为idDefaultServiceAddress=0
    		this.userService.setAddressIdDefaultServiceAddressToZero(userId);
		}
    	
    	Address address = new Address();
    	address.setAddress(addressStr);
    	address.setDistrictInformation(districtInformation);
    	address.setIdDefaultServiceAddress(idDefaultServiceAddress);
    	address.setPhone(phone);
    	address.setReservation(reservation);
    	address.setUserId(userId);
    	address = this.userService.addUserAddress(address);
    	if (address == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_OPERATION_ERR);
		}
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	returnMap.put("reservation", address.getReservation());
    	returnMap.put("serviceAddressId", address.getId());
    	returnMap.put("districtInformation", address.getDistrictInformation());
    	returnMap.put("idDefaultServiceAddress", address.getIdDefaultServiceAddress());
    	returnMap.put("address", address.getAddress());
    	returnMap.put("phone", address.getPhone());
    	result.setData(returnMap);
		return result;
	}
    
    @RequestMapping("/updateUserServiceAddress")
    @ResponseBody
    @NoAuth
    public HttpDataResult updateUserServiceAddress(HttpServletRequest request,@RequestParam String string,  HttpDataResult result){
    	JSONObject parameter = null;
    	try {
    		parameter = JSONObject.fromObject(string);
    	} catch (Exception e) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	if (!parameter.containsKey("serviceAddressId") || !parameter.containsKey("userId")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	int userId = parameter.getInt("userId");
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	int serviceAddressId = parameter.getInt("serviceAddressId");
    	Address address = this.userService.findAddressById(serviceAddressId);
    	if (address == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ADDRESSID_NOT_EXIST_ERR);
    	}
    	if (userId != address.getUserId()) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_UNAUTHORIZEDOPERATION_ERR);
		}
    	try {
    		if (parameter.containsKey("idDefaultServiceAddress")) {
    			int idDefaultServiceAddress = parameter.getInt("idDefaultServiceAddress");
    			address.setIdDefaultServiceAddress(idDefaultServiceAddress);
    			if (idDefaultServiceAddress == 1) {
    	    		// 修改其他地址为idDefaultServiceAddress=0
    	    		this.userService.setAddressIdDefaultServiceAddressToZero(userId);
    	    	}
			}
    	} catch (Exception e) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    	}
    	
    	if (parameter.containsKey("reservation")) {
			address.setReservation(parameter.getString("reservation"));
		}
    	if (parameter.containsKey("phone")) {
    		address.setPhone(parameter.getString("phone"));
    	}
    	if (parameter.containsKey("districtInformation")) {
    		address.setDistrictInformation(parameter.getString("districtInformation"));
    	}
    	if (parameter.containsKey("address")) {
    		address.setAddress(parameter.getString("address"));
    	}
    	
    	address = this.userService.addUserAddress(address);
    	if (address == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_OPERATION_ERR);
    	}
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	returnMap.put("reservation", address.getReservation());
    	returnMap.put("serviceAddressId", address.getId());
    	returnMap.put("districtInformation", address.getDistrictInformation());
    	returnMap.put("idDefaultServiceAddress", address.getIdDefaultServiceAddress());
    	returnMap.put("address", address.getAddress());
    	returnMap.put("phone", address.getPhone());
    	result.setData(returnMap);
    	return result;
    }
    
    
    @RequestMapping("/getUserServiceAddress")
    @ResponseBody
    @NoAuth
    public HttpListResult getUserServiceAddress(HttpServletRequest request,@RequestParam int userId, HttpListResult result){
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpListResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	List<Address> list = this.userService.findUserServiceAddressByUserId(userId);
    	List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
    	if (list != null && list.size() > 0) {
			for (Address address : list) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("reservation", address.getReservation());
		    	returnMap.put("serviceAddressId", address.getId());
		    	returnMap.put("districtInformation", address.getDistrictInformation());
		    	returnMap.put("idDefaultServiceAddress", address.getIdDefaultServiceAddress());
		    	returnMap.put("address", address.getAddress());
		    	returnMap.put("phone", address.getPhone());
		    	dataList.add(returnMap);
			}
		}
    	result.setDataList(dataList);
    	return result;
    }


    
    @RequestMapping("/getUserCoupon")
    @ResponseBody
    @NoAuth
    public HttpListResult getUserCoupon(HttpServletRequest request,@RequestParam int userId,@RequestParam int isUsed, HttpListResult result){
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpListResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	if (isUsed != 0) {
			isUsed = 1;
		}
    	List<UserCoupon> list = this.userService.findUserCouponByUserIdAndIsUsed(userId, isUsed);
    	List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
    	if (list != null && list.size() > 0) {
			for (UserCoupon userCoupon : list) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("userCouponId", userCoupon.getId());
				returnMap.put("isUsed", userCoupon.getIsUsed());
		    	returnMap.put("couponName", userCoupon.getCoupon().getCouponName());
		    	returnMap.put("couponIcon", PropertiesUtils.getInstance("config").getValue("com.magic.zjtv.host.images") + userCoupon.getCoupon().getCouponIcon());
		    	returnMap.put("updateTime", userCoupon.getUpdateTimeStr());
		    	returnMap.put("createDate", userCoupon.getCreateDateStr());
		    	dataList.add(returnMap);
			}
		}
    	result.setDataList(dataList);
    	return result;
    }
    
    @RequestMapping("/getServicePackage")
    @ResponseBody
    @NoAuth
    public HttpListResult getServicePackage(HttpServletRequest request,@RequestParam int packageType, HttpListResult result){
    	if (packageType != 1 && packageType != 2 && packageType != 3 && packageType != 4) {
    		return (HttpListResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PACKAGETYPE_ERR);
		}
    	List<Packages> list = this.userService.findPackagesByType(packageType);
    	List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
    	if (list != null && list.size() > 0) {
			for (Packages packages : list) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("packageServiceName", packages.getPackageServiceName());
				returnMap.put("packageServicePrice", packages.getPackageServicePrice());
				returnMap.put("packageServiceIcon", packages.getPackageServiceIcon());
		    	returnMap.put("packageServiceId", packages.getId());
		    	dataList.add(returnMap);
			}
		}
    	result.setDataList(dataList);
    	return result;
    }
    
    
    @RequestMapping("/getServicePackageByPackageServiceId")
    @ResponseBody
    @NoAuth
    public HttpDataResult getServicePackageByPackageServiceId(HttpServletRequest request,@RequestParam int packageServiceId,  HttpDataResult result){
    	Packages packages = this.userService.findPackageByPackageServiceId(packageServiceId);
    	if (packages == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PACKAGE_NOT_EXIST_ERR);
		}
    	Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("packageServiceName", packages.getPackageServiceName());
		returnMap.put("packageServicePrice", packages.getPackageServicePrice());
		returnMap.put("packageServiceIcon", packages.getPackageServiceIcon());
    	returnMap.put("packageServiceId", packages.getId());
    	returnMap.put("packageServiceDesc", packages.getPackageServiceDesc());
    	result.setData(returnMap);
    	return result;
    }
    
    @RequestMapping("/submitComment")
    @ResponseBody
    @NoAuth
    public HttpDataResult submitComment(HttpServletRequest request,@RequestParam int userId,@RequestParam int orderId,@RequestParam int star,@RequestParam String content,  HttpDataResult result){
    	if (star != 1 && star != 2 && star != 3 && star != 4 && star != 5) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_START_ERR);
		}
    	Comment comment = this.userService.findCommentByUserIdAndOrderId(userId, orderId);
    	if (comment != null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_COMMENT_EXIST_ERR);
		}
    	if (content == null || "".equals(content.trim())) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_COMMENT_CONTENT_NULL_EXISTS);
		}
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	Order order = this.userService.findOrderByOrderId(orderId);
    	if (order == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ORDER_NOT_EXIST);
		}
    	if (userId != order.getUserId()) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ORDER_NOT_EXIST);
		}
    	if (order.getState() != 3) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ORDER_NOT_END);
		}
    	comment = new Comment();
    	comment.setCommentTime(DateUtils.getDateTimestamp());
    	comment.setContent(content);
    	comment.setOrderId(orderId);
    	comment.setStar(star);
    	comment.setUserId(userId);
    	String msg = this.userService.addComment(comment);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	if (msg != null) {
    		returnMap.put("state", true);
			if (star == 5) {
				returnMap.put("msg", msg);
			}
		}else {
			returnMap.put("state", false);
		}
    	result.setData(returnMap);
    	return result;
    }
    
    @RequestMapping("/getAverageCommentByPackageType")
    @ResponseBody
    @NoAuth
    public HttpDataResult getAverageCommentByPackageType(HttpServletRequest request,@RequestParam int packageType,  HttpDataResult result){
    	if (packageType != 1 && packageType != 2 && packageType != 3 && packageType != 4) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PACKAGETYPE_ERR);
		}
    	List<Comment> list = this.userService.findAllCommentsByPackageType(packageType);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	if (list != null && list.size() > 0) {
			int size = list.size();
			int sumStar = 0;
			for (Comment comment : list) {
				sumStar += comment.getStar();
			}
			float averageStar = Float.valueOf((sumStar/size)+"."+(sumStar%size));
			returnMap.put("averageStar", averageStar);
			returnMap.put("allCnt", size);
		}else {
			returnMap.put("averageStar", 0);
			returnMap.put("allCnt", 0);
		}
    	result.setData(returnMap);
    	return result;
    }
    
    
    @RequestMapping("/getCommentByPackageType")
    @ResponseBody
    @NoAuth
    public HttpListResult getCommentByPackageType(HttpServletRequest request,@RequestParam int packageType,@RequestParam int page,@RequestParam int pageSize, HttpListResult result){
    	if (packageType != 1 && packageType != 2 && packageType != 3 && packageType != 4) {
    		return (HttpListResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PACKAGETYPE_ERR);
		}
    	if (page < 1) {
			page = 1;
		}
    	if (pageSize < 1) {
			pageSize = 10;
		}
    	List<Comment> list = this.userService.getCommentByPackageType(packageType, page, pageSize);
    	List<Map<String, Object>> returnMap = new ArrayList<Map<String,Object>>();
    	if (list != null && list.size() > 0) {
			Map<String, Object> map = null;
    		for (Comment comment : list) {
				map = new HashMap<String, Object>();
				map.put("commentId", comment.getId());
				map.put("nickName", comment.getNickName());
				map.put("commentTime", comment.getCommentTimeStr());
				map.put("content", comment.getContent());
				map.put("star", comment.getStar());
				returnMap.add(map);
			}
		}
    	result.setDataList(returnMap);
    	return result;
    }
    
    @RequestMapping("/addOrder")
    @ResponseBody
    @NoAuth
    public HttpDataResult addOrder(HttpServletRequest request,@RequestParam String string,  HttpDataResult result){
    	JSONObject parameter = null;
    	Order order = new Order();
    	try {
    		parameter = JSONObject.fromObject(string);
		} catch (Exception e) {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	if (!parameter.containsKey("userId")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.USERID_NOT_EXSIT_ERR);
		}
    	int userId = parameter.getInt("userId");
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	order.setUserId(userId);
    	if (!parameter.containsKey("packageServiceIdJSONArray")) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PACKAGESERVICEID_NOT_EXISTS);
		}
    	int userCouponId = 0;
    	if (parameter.containsKey("userCouponId")) {
			try {
				userCouponId = parameter.getInt("userCouponId");
				UserCoupon userCoupon = this.userService.findUserCouponByUserCouponId(userCouponId);
				if (userCoupon == null) {
					return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERCOUPON_NOT_EXISTS);
				}else {
					if (userCoupon.getUserId() != userId) {
						return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERCOUPON_NOT_EXISTS);
					}
					if (userCoupon.getIsUsed() == 1) {
						return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_COUPON_USED);
					}
				}
			} catch (Exception e) {
				return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
			}
		}
    	order.setUserCouponId(userCouponId);
    	if (parameter.containsKey("additionalRequirements")) {
			order.setAdditionalRequirements(parameter.getString("additionalRequirements"));
		}
    	if (parameter.containsKey("serviceTime")) {
    		try {
    			String serviceTime = parameter.getString("serviceTime");
    			if (serviceTime == null || "".equals(serviceTime.trim())) {
    				return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_SERVICETIME_ERR);
				}else {
					order.setServiceTime(serviceTime);
				}
    		}catch (Exception e) {
    			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
			}
		}else {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_SERVICETIME_ERR);
		}
    	if (parameter.containsKey("serviceAddressId")) {
    		try {
    			int serviceAddressId = parameter.getInt("serviceAddressId");
    			Address address = this.userService.findAddressById(serviceAddressId);
    			if (address.getUserId() != userId) {
    				return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_SERVICEADDRESS_NOT_EXISTS);
				}else {
					order.setServiceAddressId(serviceAddressId);
				}
    		}catch (Exception e) {
    			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    		}
    	}else {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_SERVICEADDRESS_NOT_EXISTS);
    	}
    	float sumPrice = 0;
    	if (parameter.containsKey("sumPrice")) {
    		try {
    			sumPrice = (float)parameter.getDouble("sumPrice");
    		}catch (Exception e) {
    			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
    		}
		}
    	List<Integer> packageServiceIdList = new ArrayList<Integer>();
    	if (parameter.containsKey("packageServiceIdJSONArray")) {
			try {
				JSONArray packageServiceIdJSONArray = JSONArray.fromObject(parameter.getString("packageServiceIdJSONArray"));
				if (packageServiceIdJSONArray != null && packageServiceIdJSONArray.size() > 0) {
					for (int i = 0; i < packageServiceIdJSONArray.size(); i++) {
						packageServiceIdList.add(packageServiceIdJSONArray.getInt(i));
					}
				}else {
					return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_CHOICEPACKAGESERVICE);
				}
			} catch (Exception e) {
				return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
			}
		}else {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_CHOICEPACKAGESERVICE);
		}
    	int packageTypeId = 0;
    	List<Packages> packagesList = this.userService.findPackagesByIds(packageServiceIdList);
    	if (packagesList != null && packagesList.size() > 0) {
			if (packageServiceIdList.size() == packagesList.size()) {
				float tempPrice = 0;
				for (Packages packages : packagesList) {
					tempPrice += packages.getPackageServicePrice();
				}
				if (userCouponId != 0) {
					tempPrice = tempPrice - 5;
				}
				if (sumPrice != tempPrice) {
					return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_SUMPRAICE_ERR);
				}
				packageTypeId = packagesList.get(0).getPackageTypeId();
			}else {
				return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PACKAGE_PARAMETER_ERR);
			}
		}else {
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PACKAGE_PARAMETER_ERR);
		}
    	order.setSumPrice(sumPrice);
    	order = this.userService.addOrder(order, packageTypeId);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	if (order != null) {
			returnMap.put("state", true);
		}else {
			returnMap.put("state", false);
		}
    	result.setData(returnMap);
    	return result;
    }
    
    @RequestMapping("/updateOrderState")
    @ResponseBody
    @NoAuth
    public HttpDataResult updateOrderState(HttpServletRequest request,@RequestParam int userId,@RequestParam int orderId, HttpDataResult result){
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	Order order = this.userService.findOrderByOrderId(orderId);
    	if (order == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ORDER_NOT_EXIST);
		}
    	if (userId != order.getUserId()) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ORDER_NOT_EXIST);
		}
    	if (order.getState() == 3) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_END_ORDER);
		}
    	order.setState(3);
    	order = this.userService.updateOrderState(order);
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	if (order != null) {
			returnMap.put("state", true);
		}else {
			returnMap.put("state", false);
		}
    	result.setData(returnMap);
    	return result;
    }
    
    @RequestMapping("/getOrderDescByOrderId")
    @ResponseBody
    @NoAuth
    public HttpDataResult getOrderDescByOrderId(HttpServletRequest request,@RequestParam int orderId, HttpDataResult result){
    	Order order = this.userService.findOrderByOrderId(orderId);
    	if (order == null) {
    		return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_ORDER_NOT_EXIST);
    	}
    	List<OrderPackageService> orderPackageServiceList = this.userService.findOrderPackageServiceByOrderId(orderId);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	if (order != null) {
			map.put("orderId", order.getId());
			map.put("orderName", order.getOrderName());
			map.put("sumPrice", order.getSumPrice());
			map.put("state", order.getState());
			map.put("isComment", order.getIsComment());
			map.put("updateTime", order.getUpdateTimeStr());
			map.put("createDate", order.getCreateDateStr());
			map.put("isUsedCoupon", order.getUserCouponId() == 0 ? 0 : 1);
			List<Map<String, Object>> oList = new ArrayList<Map<String,Object>>();
			if (orderPackageServiceList != null) {
				for (OrderPackageService orderPackageService : orderPackageServiceList) {
					Map<String, Object> oMap = new HashMap<String, Object>();
					oMap.put("packageServiceName", orderPackageService.getPackages() == null ? "" : orderPackageService.getPackages().getPackageServiceName());
					oMap.put("packageServiceDesc", orderPackageService.getPackages() == null ? "" : orderPackageService.getPackages().getPackageServiceDesc());
					oMap.put("packageServicePrice", orderPackageService.getPackages() == null ? "" : orderPackageService.getPackages().getPackageServicePrice());
					oMap.put("packageServiceIcon", orderPackageService.getPackages() == null ? "" : orderPackageService.getPackages().getPackageServiceIcon());
					oMap.put("packageServiceId", orderPackageService.getPackages() == null ? 0 : orderPackageService.getPackages().getId());
					oList.add(oMap);
				}
			}
			map.put("packageService", oList);
			Address address = this.userService.findAddressById(order.getServiceAddressId());
			Map<String, Object> addressMap = new HashMap<String, Object>();
			if (address != null) {
				addressMap.put("address", address.getAddress());
				addressMap.put("districtInformation", address.getDistrictInformation());
				addressMap.put("phone", address.getPhone());
				addressMap.put("reservation", address.getReservation());
			}
			map.put("serviceAddress", addressMap);
    	}
    	result.setData(map);
    	return result;
    }
    
    @RequestMapping("/getUserOrderList")
    @ResponseBody
    @NoAuth
    public HttpListResult getUserOrderList(HttpServletRequest request,@RequestParam int userId,@RequestParam int state,@RequestParam int page,@RequestParam int pageSize, HttpListResult result){
    	User user = this.userService.findUserByUserId(userId);
    	if (user == null) {
    		return (HttpListResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_USERID_ERR);
		}
    	if (state != 1 && state != 0) {
    		return (HttpListResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
    	if (page < 1) {
			page = 1;
		}
    	if (pageSize < 1) {
			pageSize = 10;
		}
    	List<Order> list = this.userService.findOrders(userId, state, page, pageSize);
    	List<Map<String, Object>> returnMap = new ArrayList<Map<String,Object>>();
    	if (list != null && list.size() > 0) {
			Map<String, Object> map = null;
    		for (Order order : list) {
				map = new HashMap<String, Object>();
				map.put("orderId", order.getId());
				map.put("orderName", order.getOrderName());
				map.put("sumPrice", order.getSumPrice());
				map.put("state", order.getState());
				map.put("isComment", order.getIsComment());
				map.put("updateTime", order.getUpdateTimeStr());
				map.put("createDate", order.getCreateDateStr());
				returnMap.add(map);
			}
		}
    	result.setDataList(returnMap);
    	return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
