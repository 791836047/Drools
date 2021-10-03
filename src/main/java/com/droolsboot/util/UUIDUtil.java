package com.droolsboot.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;
import java.util.Date;
import java.util.UUID;

import static com.droolsboot.util.LossMoneyTemplate.WORK_MONEY_ST;


/**
 * @author liaowenhui
 */
public class UUIDUtil {


    /**
     * 生成原始UUID
     *
     * @return
     */
    private static String UUIDString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    /**
     * 生成格式化UUID
     *
     * @return
     */
    public static String UUIDFormatString(String replace) {
        String uuid = UUID.randomUUID().toString().replaceAll(replace, "");
        return uuid;
    }

    /**
     * 值加密
     *
     * @return
     */
    public static String MD5AndUUID() {
        //时间戳
        String timeJab = String.valueOf(System.currentTimeMillis());
        //UUID+时间戳
        String concat = UUIDString().concat(timeJab);
        return DigestUtils.md5Hex(concat);
    }

    /**
     * 生成不重复的优惠券编码
     *
     * @return
     */
    public static String typeJoinTime() {
        String dateNowStr = StringJointUtil.dateToStringThree(new Date());
        int math = (int) ((Math.random() * 9 + 1) * 1000000);
        return dateNowStr.concat(Integer.toString(math));

    }

    /**
     * 使用StringTemplate模板拼接规则内容
     * @param json
     * @return
     */
    public static String rule(String json) {
        return ruleWordExchangsST(json);
    }

    /**
     * 规则业务生成
     */
    public static String ruleWordExchangsST(String json) {
        STGroup group = new STGroupString(WORK_MONEY_ST);
        ST stFile = group.getInstanceOf("wordImport");
        ST stRule = group.getInstanceOf("ruleValue");

        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject condition = jsonObject.getJSONObject("condition");
        JSONObject action = jsonObject.getJSONObject("action");
        JSONObject rule = jsonObject.getJSONObject("rule");
        stRule.add("condition", condition);
        stRule.add("action", action);
        stRule.add("rule", rule);
        stFile.add("rules", stRule);
        String result = stFile.render();
        return result;
    }
}
