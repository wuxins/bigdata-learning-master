package com.fsmeeting;

import com.fsmeeting.biz.spark.sql.AnalysisClientDataProcessor;
import com.fsmeeting.biz.spark.sql.BizProcessFacory;
import com.fsmeeting.biz.spark.sql.LivePCUProcessor;
import com.fsmeeting.biz.spark.sql.MediaStorageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 * @date: 2018/5/24 21:07
 * @author: yicai.liu
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * main
     *
     * @param args main params
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws InterruptedException {

        String bizType = args[0];
        int rate = 3;
        try {
            rate = Integer.parseInt(args[1]);
        } catch (Exception ex) {
        }
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable cmd) {
                return new Thread(cmd, "app logger executor");
            }
        });
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                switch (bizType) {
                    case "live-pcu":
                        BizProcessFacory.create(LivePCUProcessor.class).process();
                        break;
                    case "analysis-deviceData":
                        BizProcessFacory.create(AnalysisClientDataProcessor.class).process();
                        break;
                    case "media-storage":
                        BizProcessFacory.create(MediaStorageProcessor.class).process();
                        break;
                    default:
                        throw new RuntimeException("params[bizType] unsupported!");
                }
            }
        }, rate, rate, TimeUnit.SECONDS);

        TimeUnit.MINUTES.sleep(Integer.MAX_VALUE);
    }

}
