package com.spring.web.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.spring.web.constant.CommonConstant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;

/**
 * All of common methods in project
 * 
 * @author HungVT
 */
public class CommonUtils {

    /**
     * get current date time with timezone UTC
     * 
     * @author HungVT
     * @return localDateTime
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    /**
     * convert data json Option.P_Id (String -> Long)
     * 
     * @author HungVT
     * @param mapObject
     */
    @SuppressWarnings("unchecked")
    public static void convertOptionIdToLongInDataJson(Map<String, Object> mapObject) {
        if (mapObject == null) {
            return;
        }
        for (Entry<String, Object> entry : mapObject.entrySet()) {
            Object valueObj = entry.getValue();
            if (valueObj instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) valueObj;
                map.put("Option.P_Id", Long.parseLong(map.get("Option.P_Id").toString()));
            }
        }
    }
    
    /**
     * convert date to LocalDateTime
     * 
     * @author HungVT
     * @param sqlTimestamp : Timestamp
     * @return LocalDateTime
     */
    public static LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }


    /**
     * get current page
     * 
     * @author HungVT
     * @param pageNumber
     * @return current page
     */
    public static Integer getCurrentPageByPageNumber(Optional<Integer> pageNumber) {
        return pageNumber.isPresent() ? pageNumber.get() : 1;
    }

   

    /**
     * Convert list to Json String.
     * For example :{"Datas": [{"P_Id": 11559, "P_Name": "demoName"}, {"P_Id": 11559, "P_Name": "demoName"}]}
     * 
     * @author HungVT
     * @param list : list to convert
     * @return string json
     */
    public static String convertDataListToJson(List<?> list) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("Datas", list);
        return gson.toJson(map);
    }

    /**
     * convert Json String to a Map<String, Object> object
     * 
     * @author HungVT
     * @param jsonStr json string
     * @return Map<String, Object> object from jsonStr
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static <T> Map<String, T> convertJsonToMap(String jsonStr) throws JsonParseException, JsonMappingException, IOException {
        if (jsonStr == null || jsonStr.length() == 0) {
            return new HashMap<String, T>();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr.toString(), new TypeReference<Map<String, Object>>(){});
    }

    

    /**
     * Cast a map to a map type of entity, for example the mapInput contains Key "P_Id" => cast to "p_Id" with same Value
     * 
     * @author HungVT
     * @param mapInput the map want to cast
     * @return map have key suitable for an entity
     */
    public static Map<String, Object> castToMapEntity (Map<String, Object> mapInput) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Entry<String, Object> entry : mapInput.entrySet()) {
            String newKey = toLowerFirstChar(entry.getKey());
            Object value = entry.getValue();
            result.put(newKey, value);
        }
        return result;
    }

    /**
     * get current date time by format. For example "yyyy/MM/dd HH:mm:ss"
     * 
     * @author HungVT
     * @param format format string need to get
     * @return string of current datetime
     */
    public static String getCurrentDate(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now(ZoneOffset.UTC).format(formatter);
    }

    /**
     * cast a string to a LocalDate object by a format. For example "yyyy/MM/dd"
     * 
     * @author HungVT
     * @param str
     * @param format
     * @return LocalDate object
     */
    public static LocalDate castStringToLocalDate(String str, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate datetime = null;
        if (str != null && !str.isEmpty()) {
            datetime =  LocalDate.parse(str, formatter);
        }
        return datetime;
    }

    /**
     * cast a string to a LocalDateTime object by a format. For example "yyyy/MM/dd HH:mm:ss"
     * 
     * @author HungVT
     * @param str
     * @param format
     * @return LocalDateTime object
     */
    public static LocalDateTime castStringToLocalDateTime(String str, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime datetime = null;
        if (str != null && !str.isEmpty()) {
            datetime =  LocalDateTime.parse(str, formatter);
        }
        return datetime;
    }

    /**
     * cast first char of an String to lowercase
     * 
     * @author HungVT
     * @param str the string want to cast
     * @return the string has first char is lowercase
     */
    private static String toLowerFirstChar(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * Cast an Object to a Map
     * 
     * @author HungVT
     * @param obj object need to be casted
     * @return Map<String, Object> with keys is field names, values is values of the fields
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> convertObj2Map(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Map<String, Object> result = mapper.convertValue(obj, Map.class);
        return result != null ? result : new HashMap<String, Object>();
    }

    /**
     * Cast a Map to an Object
     * 
     * @author HungVT
     * @param mapData map want to cast to an Object
     * @param beanClass class of the object
     * @return an Object with specification class
     */
    public static <T> T convertMap2Obj(Map<String, Object> mapData, Class<T> beanClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        T obj = mapper.convertValue(mapData, beanClass);
        return obj;
    }

    public String removePrefixFromPorter(String fieldNameFromPorter) {
        return fieldNameFromPorter.substring(fieldNameFromPorter.indexOf("."));
    }
    /**
     * convert data request to List<String>
     * 
     * @author HungVT
     * @param val : data request
     * @return data request/ null if not value request
     */
    public static List<Integer> convertStringToListInteger(String val) {
        List<Integer> lsResult = new ArrayList<>();
        if (val == null || val.length() == 0) {
            return null;
        }
        List<String> lsData = new ArrayList<String>(Arrays.asList(val.split(",")));
        for (String str : lsData) {
            lsResult.add(Integer.parseInt(str.trim()));
        }
        return lsResult;
    }

    /**
     * convert data request to String
     * 
     * @author HungVT
     * @param val : data request
     * @return data request/ null if not value request
     */
    public static String convertToString(String val) {
        if (val == null || val.length() == 0) {
            return null;
        }
        return val;
    }

    /**
     * convert data request to Integer
     * 
     * @author HungVT
     * @param val : data request
     * @return data request/ null if not value request
     */
    public static Integer convertToInteger(String val) {
        if (val == null || val.length() == 0) {
            return null;
        }
        return Integer.parseInt(val);
    }

    /**
     * convert data request to boolean
     * 
     * @author HungVT
     * @param val : data request
     * @return data request/ null if not value request
     */
    public static boolean convertToBoolean(String val) {
        if (val == null || val.length() == 0) {
            return false;
        }
        return val.equals("true");
    }

   

    /**
     * convert XML Node to String
     * 
     * @author HungVT
     * @param node
     * @return String
     * @throws TransformerException
     */
    private static String nodeToString(Node node) throws TransformerException {
        StringWriter sw = new StringWriter();
        Transformer transObj = TransformerFactory.newInstance().newTransformer();
        transObj.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transObj.setOutputProperty(OutputKeys.INDENT, "");
        transObj.transform(new DOMSource(node), new StreamResult(sw));
        return sw.toString();
    }
}
