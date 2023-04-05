package kr.hs.dgsw.smartschool.remote.response

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @field:SerializedName("data")
    val data: T,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: Int,
)
