package com.zity.intell.dev.dao;

import com.zity.intell.dev.domain.IntellLogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

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


public interface IIntellLogDao extends PagingAndSortingRepository<IntellLogDomain,Long>,JpaSpecificationExecutor<IntellLogDomain> {

    @Override
    IntellLogDomain save(IntellLogDomain entity);

    @Override
    Page<IntellLogDomain> findAll(Specification<IntellLogDomain> spec, Pageable pageable);

    @Override
    IntellLogDomain findOne(Long id);

    @Transactional
    @Modifying
    @Query("update intell_log set status=0 where id=?1")
    void updateStatus(Long id);



}
