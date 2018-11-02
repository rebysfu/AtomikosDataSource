package com.f.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : caibi
 * private static final int PayType_Apple = 5;
 * public static final int PayType_Alipay = 222;
 * public static final int PayType_FixedAlipay = 223;
 * public static final int PayType_Wechat = 300;
 * public static final int PayType_QQpay = 777;
 * public static final int PayType_Unionpay = 888;
 * public static final int PayType_Bankpay = 198;
 * public static final int PayType_Jdpay = 666;
 * public static final int PayType_VipAlipay = 233;
 * public static final int PayType_RechargeCardpay = 266;
 * public static final int PayType_Yotapay = 145;
 */
public enum PayType {

    Apple(5, "苹果5"),
    Alipay(222, "支付宝222"),
    FixedAlipay(223, "固额支付宝223"),
    Wechat(300, "微信300"),
    QQpay(777, "QQ钱包777"),
    Unionpay(888, "银联支付888"),
    Bankpay(198, "BANKPAY198"),
    Jdpay(666, "京东支付666"),
    VipAlipay(233, "VIP支付宝233"),
    RechargeCardpay(266, "充值卡266"),
    Yotapay(145, "yota145"),

    ;

    private static final Map<Integer, PayType> CACHE = new HashMap<>();
    private static final Map<Integer, String> map = Maps.newHashMap();
    private static final List<Map<String, Object>> mapList = Lists.newArrayList();

    static {
        for (PayType type : PayType.values()) {
            CACHE.put(type.value, type);
            map.put(type.value, type.desc);
            mapList.add(type.toMap());
        }
    }

    private final Integer value;
    private final String desc;

    PayType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static PayType valueOf(int value) {
        return CACHE.get(value);
    }

    public static Map<Integer, String> getMap() {
        return map;
    }

    public static List<Map<String, Object>> getMapList() {
        return mapList;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("value", this.getValue());
        map.put("desc", this.getDesc());
        return map;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}
