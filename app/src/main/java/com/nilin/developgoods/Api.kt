package com.nilin.retrofit2_rxjava2_demo

import com.nilin.developgoods.model.Result

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by liangd on 2017/7/11.
 */
interface Api {

    @GET("api/data/{type}/{pageSize}/{pageNumber}")
    fun getData(@Path("type") type: String,
                @Path("pageSize") pageSize: Int,
                @Path("pageNumber") pageNumber: Int): Call<Result>
}
