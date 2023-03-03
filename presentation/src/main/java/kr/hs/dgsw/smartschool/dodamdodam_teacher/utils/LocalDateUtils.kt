package kr.hs.dgsw.smartschool.dodamdodam_teacher.utils

import java.time.LocalDateTime

fun LocalDateTime.toSimpleYearDateTime(): String =
    "$year.$monthValue.$dayOfMonth %02d:%02d".format(hour, minute)
