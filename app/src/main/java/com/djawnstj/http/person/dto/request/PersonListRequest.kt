package com.djawnstj.http.person.dto.request

import com.djawnstj.http.common.api.dto.request.BaseRequest

class PersonListRequest {

    fun toQueryMap(): Map<String, Any?> = BaseRequest.DEFAULT_REQUEST.toQueryMap()

}