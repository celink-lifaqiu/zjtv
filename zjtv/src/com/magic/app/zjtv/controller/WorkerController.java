package com.magic.app.zjtv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.app.zjtv.model.Worker;
import com.magic.app.zjtv.model.WorkerServiceTime;
import com.magic.app.zjtv.model.WorkerServiceTimeComp;
import com.magic.app.zjtv.services.WorkerService;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.JsonUtils;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.Permission;
import com.magic.core.annotation.layout.LayoutMenuHorizontal;

@Controller
@RequestMapping("/worker")
@Menu(id = "workerId", label = "员工管理", icon = Menu.ICON_EDIT, serialNumber = 2)
@com.magic.core.annotation.Permission("P_WORKER_MGN")
public class WorkerController {
	/**
	 * 员工管理，包括员工可服务时间，评论
	 */
	@Autowired
	WorkerService workerService;

    @RequestMapping
    public String index(){
        return "index";
    }


	@RequestMapping("/worker")
    @Menu(id = "worker", label = "员工列表", icon = Menu.ICON_EDIT, serialNumber = 1)
    @Permission("P_WORKER_WORKER")
    @LayoutMenuHorizontal
    public String worker(){
        return "worker";
    }
    
	@RequestMapping("/loadWorker")
    @ResponseBody
    public String loadWorker(){
    	List<Worker> list = workerService.findAllWorkers();
    	return JsonUtils.aaData(list);
    }
    

    @RequestMapping("/editWorker/{id}")
    public String editWorker(@PathVariable Integer id, @ModelAttribute Worker worker, HttpServletRequest request){
    	worker.setId(null);
        if (id != 0){
        	Worker tmp = workerService.findWorkerByWorkerId(id);
            BeanUtils.copyProperties(tmp, worker);
        }else{
        	worker.setGender(0);
        }
        return "worker_edit";
    }
    
	

	@RequestMapping("/saveOrUpdateWorker")
	public String saveOrUpdateWorker(
			@ModelAttribute("worker") Worker worker,
			HttpSession session) {
		Boolean isContinue = worker.getContinue();
		worker = workerService.saveOrUpdateWorker(worker);
		if (isContinue){
			return "redirect:/worker/editWorker/0";
		}
		return "redirect:/worker/worker";
	}

    @RequestMapping("/deleteWorker/{id}")
    @ResponseBody
    public String deleteWorker(@PathVariable Integer id){
    	workerService.deleteWorker(id);
        return "success";
    }
    
    
    @RequestMapping("/workerservicetime")
    @Menu(id = "workerservicetime", label = "员工可服务时间列表", icon = Menu.ICON_EDIT, serialNumber = 2)
    @Permission("P_WORKER_WORKERSERVICETIME")
    @LayoutMenuHorizontal
    public String workerservicetime(){
    	return "workerservicetime";
    }
    
    @RequestMapping("/loadWorkerServiceTime")
    @ResponseBody
    public String loadWorkerServiceTime(){
    	List<WorkerServiceTimeComp> list = workerService.findAllWorkerServiceTimeComp();
    	return JsonUtils.aaData(list);
    }
    
    
    @RequestMapping("/editWorkerServiceTime/{id}")
    public String editWorkerServiceTime(@PathVariable Integer id, @ModelAttribute WorkerServiceTime workerServiceTime, HttpServletRequest request){
    	workerServiceTime.setId(null);
    	if (id != 0){
    		WorkerServiceTime tmp = workerService.findWorkerServiceTimeById(id);
    		BeanUtils.copyProperties(tmp, workerServiceTime);
    	}
    	List<Worker> list = workerService.findAllWorkers();
    	request.setAttribute("workerList", list);
    	return "workerServiceTime_edit";
    }
    
    
    
    @RequestMapping("/saveOrUpdateWorkerServiceTime")
    public String saveOrUpdateWorkerServiceTime(
    		@ModelAttribute("workerServiceTime") WorkerServiceTime workerServiceTime,
    		HttpSession session) {
    	Boolean isContinue = workerServiceTime.getContinue();
    	workerServiceTime = workerService.saveOrUpdateWorkerServiceTime(workerServiceTime);
    	if (isContinue){
    		return "redirect:/worker/editWorkerServiceTime/0";
    	}
    	return "redirect:/worker/workerservicetime";
    }
    
    @RequestMapping("/deleteWorkerServiceTime/{id}")
    @ResponseBody
    public String deleteWorkerServiceTime(@PathVariable Integer id){
    	workerService.deleteWorkerServiceTime(id);
    	return "success";
    }
    
    
}
