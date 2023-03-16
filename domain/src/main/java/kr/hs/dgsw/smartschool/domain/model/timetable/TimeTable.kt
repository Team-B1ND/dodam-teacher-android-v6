package kr.hs.dgsw.smartschool.domain.model.timetable

data class TimeTable(
    val id: Int,
    val name: String,
    val type: TimeTableType,
    val startTime: String,
    val endTime: String
)
