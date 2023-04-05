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

    constructor() : this(
        id = -1,
        name = "",
        type = PlaceType(
            id = -1,
            name = ""
        )
    )

    constructor(id: Int) : this(
        id = id,
        name = "",
        type = PlaceType(
            id = -1,
            name = "",
        )
    )
}
