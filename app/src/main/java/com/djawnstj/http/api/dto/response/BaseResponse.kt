package com.djawnstj.http.api.dto.response

data class BaseResponse<T>(
    val code: Int,
    val data: Data<T>,
    val message: String,
    val requestCode: String,
) {

    data class Data<T>(
        val header: Header,
        val body: T
    )

    data class Header(
        val code: String,
        val errMessage: String,
        val message: String,
        val repeat: Int,
        val serviceId: String,
        val userId: String
    )

}