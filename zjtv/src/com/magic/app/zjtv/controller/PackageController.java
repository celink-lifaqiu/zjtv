package com.magic.app.zjtv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.app.zjtv.model.PackageType;
import com.magic.app.zjtv.model.Packages;
import com.magic.app.zjtv.model.PackagesComp;
import com.magic.app.zjtv.model.User;
import com.magic.app.zjtv.services.PackageService;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.JsonUtils;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.Permission;
import com.magic.core.annotation.layout.LayoutMenuHorizontal;

@Controller
@RequestMapping("/package")
@Menu(id = "packageId", label = "服务套餐管理", icon = Menu.ICON_EDIT, serialNumber = 5)
@com.magic.core.annotation.Permission("P_PACKAGE_MGN")
public class PackageController {
  
	@Autowired
	PackageService packageService;

    @RequestMapping
    public String index(){
        return "index";
    }

	@RequestMapping("/packageType")
    @Menu(id = "packageType", label = "服务套餐类型列表", icon = Menu.ICON_EDIT, serialNumber = 1)
    @Permission("P_PACKAGE_PACKAGETYPE")
    @LayoutMenuHorizontal
    public String packageType(){
        return "package_type";
    }
	
	

	@RequestMapping("/packages")
    @Menu(id = "packages", label = "服务套餐列表", icon = Menu.ICON_EDIT, serialNumber = 2)
    @Permission("P_PACKAGE_PACKAGE")
    @LayoutMenuHorizontal
    public String packages(){
        return "packages";
    }
	

    @RequestMapping("/loadPackageType")
    @ResponseBody
    public String loadPackageType(){
    	List<PackageType> list = packageService.findAllPackageTypes();
    	return JsonUtils.aaData(list);
    }
    

    @RequestMapping("/editPackageType/{id}")
    public String editPackageType(@PathVariable Integer id, @ModelAttribute PackageType packageType, HttpServletRequest request){
    	packageType.setId(null);
        if (id != 0){
        	PackageType tmp = packageService.findPackageTypeByPackageTypeId(id);
            BeanUtils.copyProperties(tmp, packageType);
        }
        return "packageType_edit";
    }
    
	

	@RequestMapping("/saveOrUpdatePackageType")
	public String saveOrUpdatePackageType(
			@ModelAttribute("packageType") PackageType packageType,
			HttpSession session) {
		Boolean isContinue = packageType.getContinue();
		packageType = packageService.saveOrUpdatePackageType(packageType);
		if (isContinue){
			return "redirect:/package/editPackageType/0";
		}
		return "redirect:/package/packageType";
	}

    @RequestMapping("/deletePackageType/{id}")
    @ResponseBody
    public String deletePackageType(@PathVariable Integer id){
    	packageService.deletePackageType(id);
        return "success";
    }
    
    
    
    @RequestMapping("/loadPackages")
    @ResponseBody
    public String loadPackages(){
    	List<PackagesComp> list = packageService.findAllPackagesComp();
    	return JsonUtils.aaData(list);
    }
    
    
    @RequestMapping("/editPackages/{id}")
    public String editPackages(@PathVariable Integer id, @ModelAttribute Packages packages, HttpServletRequest request){
    	packages.setId(null);
    	if (id != 0){
    		Packages tmp = packageService.findPackagesByPackageId(id);
    		BeanUtils.copyProperties(tmp, packages);
    	}else{
    		packages.setPackageServicePrice((float) 0.0);
    	}
    	
    	List<PackageType> list = packageService.findAllPackageTypes();
    	request.setAttribute("packageTypeList", list);
    	return "packages_edit";
    }
    
    
    
    @RequestMapping("/saveOrUpdatePackages")
    public String saveOrUpdatePackages(
    		@ModelAttribute("packages") Packages packages,
    		HttpSession session) {
    	Boolean isContinue = packages.getContinue();
    	packages = packageService.saveOrUpdatePackages(packages);
    	if (isContinue){
    		return "redirect:/package/editPackages/0";
    	}
    	return "redirect:/package/packages";
    }
    
    @RequestMapping("/deletePackages/{id}")
    @ResponseBody
    public String deletePackages(@PathVariable Integer id){
    	packageService.deletePackages(id);
    	return "success";
    }
    
    
}
