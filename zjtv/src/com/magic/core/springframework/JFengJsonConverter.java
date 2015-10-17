package com.magic.core.springframework;

import com.magic.commons.models.HttpDataResult;
import com.magic.commons.models.HttpListResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by YinJianFeng on 14-4-22.
 */
public class JFengJsonConverter extends MappingJackson2HttpMessageConverter{
	private static final String TAG = "JFengJsonConverter";
	Logger logger = LoggerFactory.getLogger(getClass());
	
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        
            if (object instanceof HttpDataResult){
//                System.out.println("HttpDataResult");
                HttpDataResult result = (HttpDataResult) object;
                Object data = result.getData();
                if(data != null){
                    if(data instanceof Map){
                    	/*
                        Map datamap = (Map) data;
                        for (Iterator iterator = datamap.entrySet().iterator(); iterator.hasNext(); ) {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            Object value = entry.getValue();
                            if (value == null) {
                                if (value instanceof Integer) {
                                    entry.setValue(0);
                                } else if (value instanceof String) {
                                    entry.setValue("");
                                } else if (value instanceof BigDecimal) {
                                    entry.setValue(BigDecimal.ZERO);
                                } else if (value instanceof Date) {

                                }
                            }
                        }
                        */
                    }else {
                    	List<Field> allField = new ArrayList<Field>();
                        Field[] fields = data.getClass().getDeclaredFields();
                        allField.addAll(Arrays.asList(fields));
                        Field[] superFields = data.getClass().getSuperclass().getDeclaredFields();
                        allField.addAll(Arrays.asList(superFields));
                        for (Field field : allField) {
                            try {
                                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), data.getClass());
                                Method getMethod = pd.getReadMethod();
                                Method writeMethod = pd.getWriteMethod();
                                Object value = getMethod.invoke(data);
                                if (value == null) {
                                    if ("java.lang.Integer".equals(field.getType().getName())) {
                                        writeMethod.invoke(data, 0);
                                    } else if ("java.lang.String".equals(field.getType().getName())) {
                                        writeMethod.invoke(data, "");
                                    } else if ("java.math.BigDecimal".equals(field.getType().getName())) {
                                        writeMethod.invoke(data, BigDecimal.ZERO);
                                    } else if ("java.util.Date".equals(field.getType().getName())) {

                                    }
                                }
                            } catch (IllegalAccessException e) {
                            	logger.debug(e.getMessage());
                            } catch (IllegalArgumentException e) {
                            	logger.debug(e.getMessage());
                            } catch (InvocationTargetException e) {
                            	logger.debug(e.getMessage());
                            } catch (IntrospectionException e) {
                            	logger.debug(e.getMessage());
                            }
                        }
                    }
                }
            }else if(object instanceof HttpListResult){
//                System.out.println("HttpListResult");
                HttpListResult result = (HttpListResult) object;
                List<?> list = result.getDataList();
                if(list != null){
	                for (Object data : list) {
                        if(data instanceof Map){
                        	/*
                            Map datamap = (Map) data;
                            for (Iterator iterator = datamap.entrySet().iterator(); iterator.hasNext(); ) {
                                Map.Entry entry = (Map.Entry) iterator.next();
                                Object value = entry.getValue();
                                if (value == null) {
                                    if (value instanceof Integer) {
                                        entry.setValue(0);
                                    } else if (value instanceof String) {
                                        entry.setValue("");
                                    } else if (value instanceof BigDecimal) {
                                        entry.setValue(BigDecimal.ZERO);
                                    } else if (value instanceof Date) {

                                    }
                                }
                            }
                            */
                        }else {
                        	List<Field> allField = new ArrayList<Field>();
                            Field[] fields = data.getClass().getDeclaredFields();
                            allField.addAll(Arrays.asList(fields));
                            Field[] superFields = data.getClass().getSuperclass().getDeclaredFields();
                            allField.addAll(Arrays.asList(superFields));
                            for (Field field : allField) {
                                try {
                                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), data.getClass());
                                    Method getMethod = pd.getReadMethod();
                                    Method writeMethod = pd.getWriteMethod();
                                    Object value = getMethod.invoke(data);
                                    if (value == null) {
                                        if ("java.lang.Integer".equals(field.getType().getName())) {
                                            writeMethod.invoke(data, 0);
                                        } else if ("java.lang.String".equals(field.getType().getName())) {
                                            writeMethod.invoke(data, "");
                                        } else if ("java.math.BigDecimal".equals(field.getType().getName())) {
                                            writeMethod.invoke(data, BigDecimal.ZERO);
                                        } else if ("java.util.Date".equals(field.getType().getName())) {

                                        }
                                    }
                                } catch (IllegalAccessException e) {
                                	logger.debug(e.getMessage());
                                } catch (IllegalArgumentException e) {
                                	logger.debug(e.getMessage());
                                } catch (InvocationTargetException e) {
                                	logger.debug(e.getMessage());
                                } catch (IntrospectionException e) {
                                	logger.debug(e.getMessage());
                                }
                            }
                        }
	                }
	            }
            }
     
        super.writeInternal(object, outputMessage);
    }
}
