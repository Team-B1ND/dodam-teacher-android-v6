package kr.hs.dgsw.smartschool.data.datasource.out

import kr.hs.dgsw.smartschool.domain.model.out.Out

interface OutRemoteDataSource {

    suspend fun getOutgoingByDate(date: String): List<Out>

    suspend fun getOutSleepingByDate(data: String): List<Out>

    suspend fun getOutSleepingValidByDate(): List<Out>

//    suspend fun getOutgoing(id: Int): OutItem

    suspend fun deleteOutgoing(id: Int)

    suspend fun allowOutgoing(id: Int)

    suspend fun cancelAllowOutgoing(id: Int)

    suspend fun denyOutgoing(ids: List<Int>)

//    suspend fun getOutsleeping(id: Int): OutItem

    suspend fun deleteOutsleeping(id: Int)

    suspend fun allowOutsleeping(id: Int)

    suspend fun cancelAllowOutsleeping(id: Int)

    suspend fun denyOutsleeping(ids: List<Int>)
}
