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
     * 创建KieBase
     * KieBase是一个规则库，包含若干的规则、流程、函数等，Kie本身并不包含运行时的相关数据，如果需要执行KieBase中的规则，
     * 就需要根据KieBase创建KieSession。一般情况都是直接根据KieContainer创建的。KieBase可以对规则进行基本操作，如删除规则、删除查询、删除函数等。
     * @param rule
     * @return
     */
    public static KieBase rulekieBase(String rule) {
        KieHelper helper = new KieHelper();
        try {
            helper.addContent(rule, ResourceType.DRL);
            //底层根据KieContainer创建
            return helper.build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("规则初始化失败");
        }
    }
}
