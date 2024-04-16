package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateToLocalDate
import kr.hs.dgsw.smartschool.domain.model.night_study.AllowCheck
import kr.hs.dgsw.smartschool.domain.model.night_study.NightStudy
import kr.hs.dgsw.smartschool.domain.model.night_study.NightStudyStudent
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
        reason = reasonForPhone,
        state = status.toOutStatus(),
        student = student.toModel(),
        place = place,
        startAt = startAt.yearDateToLocalDate(),
        endAt = endAt.yearDateToLocalDate(),
        createdAt = createdAt?:"",
        modifiedAt = modifiedAt?:"",
        isPhone = doNeedPhone,

    )

internal fun EveningStudyStudentResponse.toModel(): NightStudyStudent =
    NightStudyStudent(
        id = id,
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
