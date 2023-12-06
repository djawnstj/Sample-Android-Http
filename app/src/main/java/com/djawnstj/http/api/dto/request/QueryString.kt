package com.djawnstj.http.api.dto.request

interface QueryString {

    fun toQueryMap(): HashMap<String, Any?>

}