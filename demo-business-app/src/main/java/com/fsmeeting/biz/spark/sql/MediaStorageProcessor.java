package com.fsmeeting.biz.spark.sql;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @date: 2018/5/24 22:04
 * @author: yicai.liu
 */
public class MediaStorageProcessor implements IBizProcessor {

    private static Logger logger = LoggerFactory.getLogger(MediaStorageProcessor.class);

    /**
     * media storage
     * <p>
     * {
     * ** "event": {
     * ***** "code": "media-storage",
     * ***** "time": 1524749266600
     * ** },
     * ** "companyId": 12354,
     * ** "storage": {
     * ***** "used": 30022121
     * ** },
     * }
     * </p>
     *
     * @param params params
     */
    @Override
    public void process(Object... params) {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> event = new HashMap<>();
        event.put("code", "media-storage");
        event.put("time", System.currentTimeMillis());
        info.put("event", event);
        Map<String, Object> storage = new HashMap<>();
        info.put("storage", storage);
        info.put("companyId", new Random().nextInt(1000) + 1000);
        logger.info(JSON.toJSONString(info));
    }
}
