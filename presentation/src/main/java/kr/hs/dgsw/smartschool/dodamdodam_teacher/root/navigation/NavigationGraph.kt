package kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.screen.JoinScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.screen.LoginScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.screen.EtcScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.screen.HomeScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.screen.OutScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.screen.MainScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen.StudyRoomScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.screen.MealScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.PointScreen

@Composable
fun NavigationGraph(
    enableAutoLogin: Boolean,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = getStartDestination(enableAutoLogin)) {

        composable(NavGroup.Auth.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(NavGroup.Auth.JOIN) {
            JoinScreen(navController = navController)
        }

        composable(NavGroup.Main.MAIN) {
            MainScreen(navController = navController)
        }

        composable(NavGroup.Main.STUDYROOM) {
            StudyRoomScreen(navController = navController)
        }

        composable(NavGroup.Main.OUT) {
            OutScreen(navController = navController)
        }

        composable(NavGroup.Main.ETC) {
            EtcScreen(navController = navController)
        }

        composable(NavGroup.Main.HOME) {
            HomeScreen(navController = navController)
        }

        composable(NavGroup.Feature.MEAL) {
            MealScreen(navController = navController)
        }

        composable(NavGroup.Feature.POINT) {
            PointScreen(navController = navController)
        }
    }
}

fun getStartDestination(enableAutoLogin: Boolean) =
    if (enableAutoLogin) NavGroup.Main.MAIN else NavGroup.Auth.LOGIN
