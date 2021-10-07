package com.droolsboot.model;

import lombok.Data;
import org.kie.api.KieBase;
import org.kie.api.runtime.StatelessKieSession;

import static com.droolsboot.service.NewKieBase.rulekieBase;

/**
 * 此类是Drools的工具类，用来存放规则库、规则会话、规则内容等，使其更方便地管理和使用。
 * @author liaowenhui
 */
@Data
public class PromoteExecute {
    /**
     * 促销编号
     */
    private String promoteCode;
    /**
     *业务Kbase
     */
    private KieBase workKbase;
    /**
     * 业务session
      */
    private StatelessKieSession workSession;
    /**
     * 规则内容
     */
    private String workContent;

    private String promoteName;

    public KieBase getWorkKbase() {
        if (this.workKbase == null) {
            this.setWorkKbase();
        }
        return workKbase;
    }

    public void setWorkKbase() {
        this.workKbase = rulekieBase(this.getWorkContent());
    }

    public StatelessKieSession getWorkSession() {
        if (this.workSession == null) {
            this.setWorkSession();
        }
        return workSession;
    }

    /**
     * 创建无状态的KieSession
     * 说明：kieSession是一个与Drools引擎打交道的会话，基于KieBase创建，它包括运行时数据以及Fact事实对象，
     * 并对运行时数据事实进行规则运算。通过KieContainer创建KieSession是一种较为方便的做法，
     * 其本质上是从KieBase中创建出来的，且默认是一个有状态的。分为有状态与无状态两种KieSession。
     */
    public void setWorkSession() {
        //通过KieBase创建KieSession
        if (null != this.getWorkKbase()) {
            this.workSession = this.getWorkKbase().newStatelessKieSession();
        }
    }
}
