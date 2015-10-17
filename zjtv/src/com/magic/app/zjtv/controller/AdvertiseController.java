package com.magic.app.zjtv.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.app.zjtv.model.Advertise;
import com.magic.app.zjtv.services.AdvertiseService;
import com.magic.commons.models.HttpDataResult;
import com.magic.commons.models.HttpListResult;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.JsonUtils;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.NoAuth;
import com.magic.core.annotation.layout.LayoutNone;
import com.magic.qiniu.QiniuHelper;
import com.qiniu.api.auth.AuthException;

@Controller
@RequestMapping("/advertise")
@Menu(id = "advertiseId", label = "广告中心", icon = Menu.ICON_EDIT, serialNumber = 1)
@com.magic.core.annotation.Permission("P_ADVERTISE_MGN")
public class AdvertiseController {
    @Autowired
    AdvertiseService advertiseService;
    

    @RequestMapping
    public String index(){
        return "index";
    }

    @RequestMapping("/advertiseinfo")
    @Menu(id = "advertiseinfo", label = "广告管理", serialNumber = 1)
    @com.magic.core.annotation.Permission("P_ADVERTISE_INFO")
    public String advertiseinfo(){
        return "advertiseinfo";
    }
    
    

    @RequestMapping("/showAdvertiseDetail/{id}")
    @LayoutNone
    public String showAdvertiseDetail(@PathVariable Integer id, HttpServletRequest request){
    	Advertise advertise = advertiseService.findAdvertiseById(id);
        request.setAttribute("advertise", advertise);
        return "advertise_detail";
    }
    
	
    @RequestMapping("/loadadvertises")
    @ResponseBody
    public String loadadvertises(){
    	List<Advertise> advertiseList = advertiseService.getAdvertises();
    	return JsonUtils.aaData(advertiseList);
    }
	
    
    @RequestMapping("/previewImage")
	@LayoutNone
	public String previewImage(@RequestParam String hash,
			HttpServletRequest request) {
		String imageUrl = QiniuHelper.QINIU_IMAGE_HOST + hash;
		Advertise advertise = new Advertise();
		advertise.setImage(imageUrl);
		request.setAttribute("advertise", advertise);
		return "advertise_image";
	}
    

    @RequestMapping("/editAdvertise/{id}")
    public String editAdvertise(@PathVariable Integer id, @ModelAttribute Advertise advertise, HttpServletRequest request){
    	advertise.setId(null);
        if (id != 0){
        	Advertise advertiseTmp = advertiseService.findAdvertiseById(id);
            BeanUtils.copyProperties(advertiseTmp, advertise);
        }
        
        try {
			String uptoken = QiniuHelper.generateUpToken(QiniuHelper.QINIU_BUCKET_IMAGE);
			request.setAttribute("uptoken", uptoken);
		} catch (AuthException e) {
			e.printStackTrace();
		}
        
        return "advertise_edit";
    }
    
    @RequestMapping("/saveOrUpdateAdvertise")
    public String saveOrUpdateAdvertise(@ModelAttribute Advertise advertise){
        Boolean isContinue = advertise.getContinue();
        advertiseService.saveOrUpdateAdvertise(advertise);
        if (isContinue){
            return "redirect:/advertise/editAdvertise/0";
        }
        return "redirect:/advertise/advertiseinfo";
    }
    
    
    
    
	@RequestMapping("/mainPageAds")
    @ResponseBody
    @NoAuth
	public HttpListResult mainpageads(HttpListResult result){
		List<Advertise> advertiseList = advertiseService.getAdvertises("mainTop");
		List<Map<Object, Object>> dataList = new ArrayList<Map<Object, Object>>();
		Map<Object, Object> returnMap;
		if (advertiseList != null) {
			for (Advertise a : advertiseList) {
				returnMap = new HashMap<Object, Object>();
				returnMap.put("id", a.getId());
				returnMap.put("title", a.getTitle());
				returnMap.put("image", a.getImage());
				dataList.add(returnMap);
			}
		}
		result.setDataList(dataList);
		return result;
	}
	

    @RequestMapping("/marquee")
    @ResponseBody
    @NoAuth
    public HttpDataResult marquee(HttpDataResult result){

        return result;
    }
}
