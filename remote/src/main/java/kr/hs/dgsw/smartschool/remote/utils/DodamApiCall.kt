package kr.hs.dgsw.smartschool.remote.utils

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.hs.dgsw.smartschool.domain.exception.BadRequestException
import kr.hs.dgsw.smartschool.domain.exception.OtherHttpException
import kr.hs.dgsw.smartschool.remote.response.ErrorResponse
import retrofit2.HttpException

suspend inline fun <T> dodamApiCall(
    crossinline callFunction: suspend () -> T,
): T {
    return try {
        withContext(Dispatchers.IO) {
            callFunction.invoke()
        }
    } catch (e: HttpException) {
        val message: String = getErrorMessage(e)

        throw when(e.code()) {
            400 -> BadRequestException(
                message = message,
                fieldErrors = emptyList()
            )
            else -> OtherHttpException(
                code = e.code(),
                message = message,
            )
        }
    }
}

private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 오류가 발생했습니다."

fun getErrorMessage(exception: HttpException): String {
    val errorStr = exception.response()?.errorBody()?.string()
    val errorDto: ErrorResponse? = Gson().fromJson(
        errorStr, ErrorResponse::class.java
    )

    return "STATUS : ${errorDto?.status ?: -1}\nMESSAGE : ${errorDto?.message ?: UNKNOWN_ERROR_MESSAGE}"
}