package com.magic.commons.helper;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.magic.commons.models.BaseHttpResult;
import com.magic.commons.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luoli on 14-6-18.
 */
public abstract class JPushHelper {
    static Logger logger = LoggerFactory.getLogger(JPushHelper.class);

    public static final String DEFAULT_CONTENT = "msgContent";
    public static final String DEFAULT_TITLE = "笑啦";
    private static final String appKey ="86ff3423985453f6889eb805";
    private static final String masterSecret = "ccac88a67f2ed8fdf6d02af7";
//    private static final String appKey ="98cc1ba473d3f6fd724aadeb";
//    private static final String masterSecret = "276b0b62390330c177131503";

    public static void sendNotification(String message,String uid,String type, boolean isNotification){
        logger.debug("This is JPushHelper");
        message = StringUtils.isEmpty(message)?DEFAULT_CONTENT:message;
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushPayload payload ;
        if(uid=="all"){
            payload = PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setOptions(Options.newBuilder().setApnsProduction(false).build())
                    .setAudience(Audience.all())
                    .setNotification(Notification.newBuilder()
                            .addPlatformNotification(IosNotification.newBuilder()
                                    .setAlert(message)
                                    .addExtra("type", type)
                                    .build())
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .setAlert(message)
                                    .addExtra("uid", uid)
                                    .addExtra("type", type)
                                    .addExtra("is_notification", isNotification)
                                    .build())
                            .build())
                    .build();
        }else{
            payload = PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setOptions(Options.newBuilder().setApnsProduction(false).build())
                    .setAudience(Audience.alias(uid))
                    .setNotification(Notification.newBuilder()
                            .addPlatformNotification(IosNotification.newBuilder()
                                    .setAlert(message)
                                    .addExtra("type", type)
                                    .build())
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .setAlert(message)
                                    .addExtra("uid", uid)
                                    .addExtra("type", type)
                                    .addExtra("is_notification", isNotification)
                                    .build())
                            .build())
                    .build();
        }
        PushResult result = jpushClient.sendPush(payload);
        if (result.isResultOK()) {
            logger.debug(result.toString());
        } else {
            if (result.getErrorCode() > 0) {
                logger.warn(result.getOriginalContent());
            } else {
                System.out.println("Maybe connect error. Retry laster. ");
                logger.debug("Maybe connect error. Retry laster. ");
            }
        }
    }

//    public static void sendMessage(String uid, String type){
//        sendMessage(uid, type, null, false);
//    }
//    public static void sendMessage(String uid, String type, String content){
//        sendMessage(uid, type, content, false);
//    }

//    public static void sendMessage(String uid, String type, String content, boolean isNotification){
//        content = StringUtils.isEmpty(content)?DEFAULT_CONTENT:content;
//        Message.Builder builder = Message.newBuilder()
//                .setTitle(DEFAULT_TITLE)
//                .addExtra("uid", uid)
//                .addExtra("type", type)
//                .addExtra("is_notification", isNotification)
//                .setMsgContent(content);
//        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
//        PushPayload payload = PushPayload.newBuilder()
//                .setPlatform(Platform.all())
//                .setAudience(Audience.alias("13"))
//                .setMessage(builder.build())
//                .build();
//        PushResult result = jpushClient.sendPush(payload);
//        if (result.isResultOK()) {
//            logger.debug(result.toString());
//        } else {
//            if (result.getErrorCode() > 0) {
//                logger.warn(result.getOriginalContent());
//            } else {
//                System.out.println("Maybe connect error. Retry laster. ");
//                logger.debug("Maybe connect error. Retry laster. ");
//            }
//        }
//    }
}
