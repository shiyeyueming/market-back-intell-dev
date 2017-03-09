package com.zity.intell.dev.service.impl;

import com.zity.intell.dev.dao.IIntellLogDao;
import com.zity.intell.dev.domain.IntellLogDomain;
import com.zity.intell.dev.service.IIntellLogService;
import com.zity.intell.dev.tool.DateUtils;
import com.zity.intell.dev.tool.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/8.
 */
@Service("intellLogService")
public class IntellLogServiceImpl implements IIntellLogService {
    @Autowired
    private IIntellLogDao intellLogDao;
    /**
     * 全局访问前缀
     */
    @Value("${app-prefix}")
    private String appPrefix;

    @Override
    public String findAll(Map<String, Object> retParam, int page, final String devNo) {

        Pageable pageable = new PageRequest(page-1,2);
        Page<IntellLogDomain> list = null;

        try {
            list = intellLogDao.findAll(new Specification<IntellLogDomain>() {

                public Predicate toPredicate(Root<IntellLogDomain> root,
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
        retParam.put("intellLogDomains", list);
        retParam.put("size", list.getTotalElements());
        return "intellLogMain.html";
    }

    @Override
    public IntellLogDomain findOneById(long id) {
        return intellLogDao.findOne(id);
    }

    @Override
    public String add(String devNo,String status,String belongNo) {
        String[] arr = {devNo,status,belongNo};

        if(!StringUtil.check(arr)) {
            return "false";
        }
        IntellLogDomain intellLogDomain=new IntellLogDomain();
        intellLogDomain.setGmtCreate(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellLogDomain.setGmtModified(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellLogDomain.setDevNo(devNo);
        intellLogDomain.setStatus(Byte.parseByte(status));
        intellLogDomain.setBelongNo(belongNo);
        try {
            intellLogDao.save(intellLogDomain);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

        return "true";
    }

    @Override
    public String update(String id,String gmtCreate,String devNo,String status,String belongNo) {
        String[] arr = {devNo,status,belongNo};

        if(!StringUtil.check(arr)) {
            return "false";
        }

        IntellLogDomain intellLogDomain=new IntellLogDomain();
        intellLogDomain.setId(Long.valueOf(id));
        intellLogDomain.setGmtCreate(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellLogDomain.setGmtCreate(DateUtils.getDateTime(gmtCreate));
        intellLogDomain.setGmtModified(DateUtils.getDateByFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        intellLogDomain.setDevNo(devNo);
        intellLogDomain.setStatus(Byte.parseByte(status));
        intellLogDomain.setBelongNo(belongNo);
        try {
            intellLogDao.save(intellLogDomain);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

        return "true";
    }

    @Override
    public String delete(String id) {
        try {
            intellLogDao.updateStatus(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }
}
