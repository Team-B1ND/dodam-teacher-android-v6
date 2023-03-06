package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PointScreen(
    navController: NavController,
    pointViewModel: PointViewModel = hiltViewModel(),
) {

    val state = pointViewModel.collectAsState()

    BackHandler {
        checkPage(navController, state.value.page, pointViewModel)
    }

    Column {
        DodamAppBar(onStartIconClick = {
            checkPage(navController, state.value.page, pointViewModel)
        })

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    when (state.value.page) {
                        1 -> DodamTheme.color.SecondaryColor300
                        2 -> DodamTheme.color.MainColor400
                        else -> DodamTheme.color.Gray500
                    }
                )
        ) {


        }

        Box(
            modifier = Modifier.padding(
                bottom = DodamDimen.ScreenSidePadding,
                start = DodamDimen.ScreenSidePadding,
                end = DodamDimen.ScreenSidePadding,
            )
        ) {
            DodamMaxWidthButton(
                text = "다음",
            ) {
                when (state.value.page) {
                    1 -> pointViewModel.updatePage(2)
                    2 -> pointViewModel.updatePage(3)
                    3 -> {}
                }
            }
        }
    }
}

private fun checkPage(navController: NavController, page: Int, pointViewModel: PointViewModel) {
    when (page) {
        1 -> navController.popBackStack()
        2 -> pointViewModel.updatePage(1)
        else -> pointViewModel.updatePage(2)
    }
}
