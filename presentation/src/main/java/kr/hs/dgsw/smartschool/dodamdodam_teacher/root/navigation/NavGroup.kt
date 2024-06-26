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
        const val ITMAP_DETAIL = "itmapDetail/{companyId}"
        const val CURRENT_OUT = "currentOut"
        const val NIGHT_STUDY = "nightStudy"
        const val CURRENT_NIGHT_STUDY = "currentNightStudy"
    }

    object Studyroom : NavGroup("studyroom") {
        const val STUDYROOM_APPLY = "studyroom_apply/{type}"
        const val STUDYROOM_CTRL = "studyroom_ctrl/{studentId}"
    }
}
