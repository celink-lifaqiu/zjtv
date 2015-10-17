package com.magic.taglib;

import com.magic.commons.utils.DateUtils;
import com.magic.commons.utils.FileUtils;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-10
 * Time: 上午5:59
 * To change this template use File | Settings | File Templates.
 */
public class JFengELFunction {

    public static String imageBase64(byte[] bytes){
        try {
            return FileUtils.formatImageToBase64String(bytes);
        } catch (MagicParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MagicException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MagicMatchNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public static int size(Collection<?> collection){
        if (collection != null) {
            return collection.size();
        }
        return 0;
    }

    public static String dataFormatNoTime(Date date){
        return DateUtils.dateToInputStrWOTime(date);
    }
    public static String dateFormat(Date date){
        return DateUtils.dateToInputStr(date);
    }

    public static String gender(String value){
        if (!StringUtils.isEmpty(value))
            if ("M".equalsIgnoreCase(value)) return "男";
            else if("F".equalsIgnoreCase(value)) return "女";
        return "";
    }

    public static String age(Date dob){
        if (dob != null) {
            return String.valueOf(DateUtils.age(dob));
        }
        return "";
    }
}
