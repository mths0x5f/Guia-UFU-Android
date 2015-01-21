package io.github.mths0x5f.guiaufu.api;

import io.github.mths0x5f.guiaufu.ru.pojo.RU;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface UFUInfoAPIInterface {

    @GET("/ru/cardapio/")
    void getRU(@Query("campus") String campus, Callback<RU> callback);

}
