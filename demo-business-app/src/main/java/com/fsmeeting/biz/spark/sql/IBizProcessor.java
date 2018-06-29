package com.fsmeeting.biz.spark.sql;

/**
 * business process
 *
 * @date: 2018/5/24 22:14
 * @author: yicai.liu
 */
public interface IBizProcessor {

    /**
     * handle business
     *
     * @param params params
     */
    void process(Object... params);
}
