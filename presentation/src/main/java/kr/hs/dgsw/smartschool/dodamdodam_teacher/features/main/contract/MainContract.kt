package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract

import java.time.LocalDateTime

data class MainState(
    val setClassroomLoading: Boolean = true,
    val setMembersLoading: Boolean = true,
    val setStudentsLoading: Boolean = true,
    val setTeachersLoading: Boolean = true,
    val setOutsLoading: Boolean = true,
    val setTimeTablesLoading: Boolean = true,
    val setStudyRoomsLoading: Boolean = true,

    val getStudyRoomTime: LocalDateTime? = null,
    val getOutTime: LocalDateTime? = null,
    val selectedTab: Int = 0,
)

sealed class MainSideEffect
