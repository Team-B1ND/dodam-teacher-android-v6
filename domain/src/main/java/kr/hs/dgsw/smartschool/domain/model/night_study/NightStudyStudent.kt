package kr.hs.dgsw.smartschool.domain.model.night_study

data class NightStudyStudent(
    val id: Int,
    val name: String,
    val grade: Int,
    val room: Int,
    val number: Int,
)
