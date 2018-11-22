package com.f.mvc.service.pay;

import com.alibaba.fastjson.JSONObject;
import com.f.datasource.annotations.Redis;
import com.f.enums.RedisKey;
import com.f.mvc.entity.server.BrandTunnelRelation;
import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.entity.server.TunnelSetting;
import com.f.vo.TunnelUpdateBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author : caibi
 */
@Service
public class PayServerServiceImpl implements PayServerService {
    private static final int CMD_UPDATE_CONFIG = 1;
    private static final int CMD_ADD_TUNNEL = 2;
    private static final int CMD_UPDATE_BRAND_MAPPING = 8;
    /**
     * 同步配置到redis
     */
    private static final int CMD_SYNC_CONFIG = 9;
    /**
     * 同步所有到redis
     */
    private static final int CMD_SYNC_ALL_CONFIG = 10;

    private static final String REDIS_KEY_PREFIX_PAY_TUNNEL_CONFIG = "PAYTUNNEL_CONFIG_";
    private static final ChannelTopic tunnelChannelTopic = new ChannelTopic("tunnel:update");
    private static final ChannelTopic payChannelTopic = new ChannelTopic("pay:finish");
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 通过redis更新通道基础数据
     *
     * @param tunnel
     * @param addTunnel 是否是新增tunnel
     */
    @Override
    public void publish(TunnelSetting tunnel, boolean addTunnel) {
        Map<String, String> configMap = tunnel.toConfigMap();
        String tunnelId = tunnel.getTunnelId();
        TunnelUpdateBody body = TunnelUpdateBody.valueOfTunnel(tunnelId);
        publish(tunnelId, configMap, body, addTunnel);
    }

    /**
     * 通过redis更新通道支付数据
     *
     * @param tunnelPay
     */
    public void publish(TunnelPayRelation tunnelPay) {
        String tunnelId = tunnelPay.getTunnelId();
        Map<String, String> configMap = tunnelPay.toConfigMap();
        TunnelUpdateBody body = TunnelUpdateBody.valueOfPayType(tunnelId, tunnelPay.getPayType());
        publish(tunnelId, configMap, body, false);
    }

    /**
     * 通过redis更新通道品牌数据
     *
     * @param tunnelId
     * @param payType
     * @param brandTunnelRelation
     */
    public void publish(String tunnelId, int payType, BrandTunnelRelation brandTunnelRelation) {
        Map<String, String> configMap = brandTunnelRelation.toConfigMap(payType);
        TunnelUpdateBody body = TunnelUpdateBody.valueOfBrand(tunnelId, payType, brandTunnelRelation.getBrand());
        publish(tunnelId, configMap, body, false);
    }

    /**
     * 同步tunnel信息到redis
     *
     * @param tunnelId 为空表示同步所有
     */
    public void syncTunnel(String tunnelId) {
        boolean all = StringUtils.isBlank(tunnelId);
        int cmd = all ? CMD_SYNC_ALL_CONFIG : CMD_SYNC_CONFIG;
        TunnelUpdateBody body = all ? null : TunnelUpdateBody.valueOfTunnel(tunnelId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", cmd);
        if (body != null) {
            jsonObject.put("body", body);
        }
        redisTemplate.convertAndSend(tunnelChannelTopic.getTopic(), jsonObject.toJSONString());
    }

    /**
     * //        try (Jedis redis = jedisPool.getResource()) {
     * //            redis.hmset(redisKey, configMap);
     * //        } catch (Exception ex) {
     * //            throw new RuntimeException(ex);
     * //        }
     *
     * @param tunnelId
     * @param configMap
     * @param body
     * @param addTunnel
     */
    @Redis(value = RedisKey.MASTER)
    private void publish(String tunnelId, Map<String, String> configMap, TunnelUpdateBody body, boolean addTunnel) {
        String redisKey = REDIS_KEY_PREFIX_PAY_TUNNEL_CONFIG + tunnelId;
        redisTemplate.opsForHash().putAll(redisKey, configMap);
        int cmd = addTunnel ? CMD_ADD_TUNNEL : CMD_UPDATE_CONFIG;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", cmd);
        jsonObject.put("body", body);
        redisTemplate.convertAndSend(tunnelChannelTopic.getTopic(), jsonObject.toJSONString());
    }

    /**
     *
     */
    @Redis(value = RedisKey.MASTER)
    public void updateBrandMapping() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", CMD_UPDATE_BRAND_MAPPING);
        redisTemplate.convertAndSend(tunnelChannelTopic.getTopic(), jsonObject.toJSONString());
    }
}
