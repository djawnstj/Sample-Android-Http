package com.djawnstj.http.api.dto.request

import java.util.UUID

abstract class BaseRequest {

    val requestCode: String
        get() = generateRequestCode()

    companion object {
        private fun generateRequestCode(): String = UUID.randomUUID().toString()

        val DEFAULT_REQUEST
            get() = DefaultRequest()
    }

    class DefaultRequest: BaseRequest(), QueryString {
        override fun toQueryMap(): HashMap<String, Any?> = hashMapOf("requestCode" to requestCode)
    }

}