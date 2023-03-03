package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import kr.hs.dgsw.smartschool.domain.model.studyroom.student.Student
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTable
import kr.hs.dgsw.smartschool.remote.response.meal.MealResponse
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseRole
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponseStatus
import kr.hs.dgsw.smartschool.remote.response.studyroom.TimeTableType
import kr.hs.dgsw.smartschool.remote.utils.yearDateToLocalDate

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
        student = Student(student.id),
        status = status.toStudyRoomStatus(),
        teacher =
        with(teacher) {
            Teacher(
                id = id,
                member = with(member) {
                    Member(
                        email = email,
                        id = id.toString(),
                        joinDate = joinDate,
                        name = name,
                        profileImage = profileImage,
                        role = role.toMemberRole(),
                        status = status.toMemberStatus()
                    )
                },
                tel = tel,
                position = position,
                phone = phone
            )
        }
    )

internal fun List<StudyRoomResponse>.toModel(): StudyRoomList =
    StudyRoomList(
        studyRoomList = this.map { it.toModel() }
    )

internal fun TimeTableType.toTimeTableType(): kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTableType =
    when (this.name) {
        TimeTableType.WEEKDAY.name -> kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTableType.WEEKDAY
        TimeTableType.WEEKEND.name -> kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTableType.WEEKEND
        else -> kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTableType.WEEKDAY
    }

internal fun StudyRoomResponseStatus.toStudyRoomStatus(): StudyRoomStatus =
    when (this.name){
        StudyRoomResponseStatus.CHECKED.name -> StudyRoomStatus.CHECKED
        StudyRoomResponseStatus.PENDING.name -> StudyRoomStatus.PENDING
        else -> StudyRoomStatus.PENDING
    }