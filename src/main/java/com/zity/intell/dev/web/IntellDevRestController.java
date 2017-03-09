package com.zity.intell.dev.web;

import com.zity.intell.dev.domain.IntellDevDomain;
import com.zity.intell.dev.service.IIntellDevService;
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
@RequestMapping("/devApi")
public class IntellDevRestController {


    @Autowired
    private IIntellDevService iIntellDevService;



    @RequestMapping("/add")
    public String add( @RequestParam(required = false) String devNo,  @RequestParam(required = false) String status,
                       @RequestParam(required = false) String creator, @RequestParam(required = false) String belongNo) {

        return iIntellDevService.add(devNo,status,creator,belongNo);

    }

    @RequestMapping("/updateIntellDev")
    public String updateIntellDev(@RequestParam(required = false) String id,@RequestParam(required = false)String gmtCreate,@RequestParam(required = false) String devNo,  @RequestParam(required = false) String status,
                                  @RequestParam(required = false) String creator, @RequestParam(required = false) String belongNo) {

        return iIntellDevService.update(id,gmtCreate,devNo,status,creator,belongNo);

    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(required = false) String id) {

        return iIntellDevService.delete(id);

    }
}
