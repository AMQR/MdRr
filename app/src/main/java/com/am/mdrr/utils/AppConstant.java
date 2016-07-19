package com.am.mdrr.utils;

/**
 * Created by AM on 2016/7/19.
 */
public class AppConstant {

    public static String API_SIGN ="989409b9a5ff43cbba3472e407e3e467";
    public static String APP_ID ="21073";

    public static String getTimeTamp(){
        return DateUtils.formatDataTime(System.currentTimeMillis());
    }

    public static String getDate(){
        return DateUtils.formatDate(System.currentTimeMillis());
    }
}
