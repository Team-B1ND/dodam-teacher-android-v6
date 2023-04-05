package kr.hs.dgsw.smartschool.data.datasource.out

import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem

interface OutRemoteDataSource {

    suspend fun getOutsByDate(date: String): Out

    suspend fun getOutgoing(id: Int): OutItem

    suspend fun deleteOutgoing(id: Int)

    suspend fun allowOutgoing(ids: List<Int>)

    suspend fun cancelAllowOutgoing(ids: List<Int>)

    suspend fun denyOutgoing(ids: List<Int>)

    suspend fun getOutsleeping(id: Int): OutItem

    suspend fun deleteOutsleeping(id: Int)

    suspend fun allowOutsleeping(ids: List<Int>)

    suspend fun cancelAllowOutsleeping(ids: List<Int>)

    suspend fun denyOutsleeping(ids: List<Int>)
}
