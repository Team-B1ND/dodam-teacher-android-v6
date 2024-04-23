package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.out.Out
import java.time.LocalDate
import java.time.LocalDateTime

interface OutRepository {

    suspend fun getOutgoingByDateRemote(date: String): List<Out>

    suspend fun getOutSleepingByDateRemote(data: String): List<Out>

    suspend fun getOutgoingByDateLocal(date: LocalDate): List<Out>

    suspend fun getOutSleepingByDateLocal(date: LocalDate): List<Out>

//    suspend fun getOutgoingById(id: Int): OutItem
//
//    suspend fun getOutsleepingById(id: Int): OutItem

    suspend fun getOutSleepingValid(): List<Out>

//    suspend fun getOutgoingsByDate(date: LocalDateTime): List<Out>
//
//    suspend fun getOutsleepingsByDate(date: LocalDateTime): List<Out>
//
//    suspend fun getOutByStudentId(id: Int): List<OutItem>


    suspend fun allowOutgoing(id: Int)

    suspend fun deleteOutgoing(id: Int)

    suspend fun cancelAllowOutgoing(id: Int)

    suspend fun denyOutgoing(ids: List<Int>)

    suspend fun deleteOutsleeping(id: Int)

    suspend fun allowOutsleeping(id: Int)

    suspend fun cancelAllowOutsleeping(id: Int)

    suspend fun denyOutsleeping(ids: List<Int>)
}
