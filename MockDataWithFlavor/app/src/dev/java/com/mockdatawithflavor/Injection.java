package com.mockdatawithflavor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by tedliang on 2015/12/3.
 */
public class Injection {

    public static SimpleService.GitHub getInjection(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SimpleService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(SimpleService.GitHub.class);
    }
}
