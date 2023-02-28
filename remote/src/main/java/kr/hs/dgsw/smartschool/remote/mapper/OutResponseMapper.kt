package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateTimeToLocalDate
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.MemberStatus
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.remote.response.out.OutDetailResponse
import kr.hs.dgsw.smartschool.remote.response.out.OutResponse
import kr.hs.dgsw.smartschool.remote.response.out.OutResponseStatus

internal fun OutResponse.toOut(): Out =
    Out(
        outgoings = outgoingList.map { it.toOutItem() },
        outsleepings = outsleepingList.map { it.toOutItem() }
    )

internal fun OutDetailResponse.toOutItem(): OutItem =
    OutItem(
        id = id,
        reason = reason,
        status = status.toOutStatus(),
        studentId = student.id,
        teacher = if (teacher != null) Teacher(
            id = 0,
            member = Member(
                email = teacher.email,
                id = teacher.id,
                joinDate = null,
                name = teacher.name,
                profileImage = teacher.profileImage,
                role = MemberRole.TEACHER,
                status = MemberStatus.ACTIVE,
            ),
            phone = teacher.phone,
            position = "",
            tel = "",
        ) else null,
        startOutDate = startOutDate.yearDateTimeToLocalDate(),
        endOutDate = endOutDate.yearDateTimeToLocalDate(),
        arrivedDate = arrivedDate?.yearDateTimeToLocalDate(),
        checkedDate = checkedDate?.yearDateTimeToLocalDate(),
    )

internal fun OutResponseStatus.toOutStatus(): OutStatus =
    when (this.name) {
        OutStatus.ALLOWED.name -> OutStatus.ALLOWED
        OutStatus.PENDING.name -> OutStatus.PENDING
        OutStatus.DENIED.name -> OutStatus.DENIED
        else -> OutStatus.PENDING
    }


