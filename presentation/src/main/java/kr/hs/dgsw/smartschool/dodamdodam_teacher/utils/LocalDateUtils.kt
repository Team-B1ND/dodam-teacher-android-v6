package kr.hs.dgsw.smartschool.dodamdodam_teacher.utils

import java.time.LocalDate
import java.time.LocalDateTime

fun LocalDateTime.toSimpleYearDateTime(): String =
    "$year.$monthValue.$dayOfMonth %02d:%02d".format(hour, minute)

fun LocalDate.dayOfWeek(): String = when (this.dayOfWeek.value) {
    1 -> "월"
    2 -> "화"
    3 -> "수"
    4 -> "목"
    5 -> "금"
    6 -> "토"
    7 -> "일"
    else -> "월"
}
