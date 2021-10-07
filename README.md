# Drools整合SpringBoot 针对电商平台优惠券的动态添加。
 
一、说明/介绍：
（1）本项目中规则内容通过StringTemplate模板拼接后存放在数据库中，为规则库的构建设定基础。
（2）Drools规则引擎中传递的数据，术语称为Fact对象。本项目中的RuleResult就是Fact对象。Fact对象是一个普通的JavaBean(可以是任何Object对象)，
规则体中可以对当前对象进行任何的读/写操作，调用该对象的方法。通俗地把Fact对象理解为规则与应用系统交互的桥梁或通道。
（3）本项目中的KieSession是无状态的，无状态调用规则从Java调用execute()开始。该方法是一次性执行，
它将在内部实例化KieSession，并调用fireAllRules()方法，然后在finally中调用dispose()方法。
所以在execute()之后不能再调用会话的其他操作。

二、注意点：
（1）使用Drools时，不要使用Spring Boot的热部署，会造成规则库生成失败，经常出现的空指针问题。
其原因是规则库在构建时会在系统数据库中生成一个.class的规则类文件，这时Spring Boot会认为项目发生了变化，
热部署会被激活。从而将已经加载或者初始化好的规则库再次清空或再次初始化。

三、拼接的规则示例：
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

四、规则构建过程
kieService —创建—> KieContainer —创建—> KieBase —创建—> KieSession
（1）kieService说明
KieService是一个接口，可以对Kie进行构建和使用，如可以获取KieContainer。
（2）KieContainer说明
KieContainer是一个接口，是所有KieBase的容器，可以很方便地操作KieBase和KieSession。
（3）KieBases说明
KieBase是一个规则库，包含若干的规则、流程、函数等，Kie本身并不包含运行时的相关数据，
如果需要执行KieBase中的规则，就需要根据KieBase创建KieSession。
一般情况都是直接根据KieContainer创建的。KieBase可以对规则进行基本操作，如删除规则、删除查询、删除函数等。
（4）KieSession说明
kieSession就是一个与Drools引擎打交道的会话，基于KieBase创建，它会包括运行时数据以及Fact事实对象，
并对运行时数据事实进行规则运算。通过KieContainer创建KieSession是一种较为方便的做法，
其本质上是从KieBase中创建出来的，且默认是一个有状态的。分为有状态与无状态两种KieSession。