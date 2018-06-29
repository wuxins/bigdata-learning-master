package com.fsmeeting.analytics.collect.controller.V1;

import com.alibaba.fastjson.JSON;
import com.fsmeeting.analytics.collect.bean.ClientDevicesRequest;
import com.fsmeeting.analytics.collect.common.web.Constant;
import com.fsmeeting.analytics.collect.common.web.Response;
import com.fsmeeting.analytics.collect.common.web.WebAsyncTimeout;
import com.fsmeeting.analytics.collect.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

/**
 * Device Controller(V1)
 *
 * @author yicai.liu
 */
@RestController
public class DeviceControllerV1 {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceControllerV1.class);

    @Autowired
    private IDeviceService deviceService;

    /**
     * common async executor
     */
    @Autowired
    private ThreadPoolTaskExecutor commonAsyncExecutor;

    /**
     * client devices
     *
     * @param request request message
     * @return http response
     */
    @RequestMapping(value = "/analysis/v2/clients/devices", method = RequestMethod.POST)
    public WebAsyncTask<Response> deviceLog(@RequestBody final ClientDevicesRequest request) {
        final String log = JSON.toJSONString(request);
        LOG.info("client devices params :{}", log);
        WebAsyncTask<Response> result = new WebAsyncTask<Response>(Constant.Interface.TIMEOUT_MS, commonAsyncExecutor,
                new Callable<Response>() {
                    @Override
                    @SuppressWarnings(value = {"unchecked", "rawtypes"})
                    public Response call() throws Exception {
                        deviceService.addClientLog(request);
                        return Response.ok();
                    }
                });

        WebAsyncTimeout.onTimeout(result);
        return result;
    }

}
