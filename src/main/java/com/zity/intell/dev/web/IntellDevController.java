package com.zity.intell.dev.web;

import com.zity.intell.dev.domain.IntellDevDomain;
import com.zity.intell.dev.service.IIntellDevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/intellDev")
public class IntellDevController {
    @Autowired
    private IIntellDevService iIntellDevService;

    private static int COFFEE_PAGE_SIZE = 10;
    @Value("${app-prefix}")
    private String appPrefix;

    @RequestMapping("/index")
    public ModelAndView getIndex(Map<String, Object> retParam, HttpServletRequest request) {

        return new ModelAndView( "forward:/intellDev/index/1.html");
    }

    @RequestMapping("/index/{page}.html")
    public String index(Map<String, Object> retParam, @PathVariable int page, String devNo){

        return iIntellDevService.findAll(retParam, page, devNo);
    }
    @RequestMapping("/add")
    public String add(Map<String, Object> retParam, HttpServletRequest request) {

        retParam.put("appPrefix", appPrefix);
        retParam.put("operate", "add");
        return "addIntellDev.html";
    }

    @RequestMapping("/update")
    public String update(Map<String, Object> retParam,String id) {
        IntellDevDomain intellDevDomain = iIntellDevService.findOneById(Long.parseLong(id));
       retParam.put("appPrefix", appPrefix);
        retParam.put("operate", "update");
        retParam.put("intellDevDomain", intellDevDomain);
         return "updateIntellDev.html";



    }

    @RequestMapping("/select")
    public String select(Map<String, Object> retParam, String id) {
        IntellDevDomain intellDevDomain = iIntellDevService.findOneById(Long.parseLong(id));
        retParam.put("appPrefix", appPrefix);
        retParam.put("operate", "select");
        retParam.put("intellDevDomain", intellDevDomain);
        return "updateIntellDev.html";

    }

}
