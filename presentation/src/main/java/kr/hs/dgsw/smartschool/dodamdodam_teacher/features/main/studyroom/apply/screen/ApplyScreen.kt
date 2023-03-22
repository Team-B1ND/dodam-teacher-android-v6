package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.time.LocalDate
import kr.hs.dgsw.smartschool.components.component.basic.button.ButtonType
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMediumRoundedButton
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.component.organization.prompt.DodamPrompt
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.common.DodamTeacherDimens
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamConfirmedStudentItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi.ApplySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi.ApplyState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.vm.ApplyViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
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
    
    if (state.currentSelectedItem != null)
        DodamPrompt(
            title = "${state.currentSelectedItem.member.name}님의 자습실 정보",
            description = if (state.currentSelectedItem.studyRoom == null)
                stringResource(id = R.string.desc_not_apply_studyroom)
            else
                "${getStudyRoomName(context, type)} ${state.currentSelectedItem.studyRoom.place.name} 신청",
            primaryButton = {
                if (state.currentSelectedItem.studyRoom != null) {
                    DodamMediumRoundedButton(
                        text = if (state.currentSelectedItem.studyRoom.status == StudyRoomStatus.PENDING)
                            stringResource(id = R.string.label_check)
                        else
                            stringResource(id = R.string.label_cancel_check),
                        type = if (state.currentSelectedItem.studyRoom.status == StudyRoomStatus.PENDING)
                            ButtonType.PrimaryVariant
                        else
                            ButtonType.Danger
                    ) {
                        if (state.currentSelectedItem.studyRoom.status == StudyRoomStatus.PENDING)
                            viewModel.checkStudyRoom(state.currentSelectedItem.studyRoom.id)
                        else
                            viewModel.unCheckStudyRoom(state.currentSelectedItem.studyRoom.id)
                        viewModel.updateSelectedItem(null)
                    }
                }
            },
            secondaryButton = {
                DodamMediumRoundedButton(
                    text = stringResource(id = R.string.label_modify),
                    type = ButtonType.SecondaryVariant,
                ) {
                    viewModel.updateSelectedItem(null)
                    navController.navigate(NavGroup.Studyroom.STUDYROOM_CTRL.replace("{studentId}", state.currentSelectedItem.student.id.toString()))
                }
            },
            onDismiss = {
                viewModel.updateSelectedItem(null)
            }
        )

    if (state.loading)
        LoadInFullScreen()
    else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DodamTheme.color.White),
        ) {
            DodamAppBar(
                onStartIconClick = {
                    navController.popBackStack()
                },
                title = getStudyRoomName(context, type)
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

            val filteredApplyItemList = getFilteredStudyRoomList(type, state)

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
                contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = DodamDimen.ScreenSidePadding)
            ) {
                items(filteredApplyItemList) { applyItem ->
                    DodamConfirmedStudentItem(
                        member = applyItem.member,
                        modifier = Modifier
                            .dodamClickable(rippleEnable = false) {
                                viewModel.updateSelectedItem(applyItem)
                            },
                        backgroundColor = if (applyItem.studyRoom?.status == StudyRoomStatus.CHECKED)
                            DodamTheme.color.MainColor400
                        else
                            DodamTheme.color.Background,
                        textColor =  if (applyItem.studyRoom?.status == StudyRoomStatus.CHECKED)
                            DodamTheme.color.White
                        else
                            DodamTheme.color.Black,
                    )
                }
            }
        }
}

private fun getFilteredStudyRoomList(type: Int, state: ApplyState): List<ApplyState.ApplyItem> {
    val applyList = if (state.currentApplyType == 0) {
        state.applyItemList.filter {
            type == it.studyRoom?.timeTable?.id?.let { timeId -> if (timeId > 4) timeId - 4 else timeId }
        }
    } else {
        val typeList = state.applyItemList.filter {
            type == it.studyRoom?.timeTable?.id?.let { timeId -> if (timeId > 4) timeId - 4 else timeId }
        }
        state.students.filter { !typeList.map { applyItem -> applyItem.student.id }.contains(it.id) }.map { student ->
            val member = state.members.find { student.member.id == it.id }
            val classroom = state.classrooms.find { student.classroom.id == it.id }

            ApplyState.ApplyItem(
                student = student,
                member = member ?: Member(),
                classroom = classroom ?: Classroom(-1),
            )
        }
    }

    return if (state.currentGrade == 0 && state.currentClassroom == 0) {
        applyList
    } else if (state.currentGrade == 0) {
        applyList.filter {
            it.classroom.room == state.currentClassroom
        }
    } else if (state.currentClassroom == 0) {
        applyList.filter {
            it.classroom.grade == state.currentGrade
        }
    } else {
        applyList.filter {
            (it.classroom.grade == state.currentGrade) && (it.classroom.room == state.currentClassroom)
        }
    }
}

private fun getStudyRoomName(context: Context, type: Int): String {
    return if (LocalDate.now().dayOfWeek.value in 6..7) {
        when (type) {
            1 -> context.getString(R.string.label_forenoon_1)
            2 -> context.getString(R.string.label_forenoon_2)
            3 -> context.getString(R.string.label_afternoon_1)
            4 -> context.getString(R.string.label_afternoon_2)
            else -> context.getString(R.string.label_forenoon_1)
        }
    } else {
        when (type) {
            1 -> context.getString(R.string.label_class_8)
            2 -> context.getString(R.string.label_class_9)
            3 -> context.getString(R.string.label_class_10)
            4 -> context.getString(R.string.label_class_11)
            else -> context.getString(R.string.label_class_8)
        }
    }
}
