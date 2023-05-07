package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateTimeToLocalDate
import kr.hs.dgsw.smartschool.domain.model.evening_study.AllowCheck
import kr.hs.dgsw.smartschool.domain.model.evening_study.NightStudy
import kr.hs.dgsw.smartschool.domain.model.evening_study.NightStudyStudent
import kr.hs.dgsw.smartschool.remote.response.night_study.AllowCheckResponse
import kr.hs.dgsw.smartschool.remote.response.night_study.EveningStudyStudentResponse
import kr.hs.dgsw.smartschool.remote.response.night_study.NightStudyResponse

internal fun List<NightStudyResponse>.toModel(): List<NightStudy> =
    this.map {
        it.toModel()
    }

internal fun NightStudyResponse.toModel(): NightStudy =
    NightStudy(
        id = id,
        content = content,
        allowCheck = allowCheck.toAllowCheck(),
        reason = reason,
        student = student.toModel(),
        place = place,
        startAt = startAt.yearDateTimeToLocalDate(),
        endAt = endAt.yearDateTimeToLocalDate(),
        createdAt = createdAt.yearDateTimeToLocalDate(),
        isPhone = isPhone,
    )

internal fun EveningStudyStudentResponse.toModel(): NightStudyStudent =
    NightStudyStudent(
        name = name,
        grade = grade,
        room = room,
        number = number,
    )

internal fun AllowCheckResponse.toAllowCheck(): AllowCheck =
    when (this.name) {
        AllowCheck.ALLOWED.name -> AllowCheck.ALLOWED
        AllowCheck.PENDING.name -> AllowCheck.PENDING
        AllowCheck.DENIED.name -> AllowCheck.DENIED
        else -> AllowCheck.PENDING
    }
