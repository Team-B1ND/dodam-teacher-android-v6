package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateToLocalDate
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.local.entity.studyroom.StudyRoomEntity

internal fun List<StudyRoomEntity>.toStudyRoomList(): List<StudyRoom> =
    this.map {
        it.toStudyRoom()
    }

internal fun StudyRoomEntity.toStudyRoom(): StudyRoom =
    StudyRoom(
        id = id,
        date = date.yearDateToLocalDate(),
        timeTable = TimeTable(timeTableId),
        place = Place(placeId),
        studentId = studentId,
        status = status.toStudyRoomStatus(),
        teacher = teacherId?.let { Teacher(it) }
    )

internal fun List<StudyRoom>.toStudyRoomEntityList(): List<StudyRoomEntity> =
    this.map {
        it.toStudyRoomEntity()
    }

internal fun StudyRoom.toStudyRoomEntity(): StudyRoomEntity =
    StudyRoomEntity(
        id = id,
        date = date.toString(),
        timeTableId = timeTable.id,
        placeId = place.id,
        studentId = studentId,
        status = status.name,
        teacherId = teacher?.id
    )

internal fun String.toStudyRoomStatus() = when (this) {
    StudyRoomStatus.CHECKED.name -> StudyRoomStatus.CHECKED
    StudyRoomStatus.PENDING.name -> StudyRoomStatus.PENDING
    else -> StudyRoomStatus.PENDING
}
