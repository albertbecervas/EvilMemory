package com.evilmem.albert.evilmemory.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Albert on 03/02/2017.
 */
public interface FOAASService {


    @Headers({"Accept: application/json"})
    @GET("/version")
    Call<FOAASResponse> getVersion();

    @Headers({"Accept: application/json"})
    @GET("/cocksplat/{name}/{from}")
    Call<FOAASResponse> getCocksplat(@Path("name") String name,
                                     @Path("from") String from,
                                     @Query("i18n") String language,
                                     @Query("shoutcloud") String x);

    @Headers({"Accept: application/json"})
    @GET("/data/2.5/weather")
    Call<FOAASResponse> getWeather(@Query("lat") double lat,@Query("lon") double lon,@Query("APPID") String appid);

}
