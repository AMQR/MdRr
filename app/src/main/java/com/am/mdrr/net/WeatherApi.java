package com.am.mdrr.net;

import com.am.mdrr.entity.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AM on 2016/7/18.
 */
public interface WeatherApi {
    // 发送get请求，光是这段地址肯定不完整，待会我们还在在别的地方补上 baseurl 。 这里直接把后面的请求给写死了（这样肯定不好）
    // 请求还是Get
    @GET("/microservice/weather")
    Call<WeatherBean> getWeather(@Query("citypinyin") String city); // 使用了@Query，后面的 citypinyin 是参数名
}