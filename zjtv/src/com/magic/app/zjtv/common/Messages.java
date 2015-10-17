package com.magic.app.zjtv.common;

public enum Messages {

    EC_FAILED("EC_FAILED"),
    EC_PARAMETER_ERR("EC_PARAMETER_ERR"),
    EC_ACCOUNTRECEIVENUMS_ERR("EC_ACCOUNTRECEIVENUMS_ERR"),
    EC_PHONERECEIVENUMS_ERR("EC_PHONERECEIVENUMS_ERR"),
    EC_EXCHANGECONFIRM_ERR("EC_EXCHANGECONFIRM_ERR"),
    EC_NULLSHOPITEM_ERR("EC_NULLSHOPITEM_ERR"),
    EC_ISEXIST_AWARD("EC_ISEXIST_AWARD"),
    EC_ISEXIST_DEVICE("EC_ISEXIST_DEVICE"),
    EC_ISEXIST_AWARD_NULL("EC_ISEXIST_AWARD_NULL"),
    EC_ISEXPIRED_ERR("EC_ISEXPIRED_ERR"),
    EC_REDENVELOPE_ERR("EC_REDENVELOPE_ERR"),
    EC_SHOP_NOT_EXISTS("EC_SHOP_NOT_EXISTS"),
    EC_TEL_ERR("EC_TEL_ERR"),
    EC_LIMITTIME_ERR("EC_LIMITTIME_ERR"),
    EC_SCRAPE_TIME_ERR("EC_SCRAPE_TIME_ERR"),
    EC_COMMENT_EXIST_ERR("EC_COMMENT_EXIST_ERR"),
    EC_LOGIN_ERR("EC_LOGIN_ERR"),
    EC_USERID_ERR("EC_USERID_ERR"),
    EC_START_ERR("EC_START_ERR"),
    EC_DB_ERROR("EC_DB_ERROR"),
    EC_USERNAME_EXISTS("EC_USERNAME_EXISTS"),
    EC_USERNAME_ERR("EC_USERNAME_ERR"),
    EC_USERNAME_NOT_LEGAL("EC_USERNAME_NOT_LEGAL"),
    EC_PASSWORD_ERR("EC_PASSWORD_ERR"),
    EC_USER_NOT_EXISTS("EC_USER_NOT_EXISTS"),
    EC_DATEFORAMT_ERR("EC_DATEFORAMT_ERR"),
    EC_PASSWORD_NOT_MATCH("EC_PASSWORD_NOT_MATCH"),
    EC_NICKNAME_ERR("EC_NICKNAME_ERR"),
    USERID_NOT_EXSIT_ERR("USERID_NOT_EXSIT_ERR"),
    EC_SHOPNAME_EXISTS("EC_SHOPNAME_EXISTS"),
    EC_IMAGE_FILE_NOT_EXISTS("EC_IMAGE_FILE_NOT_EXISTS"),
    EC_COMMENT_CONTENT_NULL_EXISTS("EC_COMMENT_CONTENT_NULL_EXISTS"),
    EC_NEWPASSWORD_ERR("EC_NEWPASSWORD_ERR"),
    EC_ORDER_NOT_EXIST("EC_ORDER_NOT_EXIST"),
    EC_CANNOT_COMMENT("EC_CANNOT_COMMENT"),
    EC_IMAGE_FILE_TYPE("EC_IMAGE_FILE_TYPE"),
    EC_HELP_LADY_CONTENT_TOO_LONG("EC_HELP_LADY_CONTENT_TOO_LONG"),
    EC_OPERATION_ERR("EC_OPERATION_ERR"),
    EC_ATT_SELF_ERR("EC_ATT_SELF_ERR"),
    EC_ANSWER_ERR("EC_ANSWER_ERR"),
    EC_ADDRESSID_NOT_EXIST_ERR("EC_ADDRESSID_NOT_EXIST_ERR"),
    EC_UNAUTHORIZEDOPERATION_ERR("EC_UNAUTHORIZEDOPERATION_ERR"),
    EC_PACKAGETYPE_ERR("EC_PACKAGETYPE_ERR"),
    EC_PACKAGE_NOT_EXIST_ERR("EC_PACKAGE_NOT_EXIST_ERR"),
    EC_COUPON_USED("EC_COUPON_USED"),
    EC_SERVICEADDRESS_NOT_EXISTS("EC_SERVICEADDRESS_NOT_EXISTS"),
    EC_PACKAGE_PARAMETER_ERR("EC_PACKAGE_PARAMETER_ERR"),
    EC_SERVICETIME_ERR("EC_SERVICETIME_ERR"),
    EC_CHOICEPACKAGESERVICE("EC_CHOICEPACKAGESERVICE"),
    EC_SUMPRAICE_ERR("EC_SUMPRAICE_ERR"),
    EC_END_ORDER("EC_END_ORDER"),
    EC_EMPTY_SUBJECT_CONTENT_ERR("EC_EMPTY_SUBJECT_CONTENT_ERR"),
    EC_STORY_CATG_NOT_EXISTS("EC_STORY_CATG_NOT_EXISTS"),
    EC_STORY_NOT_EXISTS("EC_STORY_NOT_EXISTS"),
    EC_ORDER_NOT_END("EC_ORDER_NOT_END"),
    EC_PACKAGESERVICEID_NOT_EXISTS("EC_PACKAGESERVICEID_NOT_EXISTS"),
    EC_USERCOUPON_NOT_EXISTS("EC_USERCOUPON_NOT_EXISTS"),
    EC_SHOP_SCORE_ERR("EC_SHOP_SCORE_ERR"),
    EC_SEQUENCECODE_ERR("EC_SEQUENCECODE_ERR"),
    EC_SHOP_ITEM_NUM_ERR("EC_SHOP_ITEM_NUM_ERR"),
    EC_PET_LOW_HP("EC_PET_LOW_HP"),
    EC_PET_LOW_MoodValue("EC_PET_LOW_MoodValue"),
    EC_PET_LOW_SCORE("EC_PET_LOW_SCORE"),
    EC_FIFA_CHIPPED_IN("EC_FIFA_CHIPPED_IN"),
    EC_FIFA_TEAM_LIKE_LIMITED("EC_FIFA_TEAM_LIKE_LIMITED"),
    EC_FIFA_LARGE_SCORE("EC_FIFA_LARGE_SCORE");
    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}