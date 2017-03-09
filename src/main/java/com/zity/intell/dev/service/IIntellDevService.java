package com.zity.intell.dev.service;

import com.zity.intell.dev.domain.IntellDevDomain;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by lenovo on 2017/3/8.
 */

public interface IIntellDevService {

    String findAll(Map<String, Object> retParam, int page, String devNo);
    IntellDevDomain findOneById(long id);
    public String add(String devNo,String status,String creator,String belongNo);
    public String update(String id,String gmtCreate,String devNo,String status,String creator,String belongNo);
    String delete(String id);

}
