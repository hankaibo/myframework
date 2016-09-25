/**
 * Copyright © 2015.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>User: hankaibo 
 * <p>Date: 2016/9/26
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/api/test")
public class TestController {
    @ResponseBody
    @RequestMapping(value = "/show", method= RequestMethod.POST)
    @ApiOperation(value="测试接口", notes="测试接口详细描述")
    public String show(
            @ApiParam(required=true, name="name", value="姓名")
            @RequestParam(name = "name") String stuName){
        return "success";
    }

}
