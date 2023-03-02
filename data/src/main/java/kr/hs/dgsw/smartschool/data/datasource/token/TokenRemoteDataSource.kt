package kr.hs.dgsw.smartschool.data.datasource.token

interface TokenRemoteDataSource {

    suspend fun getToken(token: String): String
}