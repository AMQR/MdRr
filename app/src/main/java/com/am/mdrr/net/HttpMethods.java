package com.am.mdrr.net;





import com.am.mdrr.entity.ShowapiResBodyBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by AM on 2016/6/28.
 */
public class HttpMethods {

    public static final String BASE_URL_FOR_JOKE = "https://route.showapi.com";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private JokeTextApi jokeTextApi;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        httpClientBuilder.interceptors().add(logging);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_FOR_JOKE)
                .build();
        jokeTextApi  = retrofit.create(JokeTextApi.class);
    }

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            System.out.println("===============  HttpLoggingInterceptor："+message);
        }
    });

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }




    public void getJokeText(Subscriber<ShowapiResBodyBean> subscriber, int maxResult, int page,
                            String showapi_appid, String showapi_timestamp, String showapi_sign){


        jokeTextApi.jokeText(maxResult,page,showapi_appid,showapi_timestamp,"2015-01-01",showapi_sign)
                .flatMap(new Func1<HttpResult<ShowapiResBodyBean>, Observable<ShowapiResBodyBean>>() {
                    @Override
                    public Observable<ShowapiResBodyBean> call(HttpResult<ShowapiResBodyBean> httpResult) {

                        return flatResult(httpResult) ;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    static <T> Observable<T> flatResult(final HttpResult<T> result) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                if (result.getShowapi_res_code() != 0) {
                    subscriber.onError(new ApiException(ApiException.OTHER_ERROR,result.getShowapi_res_error()));

                } else{
                    subscriber.onNext(result.getShowapi_res_body());
                }
                subscriber.onCompleted();
            }
        });
    }




}
