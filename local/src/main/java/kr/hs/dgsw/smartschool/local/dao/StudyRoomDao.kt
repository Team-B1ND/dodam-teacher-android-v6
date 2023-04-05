package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.studyroom.StudyRoomEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface StudyRoomDao : BaseDao<StudyRoomEntity> {

    @Query("SELECT * FROM ${DodamTable.STUDYROOM}")
    suspend fun getStudyRooms(): List<StudyRoomEntity>

    @Query("DELETE FROM ${DodamTable.STUDYROOM}")
    suspend fun deleteStudyRooms()
}
