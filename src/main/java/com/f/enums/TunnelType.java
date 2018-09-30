package com.f.enums;

import com.google.common.collect.Maps;

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

    private static Map<Integer, TunnelType> CACHE = Maps.newHashMap();
    private static Map<TunnelType, String> TYPE2NAME = Maps.newHashMap();

    static {
        for (TunnelType type : TunnelType.values()) {
            CACHE.put(type.ordinal(), type);
            TYPE2NAME.put(type, type.name);
        }
    }

    private final String name;

    private TunnelType(String name) {
        this.name = name;
    }

    public static final TunnelType valueOf(int ordinal) {
        return CACHE.get(ordinal);
    }

    public static final Map<TunnelType, String> getType2Name() {
        return TYPE2NAME;
    }

    public static final Map<Integer, TunnelType> getCache() {
        return CACHE;
    }

    public String getName() {
        return name;
    }

}
