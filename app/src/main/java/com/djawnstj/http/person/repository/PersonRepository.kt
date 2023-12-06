package com.djawnstj.http.person.repository

import com.djawnstj.http.common.api.dto.request.BaseRequest
import com.djawnstj.http.common.api.dto.response.BaseResponse
import com.djawnstj.http.common.api.retrofit
import com.djawnstj.http.person.dto.Person
import com.djawnstj.http.person.dto.request.PersonCreateRequest
import com.djawnstj.http.person.dto.response.PersonCreateResponse
import retrofit2.Call
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

val personApi: PersonRepository by lazy { retrofit.create() }

interface PersonRepository {

    @GET("persons")
    fun getPersonList(
        @QueryMap request: HashMap<String, Any?> = BaseRequest.DEFAULT_REQUEST.toQueryMap()
    ): Call<BaseResponse<List<Person>>>

    @POST("persons")
    fun createPerson(
        @Body request: PersonCreateRequest
    ): Call<BaseResponse<PersonCreateResponse>>

}