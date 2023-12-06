package com.djawnstj.http.common.api.dto.request

interface QueryString {

    fun toQueryMap(): HashMap<String, Any?>

}