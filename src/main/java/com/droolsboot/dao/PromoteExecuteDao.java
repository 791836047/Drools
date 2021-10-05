package com.droolsboot.dao;

import com.droolsboot.model.PromoteExecute;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoteExecuteDao {
    /**
     * 插入优惠券信息表
     */
    int insertPromoteExecute(PromoteExecute promote);
}
