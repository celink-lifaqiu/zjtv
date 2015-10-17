package com.magic.core.report;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YinJianFeng on 14-6-15.
 */
public abstract class BaseReportListener {
    private Map<String, Object> paramMap = new HashMap<String, Object>();

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public abstract void onStart();
    public abstract void onFinished(String dir);
}
