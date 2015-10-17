package com.magic.app.zjtv.dao;

import com.magic.app.zjtv.entities.VersionChangesEntity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by YinJianFeng on 14-5-18.
 */
public interface VersionChangesDAO extends CrudRepository<VersionChangesEntity, Integer> {
    public List<VersionChangesEntity> findByVersionCodeAndPlatform(Integer versionCode, String platform);
}
