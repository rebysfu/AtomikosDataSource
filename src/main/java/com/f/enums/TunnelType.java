package com.f.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 支付通道类型
 *
 * @author : caibi
 * @date : 2018/8/16 上午11:28
 */
public enum TunnelType {

    APPLE("苹果支付(未使用)"),
    TMP("测试(未使用)"),
    COM("COM支付"),
    COM2("COM支付(快捷/扫码)"),
    DANDAN("蛋蛋支付"),
    FLASH("FLASH"),
    HEALI2("和支付宝2"),
    HYWAP("黑游支付宝H5"),
    HEALI("和支付宝"),
    HEWX("和微信"),
    WBALI("玩贝支付宝(汇通)"),
    HUIZHI("汇至支付"),
    JX("JX"),
    KAIXIN("开心支付"),
    LESHANG("乐尚"),
    MINGTIAN("明天支付"),
    NF2("新闪2"),
    SJDL("SJDL"),
    WAFFLE("闪付6"),
    WB("玩贝"),
    WEITENEW("维特新通道"),
    WEITE("维特"),
    XBPAY("西部支付"),
    WBWX("玩贝微信"),
    XIAOMI("小米"),
    XINSJDL("闪5"),
    XINXIN("新新闪付"),
    YISHAN("一闪"),
    YOTA("YOTA"),
    ;

    private static final Map<Integer, TunnelType> CACHE = Maps.newHashMap();
    private static final Map<Integer, String> map = Maps.newHashMap();
    private static final List<Map<String, Object>> mapList = Lists.newArrayList();

    static {
        for (TunnelType type : TunnelType.values()) {
            CACHE.put(type.ordinal(), type);
            map.put(type.ordinal(), type.name);
            mapList.add(type.toMap());
        }
    }

    private final String name;


    TunnelType(String name) {
        this.name = name;
    }

    public static final TunnelType valueOf(int ordinal) {
        return CACHE.get(ordinal);
    }

    public static final Map<Integer, String> getMap() {
        return map;
    }

    public static List<Map<String, Object>> getMapList() {
        return mapList;
    }

    public static final Map<Integer, TunnelType> getCache() {
        return CACHE;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("ordinal", this.ordinal());
        map.put("name", this.getName());
        return map;
    }

    public String getName() {
        return name;
    }

}
