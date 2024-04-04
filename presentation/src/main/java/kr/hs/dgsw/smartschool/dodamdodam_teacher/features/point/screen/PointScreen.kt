package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.Button
import kr.hs.dgsw.smartschool.components.component.basic.button.ButtonType
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page.FirstPage
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page.SecondPage
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page.ThirdPage
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointType
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
        when (it) {
            is PointSideEffect.ShowException -> {
                if (it.exception.message == context.getString(R.string.text_session)) {
                    navController.navigate(NavGroup.Auth.LOGIN) {
                        popUpTo(NavGroup.Feature.POINT) {
                            inclusive = true
                        }
                    }
                }
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("PointErrorLog", it.exception.stackTraceToString())
                navController.popBackStack()
            }
            is PointSideEffect.SuccessGivePoint -> {
                context.shortToast(context.getString(R.string.desc_give_point_success))
                navController.popBackStack()
            }
        }
    }

    if (state.givePointLoading)
        LoadInFullScreen()
    else
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
                when (state.page) {
                    1 -> FirstPage(state = state, viewModel = pointViewModel)
                    2 -> SecondPage(state = state, viewModel = pointViewModel)
                    3 -> ThirdPage(state = state, viewModel = pointViewModel)
                }
            }

            val size = state.pointStudents.filter { it.isChecked }.size
            val text = when (state.page) {
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
                        3 -> {
                            if (state.currentSelectedReason != null)
                                pointViewModel.givePoint(
                                    id = state.currentSelectedReason.id,
                                    place = when (state.currentPlace) {
                                        0 -> PointPlace.DORMITORY
                                        1 -> PointPlace.SCHOOL
                                        else -> PointPlace.DORMITORY
                                    },
                                    reason = state.currentSelectedReason.reason,
                                    score = state.currentSelectedReason.score,
                                    studentId = state.pointStudents.filter { it.isChecked }.map { it.studentId },
                                    type = when (state.currentPointType) {
                                        0 -> PointType.BONUS
                                        1 -> PointType.MINUS
                                        else -> PointType.MINUS
                                    },
                                )
                            else
                                context.shortToast(context.getString(R.string.desc_point_reason_empty))
                        }
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
