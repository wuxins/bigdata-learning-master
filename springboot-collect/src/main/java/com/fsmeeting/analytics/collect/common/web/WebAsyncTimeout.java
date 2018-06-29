package com.fsmeeting.analytics.collect.common.web;


import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

public class WebAsyncTimeout {

    /**
     * @Description: timeout handle
     * @Author: yicai.liu
     * @Date 14:51 2017/7/21
     */
    public static void onTimeout(WebAsyncTask<Response> result) {
        result.onTimeout(new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                return new Response.Builder()
                        .code(Constant.ResponseCode.REQUEST_TIMEOUT)
                        .message(Constant.ResponseMsg.REQUEST_TIMEOUT)
                        .build();
            }
        });
    }
}
