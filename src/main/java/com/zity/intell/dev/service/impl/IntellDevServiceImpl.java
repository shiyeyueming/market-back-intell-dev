package com.zity.intell.dev.service.impl;

import com.zity.intell.dev.dao.IIntellDevDao;
import com.zity.intell.dev.domain.IntellDevDomain;
import com.zity.intell.dev.service.IIntellDevService;
import com.zity.intell.dev.tool.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.zity.intell.dev.tool.StringUtil;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/8.
 */
@Service("intellDevService")
public class IntellDevServiceImpl implements IIntellDevService{
    @Autowired
    private IIntellDevDao intellDevDao;
    /**
     * 全局访问前缀
     */
    @Value("${app-prefix}")
    private String appPrefix;

    @Override
    public String findAll(Map<String, Object> retParam, int page, final String devNo) {

        Pageable pageable = new PageRequest(page-1,2);
        Page<IntellDevDomain> list = null;

        try {
            list = intellDevDao.findAll(new Specification<IntellDevDomain>() {

                public Predicate toPredicate(Root<IntellDevDomain> root,
                                             CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Path<String> devNoPath = root.get("devNo");
                    Path<String> statusPath = root.get("status");
                    /**
                     * 连接查询条件, 不定参数，可以连接0..N个查询条件
                     */
                    if(devNo == null || StringUtils.isEmpty(devNo)) {

                        query.where(cb.equal(statusPath,1)); //这里可以设置任意条查询条件
                    } else {
                        query.where(cb.like(devNoPath, "%"+devNo+"%"),cb.equal(statusPath,1)); //这里可以设置任意条查询条件
                    }
                    return null;
                }

            }, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        retParam.put("appPrefix", appPrefix);
        retParam.put("devNo", (devNo == null || StringUtils.isEmpty(devNo))?"":devNo);
        retParam.put("intellDevDomains", list);
        retParam.put("size", list.getTotalElements());
        return "intellDevMain.html";
    }

    @Override
    public IntellDevDomain findOneById(long id) {
        return intellDevDao.findOne(id);
    }

    @Override
    public String add(String devNo,String status,String creator,String belongNo) {
        String[] arr = {devNo,status,creator,belongNo};

        if(!StringUtil.check(arr)) {
            return "false";
        }
        IntellDevDomain intellDevDomain=new IntellDevDomain();
        intellDevDomain.setGmtCreate(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellDevDomain.setGmtModified(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellDevDomain.setDevNo(devNo);
        intellDevDomain.setStatus(Byte.parseByte(status));
        intellDevDomain.setCreator(creator);
        intellDevDomain.setBelongNo(belongNo);
        try {
            intellDevDao.save(intellDevDomain);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

        return "true";
    }

    @Override
    public String update(String id,String gmtCreate,String devNo,String status,String creator,String belongNo) {
        String[] arr = {devNo,status,creator,belongNo};

        if(!StringUtil.check(arr)) {
            return "false";
        }

        IntellDevDomain intellDevDomain=new IntellDevDomain();
        intellDevDomain.setId(Long.valueOf(id));
        intellDevDomain.setGmtCreate(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellDevDomain.setGmtCreate(DateUtils.getDateTime(gmtCreate));
        intellDevDomain.setGmtModified(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellDevDomain.setDevNo(devNo);
        intellDevDomain.setStatus(Byte.parseByte(status));
        intellDevDomain.setCreator(creator);
        intellDevDomain.setBelongNo(belongNo);
        try {
            intellDevDao.save(intellDevDomain);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

        return "true";
    }

    @Override
    public String delete(String id) {
        try {
            intellDevDao.updateStatus(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }
}
