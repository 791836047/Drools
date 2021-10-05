package com.droolsboot.service;

import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;

/**
 * 此类用来初始化规则库，是KIE的一个工具类。这里对KieHelper进行一个引用和简单封装。
 * @author liaowenhui
 */
public class NewKieBase {
    /**
     * 将业务规则写到规则库中
     * @param rule
     * @return
     */
    public static KieBase rulekieBase(String rule) {
        KieHelper helper = new KieHelper();
        try {
            helper.addContent(rule, ResourceType.DRL);
            return helper.build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("规则初始化失败");
        }
    }
}
