package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.classroom.ClassroomEntity
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface ClassroomDao : BaseDao<ClassroomDao> {

    @Query("SELECT * FROM ${DodamTable.CLASSROOM}")
    suspend fun getAllClassroom(): List<ClassroomEntity>

    @Query("SELECT * FROM ${DodamTable.CLASSROOM} where id=:id")
    suspend fun getClassroomById(id: Int): ClassroomEntity

    @Query("SELECT * FROM ${DodamTable.CLASSROOM} where placeId=:id")
    suspend fun getClassroomByPlaceId(id: Int): ClassroomEntity

    @Query("DELETE FROM ${DodamTable.CLASSROOM}")
    suspend fun deleteAllClassroom()
}
