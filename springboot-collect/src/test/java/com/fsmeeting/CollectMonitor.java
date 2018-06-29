package com.fsmeeting;

import com.alibaba.fastjson.JSON;
import com.fsmeeting.analytics.collect.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CollectMonitor {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void onlineMeg() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> log = new HashMap<>();
                Map<String, Object> meeting = new HashMap<>();
                Map<String, Object> event = new HashMap<>();
                log.put("meeting", meeting);
                log.put("event", event);
                int companyId = 1 + new Random().nextInt(1);
                int roomId = 1000 + new Random().nextInt(1);
                long time = System.currentTimeMillis() + new Random().nextInt(180000); // 3 mins
                int pcu = new Random().nextInt(1000);
                meeting.put("companyId", companyId);
                meeting.put("roomId", roomId);
                meeting.put("pcu", pcu);
                event.put("time", time);
                event.put("code", "live_login");
                String msg = JSON.toJSONString(log);
                System.out.println(msg);
                kafkaTemplate.send("live-online-users", msg);
            }
        }, 5, 2, TimeUnit.SECONDS);
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmm").format(new Date(1524276864771l)));
        System.out.println(new Random().nextInt(1));
    }
}
