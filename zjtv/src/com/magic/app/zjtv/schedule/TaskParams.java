package com.magic.app.zjtv.schedule;

import com.magic.commons.utils.StringUtils;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinjianfeng on 14-6-14.
 */
public final class TaskParams {
    public static final String PARAM_KEY = "PARAM_KEY_";
    public static final String PARAM_LABEL = "PARAM_LABEL_";
    public static final String PARAM_VALUE = "PARAM_VALUE_";

    private Map<String, String> data = new HashMap<String, String>();
    private List<String> keys = new ArrayList<String>();

    public void addParam(String key, String value){
        addParam(key, value, null);
    }
    public void addParam(String key, String value, String label){
        data.put(PARAM_KEY+key, key);
        data.put(PARAM_VALUE+key, value);
        if (StringUtils.isNotEmpty(label)){
            data.put(PARAM_LABEL+key, label);
        }
        keys.add(key);
    }

    public String getValue(String key){
        return data.get(PARAM_VALUE+key);
    }
    public String getLabel(String key){
        return data.get(PARAM_LABEL+key);
    }

    public String getJSONString(){
        JSONObject jsonObject = new JSONObject();
        for (String key : keys) {
            String paramKey = data.get(PARAM_KEY+key);
            String paramValue = data.get(PARAM_VALUE+key);
            jsonObject.put(paramKey, paramValue);
        }
        return jsonObject.toString();
    }

    public String getDispString(){
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : keys) {
            String paramValue = data.get(PARAM_VALUE + key);
            String paramLabel = data.get(PARAM_LABEL + key);
            stringBuffer.append(paramLabel+"\t=\t"+paramValue+"<br/>");
        }
        return stringBuffer.toString();
    }
}
