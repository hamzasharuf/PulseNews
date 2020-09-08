package com.hamzasharuf.pulse.data.api.requests

abstract class BaseRequest {

    abstract fun toMap(): Map<String, String>

}