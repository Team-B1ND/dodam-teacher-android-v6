package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.time.LocalDate
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi.ApplySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.vm.ApplyViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ApplyScreen(
    navController: NavController,
    viewModel: ApplyViewModel = hiltViewModel(),
    type: Int,
) {

    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    viewModel.collectSideEffect {
        when (it) {
            is ApplySideEffect.ShowException -> {
                context.shortToast(
                    it.exception.message ?: context.getString(R.string.content_unknown_exception)
                )
                Log.e("ApplyErrorLog", it.exception.stackTraceToString())
            }
        }
    }

    val gradeList = state.classrooms.asSequence().map { it.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val roomList = state.classrooms.asSequence().map { it.room }.distinct().sortedDescending().map { "${it}반" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val applyTypeList = listOf(stringResource(id = R.string.label_apply), stringResource(id = R.string.label_unapply))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamTheme.color.White),
    ) {
        DodamAppBar(
            onStartIconClick = {
                //viewModel.getAllStudyRooms()
                navController.popBackStack()
            },
            title =
            if (LocalDate.now().dayOfWeek.value in 1..5) {
                when (type) {
                    1 -> stringResource(id = R.string.label_forenoon_1)
                    2 -> stringResource(id = R.string.label_forenoon_2)
                    3 -> stringResource(id = R.string.label_afternoon_1)
                    4 -> stringResource(id = R.string.label_afternoon_2)
                    else -> stringResource(id = R.string.label_forenoon_1)
                }
            } else {
                when (type) {
                    1 -> stringResource(id = R.string.label_class_8)
                    2 -> stringResource(id = R.string.label_class_9)
                    3 -> stringResource(id = R.string.label_class_10)
                    4 -> stringResource(id = R.string.label_class_11)
                    else -> stringResource(id = R.string.label_class_8)
                }
            }
        )
        Column(
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding),
            verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
        ) {

            SelectBar(
                selectIdx = state.currentGrade,
                categoryList = gradeList,
                onSelectedItem = { idx ->
                    viewModel.updateGrade(idx)
                }
            )

            SelectBar(
                categoryList = roomList,
                selectIdx = state.currentClassroom,
                onSelectedItem = { idx ->
                    viewModel.updateClassroom(idx)
                }
            )

            SelectBar(
                categoryList = applyTypeList,
                selectIdx = state.currentApplyType,
                onSelectedItem = { idx ->
                    viewModel.updateOutType(idx)
                }
            )
        }

    }
}
