package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.model.out.OutType
import kr.hs.dgsw.smartschool.local.entity.out.OutEntity


internal fun OutEntity.toModel(): Out =
    Out(
        id = id,
        reason = reason,
        status = status.toOutStatus(),
        student = Student(
            id = studentId,
            name = studentName,
            grade = studentGrade,
            room = studentRoom,
            number = studentNumber
        ),
        rejectReason = rejectReason,
        startOutDate = startOutDate,
        endOutDate = endOutDate,
        createdAt = createdAt,
        modifiedAt = modifiedAt
    )

internal fun Out.toEntity(
    type: OutType
): OutEntity =
    OutEntity(
        id = id,
        reason = reason,
        status = status.name,
        studentId = student.id,
        studentName = student.name,
        studentGrade = student.grade,
        studentRoom = student.room,
        studentNumber = student.number,
        rejectReason = rejectReason,
        startOutDate = startOutDate,
        endOutDate = endOutDate,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        type = type.name
    )

//internal fun OutEntity.toModel(): OutItem =
//    OutItem(
//        id = id,
//        reason = reason,
//        status = status.toOutStatus(),
//        studentId = studentId,
//        teacherId = teacherId,
//        startOutDate = startOutDate.yearDateTimeHourToLocalDate(),
//        endOutDate = endOutDate.yearDateTimeHourToLocalDate(),
//        arrivedDate = createdAt.yearDateTimeHourToLocalDate(),
//        checkedDate = modifiedAt.yearDateTimeHourToLocalDate(),
//        type = type.toOutType()
//    )
//
//internal fun OutItem.toEntity(): OutEntity {
//    return OutEntity(
//        id = id,
//        reason = reason,
//        status = status.name,
//        studentId = studentId,
//        teacherId = teacherId,
//        startOutDate = startOutDate.toString(),
//        endOutDate = endOutDate.toString(),
//        createdAt = arrivedDate?.toString()!!,
//        modifiedAt = checkedDate?.toString()!!,
//        type = type.name,
//    )
//}

internal fun String.toOutStatus(): OutStatus =
    when (this) {
        OutStatus.ALLOWED.name -> OutStatus.ALLOWED
        OutStatus.PENDING.name -> OutStatus.PENDING
        OutStatus.REJECTED.name -> OutStatus.REJECTED
        else -> OutStatus.PENDING
    }

//internal fun String.toOutType(): OutType =
//    when (this) {
//        OutType.OUTGOING.name -> OutType.OUTGOING
//        OutType.OUTSLEEPING.name -> OutType.OUTSLEEPING
//        else -> OutType.OUTGOING
//    }
