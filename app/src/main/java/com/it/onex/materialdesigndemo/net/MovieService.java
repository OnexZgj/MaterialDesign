package com.it.onex.materialdesigndemo.net;


import com.it.onex.materialdesigndemo.bean.Movie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * retrofit的请求方法的实现
 * Created by Linsa on 2017/7/20.
 */

public interface MovieService {


//    @GET("top250")
//    Call<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}
