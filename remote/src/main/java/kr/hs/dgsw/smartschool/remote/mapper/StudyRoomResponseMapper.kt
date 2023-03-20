package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateToLocalDate
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponseStatus

internal fun List<StudyRoomResponse>.toModel(): List<StudyRoom> =
    this.map {
        it.toModel()
    }

internal fun StudyRoomResponse.toModel(): StudyRoom =
    StudyRoom(
        id = id,
        date = date.yearDateToLocalDate(),
        timeTable = timeTable.toModel(),
        place = place.toModel(),
        studentId = student.studentId,
        status = status.toStudyRoomStatus(),
        teacher = teacher?.toModel()
    )

internal fun StudyRoomResponseStatus.toStudyRoomStatus(): StudyRoomStatus =
    when (this.name) {
        StudyRoomResponseStatus.CHECKED.name -> StudyRoomStatus.CHECKED
        StudyRoomResponseStatus.PENDING.name -> StudyRoomStatus.PENDING
        else -> StudyRoomStatus.PENDING
    }
