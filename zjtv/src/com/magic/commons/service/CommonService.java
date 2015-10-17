/**
 * @author YinJianFeng
 */
package com.magic.commons.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.magic.commons.Constants;
import com.magic.commons.utils.DateUtils;
import com.magic.commons.utils.KeywordFilter;
import com.magic.commons.utils.PropertiesUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.app.zjtv.dao.*;
import com.magic.app.zjtv.entities.*;
import com.magic.app.zjtv.model.*;
import com.magic.commons.utils.BeanUtils;


@Service
public class CommonService {
//    Log log = LogFactory.getLog(getClass());
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	VersionDAO versionDAO;

    @Autowired
    VersionChangesDAO versionChangesDAO;
	
	
	public Version getVersion(String platform){
		VersionEntity entity = versionDAO.findByPlatform(platform);
		Version version = BeanUtils.convertEntityToModel(entity, Version.class);
        List<VersionChangesEntity> changesEntities = versionChangesDAO.findByVersionCodeAndPlatform(version.getVersionCode(), platform);
        List<String> changes = new ArrayList<String>();
        for (VersionChangesEntity changesEntity : changesEntities) {
            changes.add(changesEntity.getContent());
        }
        version.setChanges(changes.toArray(new String[]{}));
        return version;
	}


}
