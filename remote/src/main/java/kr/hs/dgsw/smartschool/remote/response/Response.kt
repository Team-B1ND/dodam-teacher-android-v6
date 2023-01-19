package kr.hs.dgsw.smartschool.remote.response

data class Response<T>(
    val data: T,
    val message: String,
    val status: Int,
)
