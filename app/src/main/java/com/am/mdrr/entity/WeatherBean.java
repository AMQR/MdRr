package com.am.mdrr.entity;

/**
 * Created by AM on 2016/7/18.
 */
public class WeatherBean {
    public int errNum;
    public String errMsg;
    public RetDataBean retData;

    public static class RetDataBean {
        public String city;
        public String pinyin;
        public String citycode;
        public String date;
        public String time;
        public String postCode;
        public int longitude;
        public int latitude;
        public String altitude;
        public String weather;
        public String temp;
        public String l_tmp;
        public String h_tmp;
        public String WD;
        public String WS;
        public String sunrise;
        public String sunset;
    }
}