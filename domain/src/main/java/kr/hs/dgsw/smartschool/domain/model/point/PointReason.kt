package kr.hs.dgsw.smartschool.domain.model.point

data class PointReason(
    val id: Int,
    val reason: String,
    val score: Int,
    val type: PointType,
    val place: PointPlace,
)
