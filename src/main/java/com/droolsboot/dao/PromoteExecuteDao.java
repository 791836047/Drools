package com.droolsboot.dao;

import com.droolsboot.model.PromoteExecute;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoteExecuteDao {
    /**
     * 插入促销基础信息
     */
    int insertPromoteExecute(PromoteExecute promote);
}
