package com.magic.commons.helper;

import com.magic.app.zjtv.common.Messages;
import com.magic.commons.models.BaseHttpResult;

import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 14-3-6
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class HttpResultHelper {
    public static BaseHttpResult handleJsonErrorResult(BaseHttpResult result, Messages message){
        try {
        	String errorRes = message.getMessage();
            if (StringUtils.isEmpty(errorRes)) errorRes = "EC_FAILED";
            ResourcePropertySource propertySource = new ResourcePropertySource("messages", "classpath:messages.properties");
            if (!errorRes.startsWith("com.magic.message.")){
                errorRes = "com.magic.message."+errorRes;
            }
            String errorcode = (String) propertySource.getProperty(errorRes);
            String error = (String) propertySource.getProperty(errorRes+".message");
            if (StringUtils.isEmpty(errorcode)) errorcode = "0";
            if (StringUtils.isEmpty(error)) error = "";
            result.setState(false);
            result.setErrcode(Integer.parseInt(errorcode));
            result.setError(error);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }
}
