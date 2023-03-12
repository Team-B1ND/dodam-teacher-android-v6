package kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation

sealed class NavGroup(val group: String) {

    object Auth : NavGroup("auth") {
        const val LOGIN = "login"
        const val JOIN = "join"
    }

    object Main : NavGroup("main") {
        const val MAIN = "main"
        const val HOME = "home"
        const val STUDYROOM = "studyroom"
        const val OUT = "OUT"
        const val ETC = "ETC"
    }

    object Feature : NavGroup("feature") {
        const val MEAL = "meal"
        const val POINT = "point"
        const val SCHEDULE = "schedule"
        const val ITMAP = "itmap"
        const val ITMAP_DETAIL = "itmapDetail/{companyId}"
    }
}
