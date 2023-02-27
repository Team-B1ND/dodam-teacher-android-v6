package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm.HomeViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val homeState = homeViewModel.container.stateFlow.collectAsState().value

    homeViewModel.collectSideEffect {

    }

    Box(
        modifier = Modifier
            .background(DodamColor.Background)
            .fillMaxSize()
    ) {

    }
}
