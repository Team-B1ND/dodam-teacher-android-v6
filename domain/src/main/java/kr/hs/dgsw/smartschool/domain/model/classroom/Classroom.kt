package kr.hs.dgsw.smartschool.domain.model.classroom

import kr.hs.dgsw.smartschool.domain.model.place.Place

data class Classroom(
    val grade: Int,
    val id: Int,
    val place: Place,
    val room: Int,
) {
    constructor(id: Int) : this(
        grade = -1,
        id = id,
        place = Place(),
        room = -1,
    )
}
