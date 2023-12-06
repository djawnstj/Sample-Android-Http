package com.djawnstj.http.common.repository

import com.djawnstj.http.common.api.dto.request.BaseRequest
import com.djawnstj.http.common.api.dto.response.BaseResponse
import com.djawnstj.http.common.api.retrofit
import com.djawnstj.http.common.dto.Ward
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.QueryMap

val commonApi: CommonRepository by lazy { retrofit.create() }

interface CommonRepository {

    @GET("wards")
    fun getWardList(
        @QueryMap request: HashMap<String, Any?> = BaseRequest.DEFAULT_REQUEST.toQueryMap()
    ): Call<BaseResponse<List<Ward>>>

}

