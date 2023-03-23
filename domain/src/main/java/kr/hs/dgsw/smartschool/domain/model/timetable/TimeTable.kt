package kr.hs.dgsw.smartschool.domain.model.timetable

data class TimeTable(
    val id: Int,
    val name: String,
    val type: TimeTableType,
    val startTime: String,
    val endTime: String
) {
    constructor(id: Int) : this(
        id = id,
        name = "",
        type = TimeTableType.WEEKDAY,
        startTime = "",
        endTime = "",
    )
}
