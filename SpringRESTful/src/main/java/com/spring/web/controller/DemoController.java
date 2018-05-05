package com.spring.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.constant.ControllerConstant;
import com.spring.web.dao.CustomerDAO;
import com.spring.web.service.TestService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

@RestController
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);
    
    @Autowired
//    private TestService testService;
    private CustomerDAO customerDAO;
    /**
     * Get Datas
     * 
     * @author HungVT
     * @param request
     * @return json string of all Client or message error
     */
    @RequestMapping(value = ControllerConstant.DATA, method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String GetData(HttpServletRequest httpRequest) throws JsonMappingException, IOException {
        Map<String, Object> mapConditions = new HashMap<>();
        mapConditions.put("cfreeword", httpRequest.getParameter("id"));
        mapConditions.put("cpid", httpRequest.getParameter("cpid"));
        CustomerDAO customerDAO = new CustomerDAO();
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("Datas", customerDAO.list());
        return gson.toJson(map);
    }
//    @GetMapping("/data")
//    public ResponseEntity getCustomers() {
//        return new ResponseEntity("Wellcom to Hello World!", HttpStatus.OK);
//    }

//  @GetMapping("/customers/{id}")
//  public ResponseEntity getCustomer(@PathVariable("id") Long id) {
//
//      Customer customer = customerDAO.get(id);
//      if (customer == null) {
//          return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//      }
//
//      return new ResponseEntity(customer, HttpStatus.OK);
//  }
//
//  @PostMapping(value = "/customers")
//  public ResponseEntity createCustomer(@RequestBody Customer customer) {
//
//      customerDAO.create(customer);
//
//      return new ResponseEntity(customer, HttpStatus.OK);
//  }
//
//  @DeleteMapping("/customers/{id}")
//  public ResponseEntity deleteCustomer(@PathVariable Long id) {
//
//      if (null == customerDAO.delete(id)) {
//          return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//      }
//
//      return new ResponseEntity(id, HttpStatus.OK);
//
//  }
//
//  @PutMapping("/customers/{id}")
//  public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//
//      customer = customerDAO.update(id, customer);
//
//      if (null == customer) {
//          return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//      }
//
//      return new ResponseEntity(customer, HttpStatus.OK);
//  }

}