package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity
import kr.hs.dgsw.smartschool.local.entity.teacher.TeacherEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface TeacherDao : BaseDao<TeacherDao> {

    @Query("SELECT * FROM ${DodamTable.TEACHER}")
    suspend fun getAllTeacher(): List<TeacherEntity>

    @Query("SELECT * FROM ${DodamTable.TEACHER} where teacherId=:id")
    suspend fun getTeacherById(id: Int): TeacherEntity

    @Query("SELECT * FROM ${DodamTable.TEACHER} where memberId=:id")
    suspend fun getTeacherByMemberId(id: String): TeacherEntity

    @Query("DELETE FROM ${DodamTable.TEACHER}")
    suspend fun deleteAllTeacher()
}
