package com.fsmeeting.biz.spark.sql;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Analysis ClientData Processor
 *
 * @date: 2018/5/24 22:18
 * @author: yicai.liu
 */
public class AnalysisClientDataProcessor implements IBizProcessor {

    private static Logger logger = LoggerFactory.getLogger(AnalysisClientDataProcessor.class);

    /**
     * analysis client data
     * <p>
     * {
     * **"appkey": "dfc0413e0198995ce87c35cd3885e912",
     * **"version": "3.1",
     * **"sign": "6B32EDA3B7015DFD4A43C11A4CEE5151",
     * **"timestamp": 1512447132000,
     * **"device": {  //device
     * ******"deviceId": "9CC0AC186027",
     * ******"deviceName": "SM-N9008",
     * ******"deviceSerno": "9CC0AC186027",
     * ******"deviceType": "D1",
     * ******"manufacturer": "samsung",
     * ******"brand": "samsung",
     * ******"deviceModel": "SM-N9008",
     * ******"os": "Android 21",
     * ******"osVersion": "5.0",
     * ******"cpuModel": "MSM8974",
     * ******"soundCardModel": "",
     * ******"videoCardModel": "",
     * ******"memoryModel": "",
     * ******"camaraModel": "",
     * ******"gpuModel": "",
     * ******"network": "3G/4G",
     * ******"languages": "zh_CN",
     * ******"platform": "Android",
     * ******"echoDelayMS": "108",
     * ******"softwareVersion ":" 111.111.111.111 ",
     * ******"hardwareVersion":" 222.222.222.222",
     * ******"systemVersion":" 333.333.333.333 ",
     * ******"LBS": { // device -> lbs
     * *********"latitude": "30.0907001034 N",
     * *********"longitude": "119.9441629988 E",
     * *********"country": "中国",
     * *********"province": "浙江",
     * *********"city": "杭州"
     * *******}
     * **},
     * **"event": {  // event
     * ******"code": "analy-client-data", //login、crash、register...
     * ******"time": "1512447131991"
     * ***},
     * **"meeting": {  // meeting
     * ******"meetingStatus": "in",	//before、in、after
     * ******"bpartnerId": 594,
     * ******"userId": 11711,
     * ******"roomId": 18695
     * ***}
     * }
     * </p>
     *
     * @param params params
     */
    @Override
    public void process(Object... params) {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> event = new HashMap<>();
        event.put("code", "analysis-deviceData");
        event.put("time", System.currentTimeMillis());
        info.put("event", event);
        Map<String, Object> meeting = new HashMap<>();
        meeting.put("companyId", new Random().nextInt(1000) + 1000);
        meeting.put("roomId", new Random().nextInt(100) + 100);
        String[] status = new String[3];
        status[0] = "before";
        status[1] = "in";
        status[2] = "after";
        meeting.put("status", status[new Random().nextInt(status.length)]);
        info.put("meeting", meeting);

        String[] appkeys = new String[4];
        appkeys[0] = "q12dsafasdfrlalkfal889h";
        appkeys[1] = "x71dsafassgfgalkfal889d";
        appkeys[2] = "n85dsafasdfklalkfal889s";
        appkeys[3] = "d32dsafasdftyalkfal889b";
        String[] versions = new String[4];
        versions[0] = "1.1.1.1";
        versions[1] = "2.2.2.2";
        versions[2] = "3.3.3.3";
        versions[3] = "4.4.4.4";
        info.put("appkey", appkeys[new Random().nextInt(appkeys.length)]);
        info.put("version", versions[new Random().nextInt(versions.length)]);
        Map<String, Object> device = new HashMap<>();
        device.put("deviceId", Math.abs(new Random().nextLong()));
        info.put("device", device);
        logger.info(JSON.toJSONString(info));
    }
}
