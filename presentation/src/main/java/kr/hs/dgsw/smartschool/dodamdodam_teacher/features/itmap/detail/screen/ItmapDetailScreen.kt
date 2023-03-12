package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Title1
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.mvi.ItmapDetailSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.vm.ItmapDetailViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ItmapDetailScreen(
    companyId: Int,
    navController: NavController,
    itmapDetailViewModel: ItmapDetailViewModel = hiltViewModel(),
) {

    itmapDetailViewModel.getCompany(companyId)

    val state = itmapDetailViewModel.collectAsState().value
    val context = LocalContext.current

    itmapDetailViewModel.collectSideEffect {
        when (it) {
            is ItmapDetailSideEffect.ShowException -> {
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
            }
        }
    }

    if (state.loading)
        LoadInFullScreen()
    else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DodamTheme.color.White),
        ) {
            DodamAppBar(
                onStartIconClick = { navController.popBackStack() }
            )

            Title1(text = state.company?.name ?: "응애")
        }
}
