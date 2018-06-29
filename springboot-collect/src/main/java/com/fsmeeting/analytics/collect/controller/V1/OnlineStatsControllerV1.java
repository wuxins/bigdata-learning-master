package com.fsmeeting.analytics.collect.controller.V1;

import com.alibaba.fastjson.JSON;
import com.fsmeeting.analytics.collect.bean.ClientDevicesRequest;
import com.fsmeeting.analytics.collect.common.web.Constant;
import com.fsmeeting.analytics.collect.common.web.Response;
import com.fsmeeting.analytics.collect.common.web.WebAsyncTimeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;


@RestController
public class OnlineStatsControllerV1 {

    private static final Logger LOG = LoggerFactory.getLogger(OnlineStatsControllerV1.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

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
    @RequestMapping(value = "/analysis/v1/online/users", method = RequestMethod.POST)
    public WebAsyncTask<Response> deviceLog(@RequestBody final ClientDevicesRequest request) {
        final String log = JSON.toJSONString(request);
        LOG.info("client devices params :{}", log);
        WebAsyncTask<Response> result = new WebAsyncTask<Response>(Constant.Interface.TIMEOUT_MS, commonAsyncExecutor,
                new Callable<Response>() {
                    @Override
                    @SuppressWarnings(value = {"unchecked", "rawtypes"})
                    public Response call() throws Exception {
                        kafkaTemplate.send("live-online-users", JSON.toJSONString(request));
                        return Response.ok();
                    }
                });

        WebAsyncTimeout.onTimeout(result);
        return result;
    }

}
