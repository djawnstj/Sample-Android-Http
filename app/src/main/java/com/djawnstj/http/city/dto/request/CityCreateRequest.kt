package com.djawnstj.http.city.dto.request

import com.djawnstj.http.api.dto.request.BaseRequest

class CityCreateRequest(
    val name: String,
    val code: Int,
): BaseRequest() {
}