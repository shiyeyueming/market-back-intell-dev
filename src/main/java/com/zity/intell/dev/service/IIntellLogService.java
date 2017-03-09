package com.zity.intell.dev.service;

import com.zity.intell.dev.domain.IntellDevDomain;
import com.zity.intell.dev.domain.IntellLogDomain;

import java.util.Map;

/**
 * Created by lenovo on 2017/3/8.
 */

public interface IIntellLogService {

    String findAll(Map<String, Object> retParam, int page, String devNo);
    IntellLogDomain findOneById(long id);
    public String add(String devNo, String status, String belongNo);
    public String update(String id, String gmtCreate, String devNo, String status,  String belongNo);
    String delete(String id);

}
