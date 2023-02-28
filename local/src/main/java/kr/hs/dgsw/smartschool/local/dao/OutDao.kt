package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.out.OutEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface OutDao : BaseDao<OutEntity> {

    @Query("SELECT * FROM ${DodamTable.OUT}")
    suspend fun getAllOut(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE id=:id")
    suspend fun getOutById(id: Int): OutEntity?

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE status=:status")
    suspend fun getOutByStatus(status: String): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE studentId=:studentId")
    suspend fun getOutByStudentId(studentId: Int): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE startOutDate=endOutDate")
    suspend fun getOutgoing(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE startOutDate!=endOutDate")
    suspend fun getOutsleeping(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE teacherId==null")
    suspend fun getAllowedOut(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE teacherId!=null")
    suspend fun getNotAllowedOut(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE teacherId==null AND startOutDate=endOutDate")
    suspend fun getAllowedOutgoing(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE teacherId!=null AND startOutDate=endOutDate")
    suspend fun getNotAllowedOutgoing(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE teacherId==null AND startOutDate!=endOutDate")
    suspend fun getAllowedOutsleeping(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE teacherId!=null AND startOutDate!=endOutDate")
    suspend fun getNotAllowedOutsleeping(): List<OutEntity>

    @Query("DELETE FROM ${DodamTable.OUT} where id=:id")
    suspend fun deleteOutById(id: Int)

    @Query("DELETE FROM ${DodamTable.OUT}")
    suspend fun deleteAllOut()
}