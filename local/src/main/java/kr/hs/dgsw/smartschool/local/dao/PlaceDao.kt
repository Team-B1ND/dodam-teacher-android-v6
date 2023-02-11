package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.classroom.ClassroomEntity
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.entity.place.PlaceEntity
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface PlaceDao : BaseDao<PlaceDao> {

    @Query("SELECT * FROM ${DodamTable.PLACE}")
    suspend fun getAllPlace(): List<PlaceEntity>

    @Query("SELECT * FROM ${DodamTable.PLACE} where id=:id")
    suspend fun getPlaceById(id: Int): PlaceEntity

    @Query("SELECT * FROM ${DodamTable.PLACE} where placeTypeId=:placeTypeId")
    suspend fun getAllPlaceByPlaceTypeId(placeTypeId: Int): List<PlaceEntity>

    @Query("DELETE FROM ${DodamTable.PLACE}")
    suspend fun deleteAllPlace()
}
