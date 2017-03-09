package com.zity.intell.dev.web;

import com.zity.intell.dev.service.IIntellDevService;
import com.zity.intell.dev.service.IIntellLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C) 2014-2016 天津紫藤科技有限公司. Co. Ltd. All Rights Reserved.
 *
 * @author hanlijie
 * @version v1
 * @description
 * @serve
 * @module
 * @date 2017/2/23
 * @code
 */

@RestController
@RequestMapping("/logApi")
public class IntellLogRestController {


    @Autowired
    private IIntellLogService iIntellLogService;
    @RequestMapping("/add")
    public String add( @RequestParam(required = false) String devNo,  @RequestParam(required = false) String status, @RequestParam(required = false) String belongNo) {

        return iIntellLogService.add(devNo,status,belongNo);

    }

    @RequestMapping("/updateIntellLog")
    public String updateIntellLog(@RequestParam(required = false) String id,@RequestParam(required = false)String gmtCreate,@RequestParam(required = false) String devNo,  @RequestParam(required = false) String status, @RequestParam(required = false) String belongNo) {

        return iIntellLogService.update(id,gmtCreate,devNo,status,belongNo);

    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(required = false) String id) {

        return iIntellLogService.delete(id);

    }
}
