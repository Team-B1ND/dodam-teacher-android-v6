package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponseStatus

internal fun StudyRoomResponse.toModel(): StudyRoom =
    StudyRoom(
        id = id,
        date = date,
        timeTable = TimeTable(
            id = timeTable.id,
            name = timeTable.name,
            type = timeTable.type.toTimeTableType(),
            startTime = timeTable.startTime,
            endTime = timeTable.endTime
        ),
        place = Place(
            place.id,
            place.name,
            Place.PlaceType(
                place.type.id,
                place.type.name
            )
        ),
        student = Student(
            classroom = student.classroom.toModel(),
            id = student.id,
            member = student.member.toMember(),
            number = student.number,
            phone = student.phone
        ),
        status = status.toStudyRoomStatus(),
        teacher = null
    )

internal fun List<StudyRoomResponse>.toModel(): StudyRoomList =
    StudyRoomList(
        studyRoomList = this.map { it.toModel() },
        otherStudents = null
    )

internal fun StudyRoomResponseStatus.toStudyRoomStatus(): StudyRoomStatus =
    when (this.name) {
        StudyRoomResponseStatus.CHECKED.name -> StudyRoomStatus.CHECKED
        StudyRoomResponseStatus.PENDING.name -> StudyRoomStatus.PENDING
        else -> StudyRoomStatus.PENDING
    }
