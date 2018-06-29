package com.fsmeeting.analytics.collect.common.web;


public class Constant {

    /****
     * 启用
     */
    public static int ACTIVE = 1;

    /**
     * 禁用
     */
    public static int UNACTIVE = 0;

    /**
     * false
     */
    public static int FAILURE = -1;

    /**
     * true
     */
    public static int SUCCESS = 1;

    /**
     * 默认值
     */
    public static String UNKNOWN = "未知";


    /**
     * 逆地理解析URL
     */
    public static final String GEOCODERURL = "http://api.map.baidu.com/geocoder/v2/";
    /**
     * baidu map appkey
     */
    public static final String BAIDUAPPKEY = "Pw3M6TY9xckFQ5qmizyTI6Vx";

    public static final int HOUR = 1;

    public static final int DAY = 2;

    public static final int WEEK = 3;

    public static final int MONTH = 4;

    public static final int DAYNOW = 5;

    public static final String BLANK = "";

    public static final String HOURPUT = "HOUR";

    public static final String DAYPUT = "DAY";

    public static final String WEEKPUT = "WEEK";

    public static final String MONTHPUT = "MONTH";

    public static final String T_DEVICE_LOG = "T_DEVICE_LOG";
    public static final String ANALYTICS_TABLE = "T_FMAnalysis_INFO";
    public static final String ANALYTICS_TABLE_HOUR = "T_FMAnalysis_INFO_HOUR";
    public static final String ANALYTICS_TABLE_DAY = "T_FMAnalysis_INFO_DAY";
    public static final String ANALYTICS_TABLE_WEEK = "T_FMAnalysis_INFO_WEEK";
    public static final String ANALYTICS_TABLE_MONTH = "T_FMAnalysis_INFO_MONTH";

    public static final String COUNTRY = "country";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";

    /**
     * @author yicai.liu
     * @description 用户行为常量信息
     * @Date 2016年5月11日上午9:15:22
     */
    public static final class Activity {
        public static final String PREFIX = "Activity";

        public static final class Column {
            public static final String RECORDTYPE = "recordType";
            public static final String ACTIVITY = "activity";
            public static final String DURATION = "duration";
            public static final String STARTMILLIS = "startMillis";
            public static final String ENDMILLIS = "endMillis";
            public static final String APPKEY = "appkey";
            public static final String VERSION = "version";
            public static final String ACTIVITYLOG = "ActivityLog";
        }

    }

    /**
     * @author yicai.liu
     * @description 事件相关常量
     * @Date 2016年5月11日上午9:16:38
     */
    public static final class Event {

        public static final int BATCH_PROCESS_NUM = 50;

        public static final String PREFIX = "Event";
        // 事件haddoop文件存放目录
        public static final String FILE_STUB = "/hadoop/dfs/output/event/day/";

        public static final class Column {
            public static final String RECORDTYPE = "recordType";
            public static final String RECORDTYPE_VALUE = "EventInfo";
            public static final String EVENTID = "eventId";
            public static final String ACTIVITY = "activity";
            public static final String LABEL = "label";
            public static final String ACC = "acc";
            public static final String APPKEY = "appkey";
            public static final String VERSION = "version";
            public static final String TIME = "time";
            public static final String DEVICEID = "deviceId";
            public static final String DEVICENAME = "deviceName";
            public static final String PLATFORM = "platform";
            public static final String ROOMID = "roomId";
            public static final String USERID = "userId";
            public static final String COMPANYID = "companyId";
        }

    }

    public static final class OsVersion {
        public static final String PREFIX_DAY = "DeviceDay";
        public static final String PREFIX_WEEK = "DeviceWeek";
        public static final String PREFIX_MONTH = "DeviceMonth";

        // 事件haddoop文件存放目录
        public static final String FILE_STUB_DAY = "/hadoop/dfs/output/os/day/";
        public static final String FILE_STUB_WEEK = "/hadoop/dfs/output/os/week/";
        public static final String FILE_STUB_MONTH = "/hadoop/dfs/output/os/month/";

        public static final class Column {
            public static final String RECORDTYPE = "recordType";
            public static final String RECORDTYPE_VALUE = "ClientData";
            public static final String APPKEY = "appkey";
            public static final String VERSION = "version";
            public static final String OS_VERSION = "os_version";
            public static final String DEVICEID = "deviceId";
            public static final String DEVICENAME = "deviceName";
            public static final String PLATFORM = "platform";

