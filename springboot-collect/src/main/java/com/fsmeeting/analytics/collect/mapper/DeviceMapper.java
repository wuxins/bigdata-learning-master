package com.fsmeeting.analytics.collect.mapper;


import com.fsmeeting.analytics.collect.bean.ClientLog;

public interface DeviceMapper {

    void insertClientLog(ClientLog request);

}
