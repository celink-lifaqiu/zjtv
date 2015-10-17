/**
 * @author YinJianFeng
 */
package com.magic.app.zjtv.dao;

import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.VersionEntity;

public interface VersionDAO extends CrudRepository<VersionEntity, Integer> {
	public VersionEntity findByPlatform(String platform);
}
