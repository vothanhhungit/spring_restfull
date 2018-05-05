package com.spring.web.constant;

import com.spring.web.util.PropertiesUtil;

/**
 * CommonConstant
 *
 * @author HungVT
 */
public class CommonConstant {
    public static final String PASS_CONST = "aim6434!forse"; // for encrypt password


    public static final String PID = "accountId";
    public static final String EMAIL = "email";
    public static final String TOKEN_SESSION_KEY = "token";
    public static final String LAST_REQUEST_TIME = "lastReqTime";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String LONGIN_NAME = "loginName";
    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_NAME = "clientName";
    public static final String USER_ID = "userId";

    public static final String AUTH_EMAIL = "X-Auth-Email";
    public static final String AUTH_PASSWORD = "X-Auth-Password";
    public static final String AUTH_CLIENT_ID = "X-Client-Id";
    public static final String AUTH_TOKEN = "X-Auth-Token";

    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME_SLASH = "yyyy/MM/dd HH:mm:ss";

    public static final String LANGUAGE_EN = "EN";
    public static final String LANGUAGE_JP = "JP";
    public static final String MESSAGE_EN = "message_en";
    public static final String MESSAGE_JP = "message_jp";

    public static final String PATH_FOLDER_CONFIG = "/home/web/conf/resource/";

    public static final String ACCOUNT = "Account";
    public static final String PREFIX_PORTER_ACCOUNT = "Account";
    public static final String JOB = "Job";
    public static final String PREFIX_PORTER_JOB = "Job";

    public static final Integer ROLE_ADMIN = 0;
    public static final Integer ROLE_AIM = 1;
    public static final Integer ROLE_NON_AIM = 2;
}
