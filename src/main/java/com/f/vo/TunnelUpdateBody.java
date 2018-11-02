package com.f.vo;

import com.f.enums.TunnelType;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author : caibi
 * @date : 2018/8/18 上午11:07
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TunnelUpdateBody {
    public static final int TUNNEL = 1;
    public static final int PAY_TYPE = 2;
    public static final int BRAND = 3;

    private String tunnelId;
    private TunnelType tunnelType;
    private int payType;
    private String brand;
    private boolean fullUpdate;
    private int type;

    public static TunnelUpdateBody valueOfTunnel(String tunnelId) {
        TunnelUpdateBody result = new TunnelUpdateBody();
        result.tunnelId = tunnelId;
        result.type = TUNNEL;
        return result;
    }

    public static TunnelUpdateBody valueOfPayType(String tunnelId, int payType) {
        TunnelUpdateBody result = new TunnelUpdateBody();
        result.tunnelId = tunnelId;
        result.payType = payType;
        result.type = PAY_TYPE;
        return result;
    }

    public static TunnelUpdateBody valueOfBrand(String tunnelId, int payType, String brand) {
        TunnelUpdateBody result = new TunnelUpdateBody();
        result.tunnelId = tunnelId;
        result.payType = payType;
        result.brand = brand;
        result.type = BRAND;
        return result;
    }

    public String getTunnelId() {
        return tunnelId;
    }

    public void setTunnelId(String tunnelId) {
        this.tunnelId = tunnelId;
    }

    public TunnelType getTunnelType() {
        return tunnelType;
    }

    public void setTunnelType(TunnelType tunnelType) {
        this.tunnelType = tunnelType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isFullUpdate() {
        return fullUpdate;
    }

    public void setFullUpdate(boolean fullUpdate) {
        this.fullUpdate = fullUpdate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
