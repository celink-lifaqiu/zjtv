package com.magic.commons.utils;
/**
 * @author Yin Jian Feng
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.ReflectUtils;


public class BeanUtils extends org.springframework.beans.BeanUtils {
	public static <T> T convertEntityToModel(Object entity, Class<T> clz, String... ignoreProperties){
		T target = (T) ReflectUtils.newInstance(clz);
		copyProperties(entity, target, ignoreProperties);
		return target;
	}
	
	public static <T> List<T> convertEntityToModelList(List<?> entities, Class<T> clz, String... ignoreProperties){
		List<T> list = new ArrayList<T>();
		if(entities != null){
			for(Object entity:entities){
				list.add(convertEntityToModel(entity, clz, ignoreProperties));
			}
		}
		return list;
	}
}
