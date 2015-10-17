package com.magic.app.zjtv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.app.zjtv.model.Address;
import com.magic.app.zjtv.model.Comment;
import com.magic.app.zjtv.model.Order;
import com.magic.app.zjtv.model.Packages;
import com.magic.app.zjtv.model.User;
import com.magic.app.zjtv.model.Worker;
import com.magic.app.zjtv.services.OrderService;
import com.magic.app.zjtv.services.PackageService;
import com.magic.app.zjtv.services.UserService;
import com.magic.app.zjtv.services.WorkerService;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.JsonUtils;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.Permission;
import com.magic.core.annotation.layout.LayoutMenuHorizontal;
import com.magic.core.annotation.layout.LayoutNone;

@Controller
@RequestMapping("/order")
@Menu(id = "orderId", label = "订单管理", icon = Menu.ICON_EDIT, serialNumber = 4)
@com.magic.core.annotation.Permission("P_ORDER_MGN")
public class OrderController {
	Log log = LogFactory.getLog(getClass());
	/**
	 * 订单管理，包括订单查看，修改
	 */
  
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	WorkerService workerService;
	
	@Autowired
	PackageService packageService;

    @RequestMapping
    public String index(){
        return "index";
    }


	@RequestMapping("/orders")
    @Menu(id = "orders", label = "订单列表", icon = Menu.ICON_EDIT, serialNumber = 1)
    @Permission("P_ORDER_ORDERS")
    @LayoutMenuHorizontal
    public String orders(){
        return "orders";
    }
    
    
	@RequestMapping("/loadOrder")
    @ResponseBody
    public String loadOrder(){
    	List<Order> list = orderService.findAllOrders();
    	return JsonUtils.aaData(list);
    }
    
    

    @RequestMapping("/showOrderDetail/{id}")
    @LayoutNone
    public String showOrderDetail(@PathVariable Integer id, HttpServletRequest request){

    	Order order = orderService.findOrderById(id);
    	User user = userService.findUserByUserId(order.getUserId());
    	Worker worker = workerService.findWorkerByWorkerId(order.getWorkerId());
    	//Packages packages = packageService.findPackagesByPackageId(order.getPackageServiceId());
    	Address a = orderService.findServiceAddressById(order.getServiceAddressId());
    	log.debug(a.toString());
    	if(order.getIsComment()==1){
    		Comment comment = orderService.findCommentByOrderId(order.getId());
    		request.setAttribute("comment", comment);
    	}
        request.setAttribute("order", order);
        request.setAttribute("user", user);
        request.setAttribute("worker", worker);
        //request.setAttribute("packages", packages);
        request.setAttribute("a", a);
        return "order_detail";
    }
    

    @RequestMapping("/editOrder/{id}")
    public String editOrder(@PathVariable Integer id, @ModelAttribute Order order, HttpServletRequest request){
    	order.setId(null);
        if (id != 0){
        	Order tmp = orderService.findOrderById(id);
            BeanUtils.copyProperties(tmp, order);
        }

        List<Worker> list = workerService.findAllWorkers();
        Worker worker = new Worker();
        worker.setId(0);
        worker.setName("请选择");
        if(list==null){
        	list = new ArrayList<Worker>();
        }
        list.add(0, worker);
        request.setAttribute("workerList", list);
        
        
        
        return "order_edit";
    }
    

	@RequestMapping("/saveOrUpdateOrder")
	public String saveOrUpdateOrder(
			@ModelAttribute("order") Order order,
			HttpSession session) {
		order = orderService.saveOrUpdateOrder(order);
		
		return "redirect:/order/orders";
	}
    
    
    
    
    
    
    
    
    
    
    
}
