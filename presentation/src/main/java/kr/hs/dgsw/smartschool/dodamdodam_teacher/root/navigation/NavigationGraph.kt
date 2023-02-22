package kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.screen.JoinScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.screen.LoginScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.screen.HomeScreen

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

        composable(NavGroup.Main.HOME) {
            HomeScreen(navController = navController)
        }
    }
}

fun getStartDestination(enableAutoLogin: Boolean) =
    if (enableAutoLogin) NavGroup.Main.HOME else NavGroup.Auth.LOGIN