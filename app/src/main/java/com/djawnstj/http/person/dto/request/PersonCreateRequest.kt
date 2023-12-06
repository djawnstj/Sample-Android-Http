package com.djawnstj.http.person.dto.request

import com.djawnstj.http.api.dto.request.BaseRequest

class PersonCreateRequest(
    val name: String,
    val age: Int,
    val address: String
): BaseRequest() {
}