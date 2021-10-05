# Drools整合SpringBoot 针对电商平台优惠券的动态添加。
 
说明：
（1）规则内容通过StringTemplate模板拼接后存放在数据库中，为规则库的构建设定基础。

注意点：
（1）使用Drools时，不要使用Spring Boot的热部署，会造成规则库生成失败，经常出现的空指针问题。
其原因是规则库在构建时会在系统数据库中生成一个.class的规则类文件，这时Spring Boot会认为项目发生了变化，
热部署会被激活。从而将已经加载或者初始化好的规则库再次清空或再次初始化。

拼接的规则示例：
package com.promote

import	com.droolsboot.model.RuleResult;
rule "14元优惠券"
	no-loop true
		when
		    $r:RuleResult(true)
		then
           modify($r){
                setPromoteName(drools.getRule().getName()),
                setFinallyMoney($r.getMoneySum() - 14.0)
           }
end
