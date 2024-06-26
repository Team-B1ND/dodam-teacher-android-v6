package kr.hs.dgsw.smartschool.data.utils

import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.yearDateToLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(this, formatter)
}

fun String?.yearDateTimeToLocalDate(): LocalDateTime {
    try {
        if (this != null) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return LocalDateTime.parse(this.split(".")[0], formatter)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return LocalDateTime.now()
}
fun String.yearDateTimeToLocalDateT(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val time = if (length == 16) "$this:00" else this
    return LocalDateTime.parse(time.split(".")[0], formatter)
}

fun String.yearDateTimeHourToLocalDate(): LocalDateTime {
    return LocalDateTime.parse(this)
}

fun toLocalDate(year: Int, month: Int, day: Int): LocalDate =
    "$year-%02d-%02d".format(month, day).yearDateToLocalDate()
