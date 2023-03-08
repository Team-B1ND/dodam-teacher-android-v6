package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.basic.button.Button
import kr.hs.dgsw.smartschool.components.component.basic.button.ButtonType
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.component.basic.toggle.DodamCheckBox
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.theme.Label2
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page.FirstPage
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page.SecondPage
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page.ThirdPage
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.member.Member
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PointScreen(
    navController: NavController,
    pointViewModel: PointViewModel = hiltViewModel(),
) {

    val state = pointViewModel.collectAsState().value
    val context = LocalContext.current

    BackHandler {
        checkPage(navController, state.page, pointViewModel)
    }

    pointViewModel.collectSideEffect {
        when(it) {
            is PointSideEffect.ShowException -> {
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("PointErrorLog", it.exception.stackTraceToString())
                navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier.background(DodamTheme.color.White)
    ) {
        DodamAppBar(onStartIconClick = {
            checkPage(navController, state.page, pointViewModel)
        })

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when(state.page) {
                1 -> FirstPage(state = state, viewModel = pointViewModel)
                2 -> SecondPage(state = state, viewModel = pointViewModel)
                3 -> ThirdPage(state = state, viewModel = pointViewModel)
            }
        }

        val size = state.students.filter { it.isChecked }.size
        val text = when(state.page) {
            1 -> "${size}명 선택"
            2 -> context.getString(R.string.label_next)
            3 -> context.getString(R.string.label_give)
            else -> context.getString(R.string.label_next)
        }

        Button(
            modifier = Modifier.padding(
                bottom = DodamDimen.ScreenSidePadding,
                start = DodamDimen.ScreenSidePadding,
                end = DodamDimen.ScreenSidePadding,
            ),
            onClick = {
                when (state.page) {
                    1 -> {
                        if (size == 0)
                            context.shortToast(context.getString(R.string.desc_minimum_point_members))
                        else
                            pointViewModel.updatePage(2)
                    }
                    2 -> pointViewModel.updatePage(3)
                    3 -> {}
                }
            },
            type = ButtonType.PrimaryVariant,
        ) {
            Label1(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
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
