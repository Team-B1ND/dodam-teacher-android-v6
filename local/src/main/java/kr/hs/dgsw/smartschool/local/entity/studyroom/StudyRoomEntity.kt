package kr.hs.dgsw.smartschool.local.entity.studyroom

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(tableName = DodamTable.STUDYROOM)
data class StudyRoomEntity(
    @PrimaryKey val id: Int,
    val date: String,
    val timeTableId: Int,
    val placeId: Int,
    val studentId: Int,
    val status: String,
    val teacherId: Int?,
)
