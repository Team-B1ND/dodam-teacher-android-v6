package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label2
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PointScreen(
    navController: NavController,
    pointViewModel: PointViewModel = hiltViewModel(),
) {

    val state = pointViewModel.collectAsState()
    val context = LocalContext.current

    BackHandler {
        checkPage(navController, state.value.page, pointViewModel)
    }

    Column(
        modifier = Modifier.background(DodamTheme.color.White)
    ) {
        DodamAppBar(onStartIconClick = {
            checkPage(navController, state.value.page, pointViewModel)
        })

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            PointCategorySelectBar(
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                categoryList = listOf("전체", "1학년", "2학년", "3학년"),
                onSelectedItem = { idx -> context.shortToast(idx.toString()) }
            )
            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))
            PointCategorySelectBar(
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                categoryList = listOf("전체", "1반", "2반", "3반", "4반"),
                onSelectedItem = { idx -> context.shortToast(idx.toString()) }
            )
            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding * 2))
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

@Composable
private fun PointCategorySelectBar(
    modifier: Modifier = Modifier,
    categoryList: List<String>,
    onSelectedItem: (idx: Int) -> Unit,
) {
    var currentSelectedIdx by remember { mutableStateOf(0) }

    Row(
        modifier = modifier
            .surface(RoundedCornerShape(100.dp), DodamColor.Background)
            .fillMaxWidth()
    ) {
        categoryList.forEachIndexed { idx, category ->
            Box(
                modifier = Modifier
                    .background(
                        shape = when (idx) {
                            0 -> RoundedCornerShape(
                                topStart = 100.dp,
                                bottomStart = 100.dp
                            )
                            categoryList.size - 1 -> RoundedCornerShape(
                                topEnd = 100.dp,
                                bottomEnd = 100.dp
                            )
                            else -> RectangleShape
                        },
                        color = if (currentSelectedIdx == idx) {
                            DodamTheme.color.MainColor400
                        } else
                            DodamTheme.color.Background
                    )
                    .dodamClickable(rippleEnable = false) {
                        onSelectedItem(idx)
                        currentSelectedIdx = idx
                    }
                    .weight(1f)
                    .height(30.dp)
            ) {
                Label2(
                    text = category,
                    textColor = if (currentSelectedIdx == idx)
                        DodamTheme.color.White
                    else
                        DodamTheme.color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
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