            public static final String LANGUAGE = "language";
            public static final String MCCMNC = "mccmnc";
            public static final String MODULENAME = "modulename";
            public static final String NETWORK = "netWork";
            public static final String RESOLUTION = "resolution";
            public static final String TIME = "time";

            public static final String USER_ID = "userId";
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "longitude";
            public static final String COUNTRY = "country";
            public static final String PROVINCE = "province";
            public static final String CITY = "city";

        }

    }

    /**
     * @author yicai.liu
     * @description 崩溃常量信息
     * @Date 2016年7月21日上午9:02:49
     */
    public static final class CrashInfo {
        public static final String PREFIX = "ErrorCrashInfo";

        public static final String FILE_STUB = "/hadoop/dfs/output/crashinfo/day/";

        public static final String ROWKEY = "rowkey";

        public static final class Column {
            public static final String RECORDTYPE = "recordType";
            public static final String RECORDTYPE_VALUE = "CrashInfo";
            public static final String APPKEY = "appkey";
            public static final String VERSION = "version";
            public static final String TIME = "time";
            public static final String DEVICEID = "deviceId";
            public static final String ADDRESS = "crashAddress";
            public static final String FILE = "crashFile";
            public static final String DESC = "crashDesc";
            public static final String DOWNLOADURL = "downloadURL";
            public static final String PRODUCTID = "productID";
            public static final String ROOMID = "roomId";
            public static final String USERID = "userId";
            public static final String COMPANYID = "companyId";
        }

    }

    /**
     * 接口
     *
     * @author yicai.liu
     */
    public static final class Interface {

        /**
         * 超时
         */
        public static final long TIMEOUT_MS = 5000L;

        /**
         * 检查更新
         *
         * @author yicai.liu
         */
        public static final class CheckUpdate {
            /**
             * 超时
             */
            public static final long TIMEOUT_MS = 10000L;
        }
    }

    /**
     * @Description: 响应编码
     * @Author: yicai.liu
     * @Date 9:36 2017/6/30
     */
    public static final class ResponseCode {
        /**
         * 成功
         */
        public static final int OK = 0;

        /**
         * 参数不合法
         */
        public static final int PARAMTER_INVALID = 120701;

        /**
         * 请求超时
         */
        public static final int REQUEST_TIMEOUT = 120702;

        /**
         * 认证失败
         */
        public static final int AUTH_FAIL = 120703;

        /**
         * 记录已经存在
         */
        public static final int RECORD_EXIST = 120704;

        /**
         * 参数验证失败
         */
        public static final int VALIDATOR_FAIL = 120705;

        /**
         * 字符串过滤失败
         */
        public static final int STRING_ESCAPE_FAIL = 120706;

        /**
         * 服务调用失败
         */
        public static final int SERVICE_FAIL = 120707;

        /**
         * 参数解析错误
         */
        public static final int PARAMTER_PARSER_ERRROR = 120708;

        /**
         * 流量溢出
         */
        public static final int TRAFFIC_OVERFLOW = 120709;

        /**
         * 签名校验不合法
         */
        public static final int SIGN_INVALID = 120710;

        /**
         * 数据库错误
         */
        public static final int DB_ERROR = 120711;
    }

    /**
     * @Description: 响应消息
     * @Author: yicai.liu
     * @Date 9:22 2017/6/30
     */
    public static final class ResponseMsg {

        /**
         * 成功
         */
        public static final String SUCCESS = "success";

        /**
         * 失败
         */
        public static final String FAIL = "fail";

        /**
         * 参数不合法
         */
        public static final String PARAMTER_INVALID = "paramter invalid";

        /**
         * 请求超时
         */
        public static final String REQUEST_TIMEOUT = "request timeout";

        /**
         * 认证失败
         */
        public static final String AUTH_FAIL = "authentication fail";

        /**
         * 记录已经存在
         */
        public static final String RECORD_EXIST = "record exist";

        /**
         * 参数验证失败
         */
        public static final String VALIDATOR_FAIL = "validator fail";

        /**
         * 字符串过滤失败
         */
        public static final String STRING_ESCAPE_FAIL = "string escape fail";

        /**
         * 服务调用失败
         */
        public static final String SERVICE_FAIL = "service fail";

        /**
         * 流量溢出
         */
        public static final String TRAFFIC_OVERFLOW = "traffic overflow";

        /**
         * 签名不合法
         */
        public static final String SIGN_INVALID = "sign invalid";

        /**
         * 数据库错误
         */
        public static final String DB_ERROR = "database error";
    }
}
