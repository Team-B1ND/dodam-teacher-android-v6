package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.Outgoing
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutSleeping
import java.time.LocalDateTime

interface OutRepository {

    suspend fun getOutgoingByDate(date: String): List<Out>

    suspend fun getOutSleepingByDate(data: String): List<Out>

    suspend fun getOutgoingById(id: Int): OutItem

    suspend fun getOutsleepingById(id: Int): OutItem

    suspend fun getOutgoingsByDate(date: LocalDateTime): List<OutItem>

    suspend fun getOutsleepingsByDate(date: LocalDateTime): List<OutItem>

    suspend fun getOutByStudentId(id: Int): List<OutItem>

    suspend fun clearOutData()

    suspend fun allowOutgoing(ids: List<Int>)

    suspend fun deleteOutgoing(id: Int)

    suspend fun cancelAllowOutgoing(ids: List<Int>)

    suspend fun denyOutgoing(ids: List<Int>)

    suspend fun deleteOutsleeping(id: Int)

    suspend fun allowOutsleeping(ids: List<Int>)

    suspend fun cancelAllowOutsleeping(ids: List<Int>)

    suspend fun denyOutsleeping(ids: List<Int>)
}
