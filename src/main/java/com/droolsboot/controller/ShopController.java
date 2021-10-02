package com.droolsboot.controller;

import com.droolsboot.service.PromoteEdieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

/**
 * @author liaowenhui
 */
@Controller
@RequestMapping("/promotion")
public class ShopController {

    @Autowired
    private PromoteEdieService promoteEdieService;

    @RequestMapping("/greeting")
    public String greeting() {
        return "index";
    }

    @RequestMapping("/shop")
    public String shop() {
        return "shopping";
    }

    /**
     * 促销活动--添加优惠劵
     */
    @RequestMapping(value = "/ediePromote", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String ediePromote(String money, String ruleName) {
        promoteEdieService.ediePromomteMap(money, ruleName);
        return "添加优惠券成功";
    }

    /**
     * 购物车
     *
     * @return 返回结果
     */
    @RequestMapping(value = "/toShopping", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> toShopping(String money) {
        return promoteEdieService.toShopping(money);
    }

}
