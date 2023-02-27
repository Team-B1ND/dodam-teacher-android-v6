package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMediumRoundedButton
import kr.hs.dgsw.smartschool.components.component.set.navtab.DodamNavBar
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm.HomeViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val homeState = homeViewModel.container.stateFlow.collectAsState().value

    homeViewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.ToastLogoutErrorMessage -> {
                Log.e("TestHome", it.throwable.stackTraceToString())
                Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
            }
            is HomeSideEffect.SuccessLogout -> {
                navController.navigate(NavGroup.Auth.LOGIN) {
                    popUpTo(NavGroup.Main.HOME) {
                        inclusive = true
                    }
                }
            }
        }
    }

    if (homeState.isLoading)
        LoadInFullScreen()
    else
        Box(
            modifier = Modifier
                .background(DodamColor.SecondaryColor300)
                .fillMaxSize()
        ) {
            DodamMediumRoundedButton(
                text = "LOGOUT",
                modifier = Modifier.align(Alignment.Center)
            ) {
                homeViewModel.logout()
            }
        }
}
