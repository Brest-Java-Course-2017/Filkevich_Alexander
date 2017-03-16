package com.epam.project.rest;

import com.epam.project.model.Wagon;
import com.epam.project.service.WagonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by master on 16.3.17.
 */
 @CrossOrigin
 @RestController
 @RequestMapping(value = "/wagon")
public class WagonRestController {
    private static final Logger LOGGER= LogManager.getLogger();

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrestDataError() {
        return "{  \"response\" : \"Incorrect Data Error\' }";
    }


    @Autowired
    private WagonService wagonService;
    
//curl -v localhost:8088/wagon/get/14176
    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Wagon getWagonById(@PathVariable(value = "id") Integer id){
        return wagonService.getWagonById(id);
    }



}
