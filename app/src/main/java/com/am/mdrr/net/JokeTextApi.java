package com.am.mdrr.net;



import com.am.mdrr.entity.ShowapiResBodyBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by AM on 2016/6/27.
 */
public interface JokeTextApi {

    @GET("/341-1")
    Observable<HttpResult<ShowapiResBodyBean>> jokeText(
            @Query("maxResult") int maxResult,
            @Query("page") int page,
            @Query("showapi_appid") String showapi_appid,
            @Query("showapi_timestamp") String showapi_timestamp,
            @Query("time") String time,
            @Query("showapi_sign") String showapi_sign);


}
