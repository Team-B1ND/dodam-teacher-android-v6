package kr.hs.dgsw.smartschool.domain.model.place

data class Place(
    val id: Int,
    val name: String,
    val type: PlaceType,
) {
    data class PlaceType(
        val id: Int,
        val name: String,
    )
}
