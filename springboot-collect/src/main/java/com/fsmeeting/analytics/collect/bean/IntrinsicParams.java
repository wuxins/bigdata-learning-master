package com.fsmeeting.analytics.collect.bean;

import java.io.Serializable;

/**
 * Interface intrinsic Interface
 *
 * @date: 2017/12/5 9:13
 * @author: yicai.liu
 */
public class IntrinsicParams implements Serializable {

    private static final long serialVersionUID = 5628458829150975573L;

    /**
     * client appkey
     */
    private String appkey;

    /**
     * client version
     */
    private String version;

    /**
     * client sign
     */
    private String sign;

    /**
     * client timestamp
     */
    private long timestamp;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
