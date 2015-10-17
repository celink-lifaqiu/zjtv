package com.magic.qiniu;

import com.magic.commons.http.HttpClientHelper;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: yunchunnan
 * Date: 14-4-7
 * Time: 下午1:18
 * To change this template use File | Settings | File Templates.
 */
public class QiniuHelper {
	public static void main(String[] args) {
		try {
			System.out.println(generateUpToken("hbzh-images"));
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static final String QINIU_IMAGE_HOST = "http://tvcloud-images.qiniudn.com/";
    public static final String QINIU_AUDIO_HOST = "http://tvcloud-audios.qiniudn.com/";
    public static final String QINIU_VIDEO_HOST = "http://tvcloud-videos.qiniudn.com/";
    public static final String QINIU_BUCKET_IMAGE = "tvcloud-images";
    public static final String QINIU_BUCKET_AUDIO = "tvcloud-audios";
    public static final String QINIU_BUCKET_VIDEO = "tvcloud-videos";

    public static void init(){
        Config.ACCESS_KEY = "8PaMy49u0A69pgqar9fkMxUYHzxUVgNWvYCbG-sc";
        Config.SECRET_KEY = "a1bys6SmLIVg1BPLv8LB5XszdUNtSsM-LIS_fAx6";
    }
    public static String generateUpToken(String bucketName) throws AuthException {
        init();
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(bucketName);
        return putPolicy.token(mac);
    }

    public static String getImageInfo(String imagePath){
        return HttpClientHelper.doGet(imagePath+"?imageInfo");
    }
    public static Integer getImageWidth(String imagePath){
        Object info = getImageInfoByKey(imagePath, "width");
        if (info != null) {
            return (Integer) info;
        }
        return 0;
    }

    public static Integer getImageHeight(String imagePath){
        Object info = getImageInfoByKey(imagePath, "height");
        if (info != null) {
            return (Integer) info;
        }
        return 0;
    }

    public static Object getImageInfoByKey(String imagePath, String key){
        String imageInfo = getImageInfo(imagePath);
        if (StringUtils.isEmpty(imageInfo)){
            return null;
        }
        JSONObject jsonObject = JSONObject.fromObject(imageInfo);
        return jsonObject.get(key);
    }

    public static String handleImageSize(String imagePath, Integer width, Integer height){
        String imageSuffix = "?imageView2/0";
        if (width != null && width != 0){
            imageSuffix +=  "/w/" + width;
        }
        if (height != null && height != 0){
            imageSuffix += "/h/" + height;
        }
        return imagePath + imageSuffix;
    }

    public static Integer calculateHeightByWidth(String imageName, Integer needWidth){
        Integer orgWidth = getImageWidth(imageName);
        Integer orgHeight = getImageHeight(imageName);
        BigDecimal orgWidthDec = BigDecimal.valueOf(orgWidth);
        BigDecimal orgHeightDec = BigDecimal.valueOf(orgHeight);
        BigDecimal needWidthDec = BigDecimal.valueOf(needWidth);

        BigDecimal scale = BigDecimal.ONE;
        if (!orgWidthDec.equals(BigDecimal.ZERO))
            scale = needWidthDec.divide(orgWidthDec, 2, BigDecimal.ROUND_CEILING);
        BigDecimal needHeightDec = orgHeightDec.multiply(scale);
        return needHeightDec.intValue();
    }

    public static String handleImageStr(String images){
        if(StringUtils.isEmpty(images)) return "";
        String image = "{";
        if (StringUtils.isNotEmpty(images)){
            String[] imageArr = images.split("@");
            for (int i = 0; i < imageArr.length; i++) {
                image += "\"image"+(i+1)+"\":\""+QiniuHelper.QINIU_IMAGE_HOST+imageArr[i]+"\",";
            }
        }
        image = image.substring(0,image.length()-1);
        image += "}";
        return image;
    }

    public static String handleAudioStr(String audio){
        if (StringUtils.isNotEmpty(audio))
            return QiniuHelper.QINIU_AUDIO_HOST+audio;
        return "";
    }
    public static String handleVideoStr(String video){
        if (StringUtils.isNotEmpty(video))
            return QiniuHelper.QINIU_VIDEO_HOST+video;
        return "";
    }
}
