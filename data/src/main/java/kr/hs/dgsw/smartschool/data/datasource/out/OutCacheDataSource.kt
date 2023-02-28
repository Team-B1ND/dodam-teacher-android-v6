package kr.hs.dgsw.smartschool.data.datasource.out

import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem

interface OutCacheDataSource {

    suspend fun getAllOut(): Out

    suspend fun getOutById(id: Int): OutItem?

    suspend fun getOutByStatus(status: String): List<OutItem>

    suspend fun getOutByStudentId(studentId: Int): List<OutItem>

    suspend fun getOutgoing(): List<OutItem>

    suspend fun getOutsleeping(): List<OutItem>

    suspend fun getAllowedOut(): List<OutItem>

    suspend fun getNotAllowedOut(): List<OutItem>

    suspend fun getAllowedOutgoing(): List<OutItem>

    suspend fun getNotAllowedOutgoing(): List<OutItem>

    suspend fun getAllowedOutsleeping(): List<OutItem>

    suspend fun getNotAllowedOutsleeping(): List<OutItem>

    suspend fun deleteAllOut()

    suspend fun deleteOutById(id: Int)

    suspend fun insertOut(outItem: OutItem)

    suspend fun insertOuts(outItems: List<OutItem>)
}
