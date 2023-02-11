package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.entity.parent.ParentEntity
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface ParentDao : BaseDao<ParentEntity> {

    @Query("SELECT * FROM ${DodamTable.PARENT}")
    suspend fun getAllParent(): List<ParentEntity>

    @Query("SELECT * FROM ${DodamTable.PARENT} where id=:id")
    suspend fun getParentById(id: Int): ParentEntity

    @Query("SELECT * FROM ${DodamTable.PARENT} where studentId=:studentId")
    suspend fun getParentByStudentId(studentId: Int): ParentEntity

    @Query("DELETE FROM ${DodamTable.PARENT}")
    suspend fun deleteAllParent()
}
