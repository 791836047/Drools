package com.droolsboot.service;


import com.droolsboot.model.PromoteExecute;
import com.droolsboot.model.RuleResult;
import org.kie.internal.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.*;

/**
 * describe: 规则工具类,将规则操作单独写一个工具类，方便扩展和管理
 *
 * @author laizhihui
 */
public class DrlExecute {
    private static DecimalFormat df = new DecimalFormat("######0.00");
    protected static Logger logger = LoggerFactory.getLogger(DrlExecute.class);

    /**
     * 判断购物车的所有商品所参加的活动
     */
    static RuleResult rulePromote(PromoteExecute promoteExecute, Double moneySum) {
        //判断业务规则是否存在
        RuleResult ruleresult = new RuleResult();
        //返回优惠前的价格
        ruleresult.setMoneySum(moneySum);
        logger.info("优惠前的价格为:" + moneySum);
        //统计完成后再将参数insert到促销规则中
        //TODO 这里为什么需要批量执行？
        List cmdCondition = new ArrayList<>();
        cmdCondition.add(CommandFactory.newInsert(ruleresult));
        promoteExecute.getWorkSession().execute(CommandFactory.newBatchExecution(cmdCondition));
        logger.info("优惠后的价格为:" + ruleresult.getFinallyMoney());
        return ruleresult;
    }
}
