package com.magic.app.zjtv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.app.zjtv.model.Version;
import com.magic.app.zjtv.model.VersionChanges;
import com.magic.app.zjtv.services.VersionService;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.JsonUtils;
import com.magic.core.annotation.Menu;

@Controller
@RequestMapping("/version")
@Menu(id = "versionId", label = "客户端版本中心", icon = Menu.ICON_EDIT, serialNumber = 3)
@com.magic.core.annotation.Permission("P_VERSION_MGN")
public class VersionController {
	
	@Autowired
	VersionService versionService;
    
    @RequestMapping
    public String index(){
        return "index";
    }

    @RequestMapping("/versioninfo")
    @Menu(id = "versioninfo", label = "版本管理", serialNumber = 1)
    @com.magic.core.annotation.Permission("P_VERSION_INFO")
    public String versioninfo(){
        return "versioninfo";
    }
    
    @RequestMapping("/versionchangesinfo")
    @Menu(id = "versionchangesinfo", label = "版本改变内容管理", serialNumber = 2)
    @com.magic.core.annotation.Permission("P_VERSIONCHANGES_INFO")
    public String versionchangesinfo(){
    	return "versionchangesinfo";
    }
    
    
	
    @RequestMapping("/loadVersions")
    @ResponseBody
    public String loadVersions(){
    	List<Version> versionList = versionService.getVersions();
    	return JsonUtils.aaData(versionList);
    }
	
    @RequestMapping("/loadVersionChanges")
    @ResponseBody
    public String loadVersionChanges(){
    	List<VersionChanges> versionChanges = versionService.getVersionChanges();
    	return JsonUtils.aaData(versionChanges);
    }

    @RequestMapping("/editVersion/{id}")
    public String editVersion(@PathVariable Integer id, @ModelAttribute Version version, HttpServletRequest request){
    	
    	Version versionTmp = versionService.findVersionById(id);
        BeanUtils.copyProperties(versionTmp, version);
        
        return "version_edit";
    }
    

    @RequestMapping("/editVersionChanges/{id}")
    public String editVersionChanges(@PathVariable Integer id, @ModelAttribute VersionChanges versionChanges, HttpServletRequest request){
    	versionChanges.setId(null);
        if (id != 0){
        	VersionChanges versionChangesTmp = versionService.findVersionChangesById(id);
            BeanUtils.copyProperties(versionChangesTmp, versionChanges);
        }
        
        return "versionChanges_edit";
    }
    
    
    @RequestMapping("/saveOrUpdateVersion")
    public String saveOrUpdateVersion(@ModelAttribute Version version){
        
    	version = versionService.saveOrUpdateVersion(version);
    	return "redirect:/version/editVersion/"+version.getId();
    }

    @RequestMapping("/saveOrUpdateVersionChanges")
    public String saveOrUpdateVersionChanges(@ModelAttribute VersionChanges versionChanges){
        Boolean isContinue = versionChanges.getContinue();
        versionService.saveOrUpdateVersionChanges(versionChanges);
        if (isContinue){
            return "redirect:/version/editVersionChanges/0";
        }
        return "redirect:/version/versionchangesinfo";
    }


    @RequestMapping("/deleteVersionChanges")
    @ResponseBody
    public String deleteVersionChanges(@RequestParam Integer id){
    	versionService.deleteVersionChanges(id);
        return "success";
    }
}
