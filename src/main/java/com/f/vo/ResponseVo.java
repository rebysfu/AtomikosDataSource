package com.f.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

/**
 * User: bvsoo
 * Date: 2018/9/13
 * Time: 上午10:44
 */
public class ResponseVo implements Serializable {
    private static final long serialVersionUID = -4986810711990682034L;

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应携带数据
     */
    private Object data;

    /**
     * 响应时间(时间戳)
     */
    private Long time;


    /**
     * 防止外部实例化
     */
    private ResponseVo() {
    }

    /**
     * 防止外部实例化
     */
    private ResponseVo(int code, String message, Object data, Long time) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = time;
    }

    public static ResponseVoBuilder builder() {
        return new ResponseVoBuilder(HttpStatus.OK);
    }

    /**
     * 转换成Map
     *
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
        return map;
    }


    /**
     * @param serializerFeatures
     * @return
     */
    public String toJSONString(SerializerFeature... serializerFeatures) {
        return JSON.toJSONString(this, serializerFeatures);
    }


    /**
     * @return
     */
    public String toJSONString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Long getTime() {
        return time;
    }

    /**
     * builder
     */
    public static class ResponseVoBuilder {
        private volatile int code;
        private volatile String message;
        private volatile Object data;

        private ResponseVoBuilder() {
        }

        private ResponseVoBuilder(HttpStatus httpStatus) {
            this.code = httpStatus.value();
            this.message = httpStatus.getReasonPhrase();
        }

        public ResponseVoBuilder code(int code) {
            this.code = code;
            return this;
        }

        public ResponseVoBuilder code(HttpStatus httpStatus) {
            this.code = httpStatus.value();
            this.message = httpStatus.getReasonPhrase();
            return this;
        }

        public ResponseVoBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseVoBuilder data(@Nullable Object data) {
            this.data = data;
            return this;
        }

        public ResponseVo build() {
            return new ResponseVo(code, message, data, Instant.now().getEpochSecond());
        }
    }


}
