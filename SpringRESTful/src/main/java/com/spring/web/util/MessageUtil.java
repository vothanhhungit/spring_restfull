package com.spring.web.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.web.constant.CommonConstant;

/**
 * get message content from file message properties
 * 
 * @author HungVT
 */
public class MessageUtil {
    private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);

    private static Map<String, String> enMsgMap = new HashMap<String, String>();
    private static Map<String, String> jpMsgMap = new HashMap<String, String>();

    static {
        if (enMsgMap.isEmpty()) {
            enMsgMap = loadMessageData(CommonConstant.MESSAGE_EN);
            enMsgMap = Collections.unmodifiableMap(enMsgMap);
        }
        if (jpMsgMap.isEmpty()) {
            jpMsgMap = loadMessageData(CommonConstant.MESSAGE_JP);
            jpMsgMap = Collections.unmodifiableMap(jpMsgMap);
        }
    }

    /**
     * Get message value
     *
     * @author HungVT
     * @param key The key to get message string
     * @param language The type of languge, "en" for English and "jp" for Japanese 
     * @return string in properties file. if get msg failure then return key
     *
     */
    public static String getMessageContent(String key, String language) {
        String msgResult = null;
        // Check two map not exist or key invalid
        if ((enMsgMap == null && jpMsgMap == null) || key == null) {
            return key;
        }

        // get message with prefix
        if (CommonConstant.LANGUAGE_EN.equals(language)) {
            msgResult = (String) enMsgMap.get(key);
        } else if (CommonConstant.LANGUAGE_JP.equals(language)) {
            msgResult = (String) jpMsgMap.get(key);
        }

        // if get msg failure then return key
        if (msgResult == null) {
            msgResult = key;
        }
        return msgResult;
    }

    /**
     * Load message config
     *
     * @author HungVT
     * @param fileName specified file to load message
     * @return HashMap contains messages info
     */
    private static HashMap<String, String> loadMessageData(String fileName) {
        ResourceBundle resourceBundle = null;
        String key = null;
        String value = null;
        HashMap<String, String> messageMap = new HashMap<String, String>();
        try {
            // get resource bundle
            File file = new File(CommonConstant.PATH_FOLDER_CONFIG);
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            resourceBundle = ResourceBundle.getBundle(fileName,Locale.getDefault(),loader);

            // load all message in file
            Enumeration<String> en  = (Enumeration<String>) resourceBundle.getKeys();
            while (en.hasMoreElements()) {
                key = (String) en.nextElement();
                value = resourceBundle.getString(key);
                messageMap.put(key, value);
            }
        } catch (MissingResourceException e) {
            log.error(e.getMessage());
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
        return messageMap;
    }
}
