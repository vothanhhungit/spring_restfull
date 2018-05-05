package com.spring.web.connect;

/**
 * sql queries
 * 
 * @author HungVT
 */
public class SqlQuery {
    public static final String OPTION_PID = "'$.\"Option.P_Id\"'";
    public static final String PHASE = "JSON_EXTRACT(PH.Phase, " + OPTION_PID + ")";

    // table name
    public static String JOB_TBL = "JOBTBL";

    // query get field type = JSON
    public static String GET_FIELD_TYPE_JSON_JOB = "SHOW COLUMNS FROM " + JOB_TBL + " WHERE TYPE IN ('json')";

}
