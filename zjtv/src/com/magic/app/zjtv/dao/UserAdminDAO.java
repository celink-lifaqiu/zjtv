package com.magic.app.zjtv.dao;

import com.magic.app.zjtv.entities.UserAdminEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by yunchunnan on 14-5-6.
 */
public interface UserAdminDAO extends CrudRepository<UserAdminEntity, Integer> {

    @Query(value = "From UserAdminEntity  where username != 'magic'")
    public List<UserAdminEntity> loadAllAdminUsers();
    public UserAdminEntity findByUsername(String username);
    
    @Query(value="select * from alipay.user_admin where shop_id=?1 and id not in(?2);", nativeQuery = true)
	public List<UserAdminEntity> queryUserAdminByShopIdAndWithoutUserAdminId(
			Integer shopid, Integer userAdminId);
}
