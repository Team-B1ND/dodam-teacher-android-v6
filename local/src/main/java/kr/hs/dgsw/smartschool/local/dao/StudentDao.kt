package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface StudentDao : BaseDao<StudentDao> {

    @Query("SELECT * FROM ${DodamTable.STUDENT}")
    suspend fun getAllStudent(): List<StudentEntity>

    @Query("SELECT * FROM ${DodamTable.STUDENT} where studentId=:id")
    suspend fun getStudentById(id: Int): StudentEntity

    @Query("SELECT * FROM ${DodamTable.STUDENT} where memberId=:id")
    suspend fun getStudentByMemberId(id: String): StudentEntity

    @Query("DELETE FROM ${DodamTable.STUDENT}")
    suspend fun deleteAllStudent()
}
