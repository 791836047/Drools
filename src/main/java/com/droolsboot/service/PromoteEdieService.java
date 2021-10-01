package com.droolsboot.service;


import com.alibaba.fastjson.JSONObject;
import com.droolsboot.dao.PromoteExecuteDao;
import com.droolsboot.model.PromoteExecute;
import com.droolsboot.model.RuleResult;
import com.droolsboot.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类是业务层的服务器，主要负责生成优惠卷、使用优惠卷、计算结果等。
 * @author liaowenhui
 */
@Service
public class PromoteEdieService {

    @Autowired
    private PromoteExecuteDao promoteExecuteDao;
    @Autowired
    private PromoteNeaten promoteNeaten;

    private Map<String, PromoteExecute> promoteExecuteMap;

    /**
     * 生成优惠券
     */
    @Transactional(rollbackFor = Exception.class)
    public void ediePromomteMap(String money, String rulename) {
        if (this.promoteExecuteMap == null) {
            promoteExecuteMap = new HashMap<>();
        }
        PromoteExecute promoteExecute = new PromoteExecute();
        double v = Double.parseDouble(money);
        String rule = UUIDUtil.rule(ruleWorkMap(rulename, v));
        String promoteCode = UUIDUtil.typeJoinTime();
        promoteExecute.setPromoteCode(promoteCode);
        promoteExecute.setWorkContent(rule);
        promoteExecute.setPromoteName(rulename);
        //插入优惠券
        int i = promoteExecuteDao.insertPromoteExecute(promoteExecute);
        if (i > 0) {
            PromoteExecute execute = promoteNeaten.editRule(rule);
            this.promoteExecuteMap.put(promoteCode, execute);
        }
    }

    /**
     * 购物车计算
     *
     * @return
     */
    public Map<String, Object> toShopping(String moneySum) {
        //购物车请求信息
        Map<String, Object> map = new HashMap<>();
        double v = Double.parseDouble(moneySum);
        List<Object> pn = new ArrayList<>();
        if (this.promoteExecuteMap != null) {
            //证明有优惠券
            for (Map.Entry<String, PromoteExecute> codes : this.promoteExecuteMap.entrySet()) {
                RuleResult ruleResult = DrlExecute.rulePromote(codes.getValue(), v);
                v = ruleResult.getFinallyMoney();
                pn.add(ruleResult);
            }
        }
        map.put("原价为",moneySum);
        map.put("最终支付金额为:",v);
        map.put("使用的优惠券如下:", pn);
        return map;
    }

    /**
     * 组合业务规则Json方法
     *
     * @return 结果
     */
    private String ruleWorkMap(String name, Double money) {
        Map<String, Object> map = new HashMap<>();
        //组合Rule部分
        Map<String, Object> rule = new HashMap<>();
        rule.put("name", name);
        map.put("rule", rule);
        //组合 规则When部分
        Map<String, Object> when = new HashMap<>();
        map.put("condition", when);
        //组合 规则Then部分
        Map<String, Object> then = new HashMap<>();
        then.put("money", money);
        map.put("action", then);
        //组合规则When And Then 部分
        return JSONObject.toJSONString(map);
    }
}