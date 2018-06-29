package com.fsmeeting;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @date: 2018/4/22 15:55
 * @author: yicai.liu
 */
public class LogProducerTest {

    @Test
    public void onlineInfo() {
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> meeting = new HashMap<>();
            Map<String, Object> event = new HashMap<>();
            map.put("meeting", meeting);
            map.put("event", event);
            int companyId = 1 + new Random().nextInt(3);
            int roomId = 1000 + new Random().nextInt(4);
            long time = System.currentTimeMillis() + new Random().nextInt(180000); // 3 mins
            int pcu = new Random().nextInt(1000);
            meeting.put("companyId", companyId);
            meeting.put("roomId", roomId);
            meeting.put("pcu", pcu);
            event.put("time", time);
            event.put("code", "live_login");
            System.out.println(JSON.toJSONString(map));
        }
    }

    @Test
    public void roomLog() {
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> meeting = new HashMap<>();
            Map<String, Object> event = new HashMap<>();
            int companyId = 1 + new Random().nextInt(3);
            int roomId = 2000 + new Random().nextInt(4);
            int userId = 20000 + new Random().nextInt(4);
            String token = "HDHKJJSJNKNJDSJKND" + userId;
            String nickname = "nick-" + userId;
            int liveRoomId = 1000 + new Random().nextInt(4);
            int liveNo = liveRoomId + new Random().nextInt(4);
            long time = System.currentTimeMillis() + new Random().nextInt(180000); // 3 mins
            String appId = "" + 1 + new Random().nextInt(3);
            int num = new Random().nextInt(1000);

            map.put("companyId", companyId);
            map.put("roomId", liveRoomId);
            map.put("time", time);
            map.put("num", num);
            System.out.println(JSON.toJSONString(map));
        }
    }
}
