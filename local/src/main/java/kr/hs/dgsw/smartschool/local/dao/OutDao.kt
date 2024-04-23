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

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE type='OUTGOING'")
    suspend fun getOutgoing(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE type='OUTSLEEPING'")
    suspend fun getOutsleeping(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE status='ALLOWED'")
    suspend fun getAllowedOut(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE status='PENDING'")
    suspend fun getNotAllowedOut(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE status='ALLOWED' AND type='OUTGOING'")
    suspend fun getAllowedOutgoing(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE status='PENDING' AND type='OUTGOING'")
    suspend fun getNotAllowedOutgoing(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE status='ALLOWED' AND type='OUTSLEEPING'")
    suspend fun getAllowedOutsleeping(): List<OutEntity>

    @Query("SELECT * FROM ${DodamTable.OUT} WHERE status='PENDING' AND type='OUTSLEEPING'")
    suspend fun getNotAllowedOutsleeping(): List<OutEntity>

    @Query("DELETE FROM ${DodamTable.OUT} where id=:id")
    suspend fun deleteOutById(id: Int)

    @Query("DELETE FROM ${DodamTable.OUT}")
    suspend fun deleteAllOut()

    @Query("DELETE FROM ${DodamTable.OUT} WHERE type=:type")
    suspend fun deleteAllTypeOut(type: String)
}
