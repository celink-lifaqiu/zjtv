package com.magic.commons.models;

import java.util.List;

/**
 * Created by Yin Jian Feng on 14-3-22.
 */
public class HttpListResult extends BaseHttpResult {
	int total;
    List<?> dataList;

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
    	if(dataList != null){
    		total = dataList.size();
    	}
        this.dataList = dataList;
    }

	public int getTotal() {
		return total;
	}
}
