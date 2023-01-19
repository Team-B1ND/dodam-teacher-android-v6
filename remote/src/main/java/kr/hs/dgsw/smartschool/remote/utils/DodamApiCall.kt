package kr.hs.dgsw.smartschool.remote.utils

import com.google.gson.Gson
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.hs.dgsw.smartschool.domain.exception.BadRequestException
import kr.hs.dgsw.smartschool.domain.exception.ConflictException
import kr.hs.dgsw.smartschool.domain.exception.ExpiredRefreshTokenException
import kr.hs.dgsw.smartschool.domain.exception.ForbiddenException
import kr.hs.dgsw.smartschool.domain.exception.NoConnectivityException
import kr.hs.dgsw.smartschool.domain.exception.NoInternetException
import kr.hs.dgsw.smartschool.domain.exception.NotFoundException
import kr.hs.dgsw.smartschool.domain.exception.OtherHttpException
import kr.hs.dgsw.smartschool.domain.exception.ServerException
import kr.hs.dgsw.smartschool.domain.exception.TimeOutException
import kr.hs.dgsw.smartschool.domain.exception.TooManyRequestsException
import kr.hs.dgsw.smartschool.domain.exception.UnAuthorizedException
import kr.hs.dgsw.smartschool.domain.exception.UnknownException
import kr.hs.dgsw.smartschool.remote.response.ErrorResponse
import retrofit2.HttpException

const val EXPIRED_TOKEN_MESSAGE = "만료된 토큰"

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
            401 -> {
                if (e.message == EXPIRED_TOKEN_MESSAGE) {
                    ExpiredRefreshTokenException()
                } else {
                    UnAuthorizedException(
                        message = message
                    )
                }
            }
            403 -> ForbiddenException(
                message = message
            )

            404 -> NotFoundException(
                message = message,
            )
            408 -> TimeOutException(
                message = message
            )
            409 -> ConflictException(
                message = message
            )
            429 -> TooManyRequestsException(
                message = message,
            )
            500, 501, 502, 503 -> ServerException(
                message = message,
            )
            else -> OtherHttpException(
                code = e.code(),
                message = message,
            )
        }
    } catch (e: UnknownHostException) {
        throw NoInternetException()
    } catch (e: SocketTimeoutException) {
        throw TimeOutException(
            message = e.message,
        )
    } catch (e: ExpiredRefreshTokenException) {
        throw e
    } catch (e: NoInternetException) {
        throw NoInternetException()
    } catch (e: NoConnectivityException) {
        throw NoConnectivityException()
    } catch (e: Exception) {
        throw UnknownException(
            message = e.message,
        )
    }
}

private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 오류가 발생했습니다."

fun getErrorMessage(exception: HttpException): String {
    val errorStr = exception.response()?.errorBody()?.string()
    val errorDto: ErrorResponse? = Gson().fromJson(
        errorStr, ErrorResponse::class.java
    )

    return errorDto?.message ?: UNKNOWN_ERROR_MESSAGE
}
