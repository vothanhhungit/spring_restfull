/**
 * 
 */
package com.spring.web.service;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.spring.web.dao.CustomerDAO;

/**
 * Service interface handling service For Example
 * @author HungVT
 *
 */
public interface TestService {
    
    public default String getList() throws JsonParseException, JsonMappingException, IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("Datas", customerDAO.list());
        return gson.toJson(map);
    };
}
