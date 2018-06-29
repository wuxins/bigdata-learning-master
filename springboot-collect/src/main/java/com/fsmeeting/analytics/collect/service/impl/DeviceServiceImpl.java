package com.fsmeeting.analytics.collect.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.fsmeeting.analytics.collect.bean.ClientDevicesRequest;
import com.fsmeeting.analytics.collect.common.exception.BaseException;
import com.fsmeeting.analytics.collect.common.web.Constant;
import com.fsmeeting.analytics.collect.mapper.DeviceMapper;
import com.fsmeeting.analytics.collect.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * add client log message
     *
     * @param request request
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addClientLog(ClientDevicesRequest request) {

        String deviceId = (String) request.getDevice().get("deviceId");
        String eventCode = (String) request.getEvent().get("code");
        String eventTimeStr = (String) request.getEvent().get("time");
        if (StringUtils.isBlank(deviceId.trim())
                || StringUtils.isBlank(eventCode.trim())
                || StringUtils.isBlank(eventTimeStr.trim())) {
            throw new BaseException(Constant.ResponseCode.PARAMTER_INVALID, Constant.ResponseMsg.PARAMTER_INVALID);
        }


        kafkaTemplate.send("spark-streaming-test", JSON.toJSONString(request));

       /* ClientLog log = new ClientLog();
        log.setVersion(request.getVersion());
        log.setDevice(JSON.toJSONString(request.getDevice()));
        log.setEvent(JSON.toJSONString(request.getEvent()));
        log.setMeeting(JSON.toJSONString(request.getMeeting()));
        log.setAppkey(request.getAppkey());
        log.setTimestamp(System.currentTimeMillis());
        deviceMapper.insertClientLog(log);*/
    }

}
