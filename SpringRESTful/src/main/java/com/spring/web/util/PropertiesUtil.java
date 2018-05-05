package com.spring.web.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.web.constant.CommonConstant;

/**
 * Properties load
 *
 */
public class PropertiesUtil {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    public static final Properties env = getProperties("application.properties");
    public static final Properties db_master = getProperties("dbconfig.properties");
    public static final Properties db_slave = getProperties("dbconfig_slave.properties");

    public static Properties getProperties(String filename) {
        String propertiesLocation = CommonConstant.PATH_FOLDER_CONFIG + filename;
        Properties prop = new Properties();
        try {
            InputStream inStream = new FileInputStream(propertiesLocation);
            prop.load(inStream);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return prop;
    }
}