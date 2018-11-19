package com.nilin.retrofit2_rxjava2_demo

import com.nilin.developgoods.model.Result
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by liangd on 2017/7/11.
 */
interface Api {

    @GET("api/data/{type}/{pageSize}/{pageNumber}")
    fun getData(@Path("type") type: String,
                @Path("pageSize") pageSize: Int,
                @Path("pageNumber") pageNumber: Int): Observable<Result>

    companion object Factory{
        fun create():Api {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://gank.io/")
                    .build()

            return retrofit.create(Api::class.java)
        }
    }
}
