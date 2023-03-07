package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract

data class MainState(
    val setClassroomLoading: Boolean = true,
)

sealed class MainSideEffect {

}