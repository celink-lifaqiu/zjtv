package com.magic.app.zjtv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.dao.AddressDAO;
import com.magic.app.zjtv.dao.CommentDAO;
import com.magic.app.zjtv.dao.CouponDAO;
import com.magic.app.zjtv.dao.OrderDAO;
import com.magic.app.zjtv.dao.OrderPackageServiceDAO;
import com.magic.app.zjtv.dao.PackageDAO;
import com.magic.app.zjtv.dao.UserCouponDAO;
import com.magic.app.zjtv.dao.UserDAO;
import com.magic.app.zjtv.entities.AddressEntity;
import com.magic.app.zjtv.entities.CommentEntity;
import com.magic.app.zjtv.entities.CouponEntity;
import com.magic.app.zjtv.entities.OrderEntity;
import com.magic.app.zjtv.entities.OrderPackageServiceEntity;
import com.magic.app.zjtv.entities.PackagesEntity;
import com.magic.app.zjtv.entities.UserCouponEntity;
import com.magic.app.zjtv.entities.UserEntity;
import com.magic.app.zjtv.model.Address;
import com.magic.app.zjtv.model.Comment;
import com.magic.app.zjtv.model.Coupon;
import com.magic.app.zjtv.model.Order;
import com.magic.app.zjtv.model.OrderPackageService;
import com.magic.app.zjtv.model.Packages;
import com.magic.app.zjtv.model.User;
import com.magic.app.zjtv.model.UserCoupon;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.DateUtils;
import com.magic.commons.utils.ValidationUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

