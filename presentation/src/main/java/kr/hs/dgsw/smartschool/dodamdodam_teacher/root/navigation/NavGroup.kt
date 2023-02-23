package kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation

sealed class NavGroup(val group: String) {

    object Auth : NavGroup("auth") {
        const val LOGIN = "login"
        const val JOIN = "join"
    }

    object Main : NavGroup("main") {
        const val HOME = "home"
    }
}
