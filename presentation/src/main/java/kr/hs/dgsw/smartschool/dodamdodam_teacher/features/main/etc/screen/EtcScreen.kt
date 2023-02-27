package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.screen

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
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi.EtcSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.vm.EtcViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun EtcScreen(
    etcViewModel: EtcViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val etcState = etcViewModel.container.stateFlow.collectAsState().value

    etcViewModel.collectSideEffect {
        when (it) {
            is EtcSideEffect.ToastLogoutErrorMessage -> {
                Log.e("TestHome", it.throwable.stackTraceToString())
                Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
            }
            is EtcSideEffect.SuccessLogout -> {
                navController.navigate(NavGroup.Auth.LOGIN) {
                    popUpTo(NavGroup.Main.MAIN) {
                        inclusive = true
                    }
                }
            }
        }
    }

    if (etcState.isLoading)
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
                etcViewModel.logout()
            }
        }
}