@Transactional
@Service  //加入这个注解后，Spring会自动加入这个类到Spring上下文中，可被其他类注入
public class UserService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	AddressDAO addressDAO;
	@Autowired
	UserCouponDAO userCouponDAO;
	@Autowired
	CouponDAO couponDAO;
	@Autowired
	PackageDAO packageDAO;
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	OrderPackageServiceDAO orderPackageServiceDAO;

	public User checkLogin(String account, String password) {
		UserEntity entity = userDAO.findByAccount(account);
		if(entity!=null){
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String encodedPs = encoder.encodePassword(password, account);
			if(entity.getPassword().equalsIgnoreCase(encodedPs)){
				return BeanUtils.convertEntityToModel(entity, User.class);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public User checkLoginByUid(Integer userId, String password) {
		UserEntity entity = userDAO.findOne(userId);
		if(entity!=null){
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String encodedPs = encoder.encodePassword(password, entity.getAccount());
			if(entity.getPassword().equalsIgnoreCase(encodedPs)){
				return BeanUtils.convertEntityToModel(entity, User.class);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	public User findUserByAccount(String account) {
		UserEntity entity = userDAO.findByAccount(account);
		return entity == null?null:BeanUtils.convertEntityToModel(entity, User.class);
	}

	public User saveUser(String account, String password) {
		UserEntity entity = new UserEntity();
		entity.setAccount(account);
		entity.setNickName(account);
		entity.setBirthday(new Date());
		entity.setPwdAnswer(new Date());
		entity.setIcon("http://115.28.17.190:80/ImageFileService/img/head.jpg");
		entity.setEmail("");
		entity.setAddress("");
		entity.setRegistDate(DateUtils.getDateTimestamp());
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    	String encodedPs = encoder.encodePassword(password, account);
    	entity.setPassword(encodedPs);
    	entity = userDAO.save(entity);
		return BeanUtils.convertEntityToModel(entity, User.class);
	}

	public User updateUser(User user) {
		UserEntity entity = userDAO.findOne(user.getId());
		entity.setNickName(user.getNickName());
		entity.setAddress(user.getAddress());
		entity.setBirthday(user.getBirthday());
		entity.setEmail(user.getEmail());
		entity.setIcon(user.getIcon());
		entity.setRegistDate(user.getRegistDate());
		entity.setPwdAnswer(user.getPwdAnswer());
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    	String encodedPs = encoder.encodePassword(user.getPassword(), user.getAccount());
    	entity.setPassword(encodedPs);
    	entity = userDAO.save(entity);
		return BeanUtils.convertEntityToModel(entity, User.class);
	}

	public User findUserByNickName(String nickName) {
		UserEntity entity = userDAO.findByNickName(nickName);
		return entity == null?null:BeanUtils.convertEntityToModel(entity, User.class);
	}

	public User findUserByUserId(Integer userId) {
		UserEntity entity = userDAO.findOne(userId);
		return entity == null?null:BeanUtils.convertEntityToModel(entity, User.class);
	}

	public List<User> findAllUsers() {
		List<UserEntity> entities = (List<UserEntity>)userDAO.findAll();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, User.class);
		}
		return null;
	}

	public void updateUserPassword(Integer userId, String password) {
		UserEntity  user= userDAO.findOne(userId);
    	Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    	String encodedPs = encoder.encodePassword(password, user.getAccount());
    	user.setPassword(encodedPs);
    	userDAO.save(user);
	}


	public User saveOrUpdatUser(User user) {
		Integer userId = user.getId();
        UserEntity entity = new UserEntity();
        
        if (userId != null) {
            entity = userDAO.findOne(userId);
        }
        entity.setNickName(user.getNickName());
		entity.setAddress(user.getAddress());
		entity.setBirthday(user.getBirthday());
		entity.setEmail(user.getEmail());
		entity.setIcon(user.getIcon());
		entity.setPwdAnswer(user.getPwdAnswer());
		entity = userDAO.save(entity);
		
		return BeanUtils.convertEntityToModel(entity, User.class);
	}

	public void setAddressIdDefaultServiceAddressToZero(int userId) {
		this.addressDAO.setAddressIdDefaultServiceAddressToZero(userId);
	}

	public Address addUserAddress(Address address) {
		AddressEntity entity = new AddressEntity();
		entity.setAddress(address.getAddress());
		entity.setDistrictInformation(address.getDistrictInformation());
		entity.setIdDefaultServiceAddress(address.getIdDefaultServiceAddress());
		entity.setPhone(address.getPhone());
		entity.setReservation(address.getReservation());
		entity.setUserId(address.getUserId());
		entity = addressDAO.save(entity);
		return BeanUtils.convertEntityToModel(entity, Address.class);
	}

	public List<Address> findUserServiceAddressByUserId(Integer userId) {
		List<AddressEntity> entities = this.addressDAO.findByUserId(userId);
		return entities == null ? null : BeanUtils.convertEntityToModelList(entities, Address.class);
	}

	public Address findAddressById(int serviceAddressId) {
		AddressEntity entity = this.addressDAO.findOne(serviceAddressId);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Address.class);
	}

	public List<UserCoupon> findUserCouponByUserIdAndIsUsed(Integer userId,
			Integer isUsed) {
		List<UserCouponEntity> entities = this.userCouponDAO.findByUserIdAndIsUsed(userId, isUsed);
		if (entities != null && entities.size() > 0) {
			List<UserCoupon> list = BeanUtils.convertEntityToModelList(entities, UserCoupon.class);
			for (UserCoupon userCoupon : list) {
				CouponEntity entity = this.couponDAO.findOne(userCoupon.getCouponId());
				userCoupon.setCoupon(BeanUtils.convertEntityToModel(entity, Coupon.class));
			}
			return list;
		}
		return null;
	}

	public List<Packages> findPackagesByType(Integer type) {
		List<PackagesEntity> entities = this.packageDAO.findByPackageTypeId(type);
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, Packages.class);
		}
		return null;
	}

	public Packages findPackageByPackageServiceId(Integer packageServiceId) {
		PackagesEntity entity = this.packageDAO.findOne(packageServiceId);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Packages.class);
	}

	public Order findOrderByOrderId(Integer orderId) {
		OrderEntity entity = this.orderDAO.findOne(orderId);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Order.class);
	}

	public Comment findCommentByUserIdAndOrderId(Integer userId, Integer orderId) {
		CommentEntity entity = this.commentDAO.findByUserIdAndOrderId(userId, orderId);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Comment.class);
	}

	public String addComment(Comment comment) {
		String msg = null;
		CommentEntity entity = new CommentEntity();
		entity.setCommentTime(comment.getCommentTime());
		entity.setContent(comment.getContent());
		entity.setOrderId(comment.getOrderId());
		entity.setStar(comment.getStar());
		entity.setUserId(comment.getUserId());
		entity = this.commentDAO.save(entity);
		if (entity != null) {
			// 修改order状态
			OrderEntity orderEntity = this.orderDAO.findOne(entity.getOrderId());
			orderEntity.setIsComment(1);
			this.orderDAO.save(orderEntity);
			// 如果评论的是5星，则赠送优惠券给用户
			if (entity.getStar() == 5) {
				List<CouponEntity> entities = (List<CouponEntity>)this.couponDAO.findAll();
				boolean flag = false;
				if (entities != null && entities.size() > 0) {
					for (CouponEntity couponEntity : entities) {
						if (couponEntity.getCouponNum() > 0) {
							UserCouponEntity userCouponEntity = new UserCouponEntity();
							userCouponEntity.setCouponId(couponEntity.getId());
							userCouponEntity.setCreateDate(DateUtils.getDateTimestamp());
							userCouponEntity.setIsUsed(0);
							userCouponEntity.setUpdateTime(DateUtils.getDateTimestamp());
							userCouponEntity.setUserId(comment.getUserId());
							// 修改优惠券数量
							this.userCouponDAO.save(userCouponEntity);
							couponEntity.setCouponNum(couponEntity.getCouponNum() - 1);
							this.couponDAO.save(couponEntity);
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					msg = "优惠券已发放到你的包裹，请到个人中心查看。";
				}else {
					msg = "优惠券已经被抢光。";
				}
			}else {
				msg = "";
			}
		}
		return msg;
	}

	public List<Comment> findAllCommentsByPackageType(Integer packageType) {
		List<CommentEntity> entities = this.commentDAO.findAllCommentsByPackageTypeId(packageType);
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, Comment.class);
		}
		return null;
	}

	public List<Comment> getCommentByPackageType(Integer packageType,
			Integer page, Integer pageSize) {
		List<Comment> list = null;
		int startNum = (page - 1) * pageSize;
		int limitNum = pageSize;
		List<CommentEntity> entities = this.commentDAO.getCommentByPackageType(packageType, startNum, limitNum);
		if(entities != null && entities.size() > 0){
			list = BeanUtils.convertEntityToModelList(entities, Comment.class);
			for (Comment comment : list) {
				String nickName = this.userDAO.findUserNickName(comment.getUserId());
				if (nickName == null || "".equals(nickName.trim())) {
					nickName = "匿名用户";
				}
				if (ValidationUtils.validateMoblie(nickName)) {
		    		String oldChar = nickName.substring(3, 8);
					nickName = nickName.replace(oldChar, "*");
				}
				comment.setNickName(nickName);
			}
		}
		return list;
	}

	public UserCoupon findUserCouponByUserCouponId(int userCouponId) {
		UserCouponEntity entity = this.userCouponDAO.findOne(userCouponId);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, UserCoupon.class);
	}

	public float getPrice(List<Integer> packageServiceIdList) {
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (int i = 0; i < packageServiceIdList.size(); i++) {
			if (i == (packageServiceIdList.size() - 1)) {
				sb.append(String.valueOf(packageServiceIdList.get(i)));
			}else {
				sb.append(String.valueOf(packageServiceIdList.get(i))).append(",");
			}
		}
		sb.append(")");
		
		return 0;
	}

	public List<Packages> findPackagesByIds(List<Integer> packageServiceIdList) {
		List<PackagesEntity> entities = new ArrayList<PackagesEntity>();
		for (int i = 0; i < packageServiceIdList.size(); i++) {
			PackagesEntity entity = this.packageDAO.findOne(packageServiceIdList.get(i));
			if (entity != null) {
				entities.add(entity);
			}
		}
		return BeanUtils.convertEntityToModelList(entities, Packages.class);
	}

	public Order addOrder(Order order, Integer packageTypeId) {
		String orderName = null;
		switch (packageTypeId) {
		case 1:
			orderName = "日常保洁";
			break;
		case 2:
			orderName = "头部保健舒缓";
			break;
		case 3:
			orderName = "狗";
			break;
		case 4:
			orderName = "猫";
			break;

		default:
			orderName = "未知";
			break;
		}
		
		OrderEntity entity = new OrderEntity();
		entity.setCreateDate(DateUtils.getDateTimestamp());
		entity.setAdditionalRequirements(order.getAdditionalRequirements());
		entity.setIsComment(0);
		entity.setOrderName(orderName);
		entity.setServiceAddressId(order.getServiceAddressId());
		entity.setServiceTime(order.getServiceTime());
		entity.setState(1);
		entity.setSumPrice(order.getSumPrice());
		entity.setUpdateTime(DateUtils.getDateTimestamp());
		entity.setUserCouponId(order.getUserCouponId());
		entity.setUserId(order.getUserId());
		entity.setWorkerId(0);
		entity = this.orderDAO.save(entity);
		if (entity != null) {
			if (order.getUserCouponId() != 0) {
				UserCouponEntity userCouponEntity = this.userCouponDAO.findOne(order.getUserCouponId());
				userCouponEntity.setIsUsed(1);
				this.userCouponDAO.save(userCouponEntity);
			}
		}else {
			return null;
		}
		return BeanUtils.convertEntityToModel(entity, Order.class);
	}

	public Order updateOrderState(Order order) {
		OrderEntity entity = this.orderDAO.findOne(order.getId());
		entity.setState(order.getState());
		entity = this.orderDAO.save(entity);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Order.class);
	}

	public List<Order> findOrders(Integer userId, Integer state, Integer page,
			Integer pageSize) {
		int startNum = (page - 1) * pageSize;
		int limitNum = pageSize;
		List<OrderEntity> entities = null;
		if (state == 1) {
			entities = this.orderDAO.findEndOrders(userId, startNum, limitNum);
		}else {
			entities = this.orderDAO.findOrders(userId, startNum, limitNum);
		}
		return entities == null ? null : BeanUtils.convertEntityToModelList(entities, Order.class);
	}

	public List<OrderPackageService> findOrderPackageServiceByOrderId(
			Integer orderId) {
		List<OrderPackageService> list = null;
		List<OrderPackageServiceEntity> entities = this.orderPackageServiceDAO.findByOrderId(orderId);
		if (entities != null && entities.size() > 0) {
			list = BeanUtils.convertEntityToModelList(entities, OrderPackageService.class);
			for (OrderPackageService orderPackageService : list) {
				PackagesEntity entity = this.packageDAO.findOne(orderPackageService.getPackageServiceId()); 
				if (entity != null) {
					orderPackageService.setPackages(BeanUtils.convertEntityToModel(entity, Packages.class));
				}
			}
		}
		return list;
	}
	

}
