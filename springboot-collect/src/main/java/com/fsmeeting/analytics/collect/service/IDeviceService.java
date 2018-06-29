package com.fsmeeting.analytics.collect.service;


import com.fsmeeting.analytics.collect.bean.ClientDevicesRequest;

/**
 * Description : 设备数据明细采集
 *
 * @author yicai.liu
 */
public interface IDeviceService {


    /**
     * add client log message
     *
     * @param request request
     */
    void addClientLog(ClientDevicesRequest request);
}
