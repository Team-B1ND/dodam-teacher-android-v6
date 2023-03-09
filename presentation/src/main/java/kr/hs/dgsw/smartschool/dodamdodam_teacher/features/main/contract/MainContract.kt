package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract

data class MainState(
    val setClassroomLoading: Boolean = true,
    val setMembersLoading: Boolean = true,
    val setStudentsLoading: Boolean = true,
    val setTeachersLoading: Boolean = true,
)

sealed class MainSideEffect
