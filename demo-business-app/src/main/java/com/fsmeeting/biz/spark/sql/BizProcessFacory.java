package com.fsmeeting.biz.spark.sql;

/**
 * Business process facory
 *
 * @date: 2018/5/24 22:16
 * @author: yicai.liu
 */
public class BizProcessFacory {

    public static IBizProcessor create(Class<? extends IBizProcessor> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
