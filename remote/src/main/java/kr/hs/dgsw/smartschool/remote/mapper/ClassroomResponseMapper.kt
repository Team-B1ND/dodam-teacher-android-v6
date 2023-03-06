package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.remote.response.classroom.ClassroomResponse

internal fun ClassroomResponse.toModel(): Classroom =
    Classroom(
        grade = grade,
        id = id,
        place = place.toModel(),
        room = room
    )