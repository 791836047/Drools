package com.droolsboot.service;


import com.droolsboot.model.PromoteExecute;
import org.springframework.stereotype.Service;

/**
 * describe: 查询业务类
 * @author liaowenhui
 */

@Service
public class PromoteNeaten {

    /**
     * 初始化指定的规则
     *
     * @param rule 促销实体数据
     * @return 返回值
     */
    public PromoteExecute editRule(String rule) throws RuntimeException {
        PromoteExecute promoteExecute = new PromoteExecute();
        //促销业务规则
        promoteExecute.setWorkContent(rule);
        //规则库 初始化
        promoteExecute.getWorkSession();
        return promoteExecute;
    }
}
