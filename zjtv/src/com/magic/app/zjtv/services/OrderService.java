package com.magic.app.zjtv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.dao.AddressDAO;
import com.magic.app.zjtv.dao.CommentDAO;
import com.magic.app.zjtv.dao.OrderDAO;
import com.magic.app.zjtv.entities.AddressEntity;
import com.magic.app.zjtv.entities.CommentEntity;
import com.magic.app.zjtv.entities.OrderEntity;
import com.magic.app.zjtv.model.Address;
import com.magic.app.zjtv.model.Comment;
import com.magic.app.zjtv.model.Order;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.DateUtils;

@Transactional
@Service  //加入这个注解后，Spring会自动加入这个类到Spring上下文中，可被其他类注入
public class OrderService {
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	AddressDAO addressDAO;
	@Autowired
	CommentDAO commentDAO;

	public List<Order> findAllOrders() {
		List<OrderEntity> entities =  orderDAO.getAllOrders();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, Order.class);
		}
		return null;
	}

	public Order findOrderById(Integer id) {
		OrderEntity entity = orderDAO.findOne(id);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Order.class);
	}

	public Address findServiceAddressById(Integer serviceAddressId) {
		AddressEntity entity = addressDAO.findOne(serviceAddressId);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Address.class);
	}

	public Comment findCommentByOrderId(Integer id) {
		CommentEntity entity = commentDAO.findOne(id);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Comment.class);
	}

	public Order saveOrUpdateOrder(Order order) {
		OrderEntity entity = orderDAO.findOne(order.getId());
		entity.setOrderName(order.getOrderName());
		entity.setSumPrice(order.getSumPrice());
		entity.setState(2);
		entity.setUpdateTime(DateUtils.getDateTimestamp());
		orderDAO.save(entity);
		return null;
	}


}
