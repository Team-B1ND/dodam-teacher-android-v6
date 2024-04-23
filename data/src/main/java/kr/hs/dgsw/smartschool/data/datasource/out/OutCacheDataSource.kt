package kr.hs.dgsw.smartschool.data.datasource.out

import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutType


interface OutCacheDataSource {

//    suspend fun getOutById(id: Int): OutItem?
//
//    suspend fun getOutByStatus(status: String): List<OutItem>
//
//    suspend fun getOutByStudentId(studentId: Int): List<OutItem>
//
    suspend fun getOutgoings(): List<Out>
//
    suspend fun getOutsleepings(): List<Out>
//
    suspend fun getNotAllowedOut(): List<Out>
//
    suspend fun getAllowedOutgoing(): List<Out>
//
    suspend fun getNotAllowedOutgoing(): List<Out>
//
    suspend fun getAllowedOutsleeping(): List<Out>
//
    suspend fun getNotAllowedOutsleeping(): List<Out>
//
    suspend fun deleteAllOut()
//
    suspend fun deleteTypeAllOut(type: OutType)

    suspend fun deleteOutById(id: Int)
//
//    suspend fun insertOut(outItem: OutItem)
//
    suspend fun insertOuts(type: OutType, outItems: List<Out>)
}
