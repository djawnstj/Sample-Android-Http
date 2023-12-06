package com.djawnstj.http.city.repository

import com.djawnstj.http.common.api.dto.request.BaseRequest
import com.djawnstj.http.common.api.dto.response.BaseResponse
import com.djawnstj.http.common.api.retrofit
import com.djawnstj.http.city.dto.City
import com.djawnstj.http.city.dto.request.CityCreateRequest
import com.djawnstj.http.city.dto.response.CityCreateResponse
import com.djawnstj.http.city.dto.response.CityDeleteResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.create
import retrofit2.http.*

val cityApi: CityRepository by lazy { retrofit.create() }

interface CityRepository {

    @GET("cities")
    suspend fun getCityList(
        @QueryMap request: HashMap<String, Any?> = BaseRequest.DEFAULT_REQUEST.toQueryMap()
    ): Response<BaseResponse<List<City>>>

    @POST("cities")
    suspend fun createCity(
        @Body request: CityCreateRequest
    ): Response<BaseResponse<CityCreateResponse>>

    @HTTP(method = "DELETE", path="cities/{id}", hasBody = true)
    suspend fun deleteCity(
        @Path("id") id: Long,
        @Body request: BaseRequest = BaseRequest.DEFAULT_REQUEST
    ): Response<BaseResponse<CityDeleteResponse>>

}