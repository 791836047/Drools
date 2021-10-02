package com.droolsboot.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * describe: 返回值实体
 *
 * @author liaowenhui
 */
@Data
public class RuleResult {
    /**
     * 参活商品优惠后的价格
     */
    private double finallyMoney;

    /**
     * 返回优惠前的价格
     */
    private double moneySum;

    /**
     * 促销活动名称
     */
    private List<String> promoteName = new ArrayList<>();

    public List<String> getPromoteName() {
        return promoteName;
    }

    public void setPromoteName(String promoteName) {
        this.promoteName.add(promoteName);
    }
}
