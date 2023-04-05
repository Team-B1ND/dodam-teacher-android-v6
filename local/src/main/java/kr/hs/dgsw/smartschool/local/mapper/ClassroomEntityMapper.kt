package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.local.entity.classroom.ClassroomEntity

internal fun List<ClassroomEntity>.toModel(): List<Classroom> =
    this.map {
        it.toModel()
    }

internal fun ClassroomEntity.toModel(): Classroom =
    Classroom(
        grade = grade,
        id = id,
        place = Place(
            id = placeId,
            name = "",
            type = Place.PlaceType(
                id = -1,
                name = ""
            )
        ),
        room = room
    )

internal fun List<Classroom>.toEntity(): List<ClassroomEntity> =
    this.map {
        it.toEntity()
    }

internal fun Classroom.toEntity(): ClassroomEntity =
    ClassroomEntity(
        id = id,
        placeId = place.id,
        grade = grade,
        room = room,
    )
