package com.fsmeeting.biz.spark.sql;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Live PCU
 *
 * @date: 2018/5/24 22:12
 * @author: yicai.liu
 */
public class LivePCUProcessor implements IBizProcessor {

    private static Logger logger = LoggerFactory.getLogger(LivePCUProcessor.class);

    /**
     * live online pcu
     * <p>
     * {
     * ** "event": {
     * ***** "code": "live-pcu",
     * ***** "time": 1524749266600
     * ** },
     * ** "meeting": {
     * ***** "companyId": 3,
     * ***** "pcu": 546,
     * ***** "roomId": 1002
     * ** }
     * }
     * </p>
     *
     * @param params params
     */
    @Override
    public void process(Object... params) {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> event = new HashMap<>();
        event.put("code", "live-pcu");
        event.put("time", System.currentTimeMillis());
        info.put("event", event);
        Map<String, Object> meeting = new HashMap<>();
        meeting.put("companyId", new Random().nextInt(1000) + 1000);
        meeting.put("roomId", new Random().nextInt(100) + 100);
        meeting.put("pcu", new Random().nextInt(1000) + 1);
        info.put("meeting", meeting);
        logger.info(JSON.toJSONString(info));
    }

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = df.format(new Date(System.currentTimeMillis()));
        System.out.println(timeStr);

    }
}
