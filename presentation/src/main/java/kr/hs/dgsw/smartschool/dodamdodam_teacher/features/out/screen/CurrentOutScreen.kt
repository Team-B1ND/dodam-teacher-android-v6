package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.ButtonType
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMediumRoundedButton
import kr.hs.dgsw.smartschool.components.component.organization.prompt.DodamPrompt
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamStudentItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.mvi.CurrentOutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.mvi.CurrentOutState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.vm.CurrentOutViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.toSimpleYearDateTime
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrentOutScreen(
    navController: NavController,
    currentOutViewModel: CurrentOutViewModel = hiltViewModel(),
) {

    val state = currentOutViewModel.collectAsState().value
    val context = LocalContext.current

    currentOutViewModel.collectSideEffect {
        when (it) {
            is CurrentOutSideEffect.ShowException -> {
                context.shortToast(
                    it.exception.message ?: context.getString(R.string.content_unknown_exception)
                )
                Log.e("OutErrorLog", it.exception.stackTraceToString())
            }
            is CurrentOutSideEffect.SuccessControl -> {
                context.shortToast(message = it.message)
                currentOutViewModel.getOutsRemote()
            }
        }
    }

    val gradeList = state.classrooms.asSequence().map { it.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val roomList = state.classrooms.asSequence().map { it.room }.distinct().sortedDescending().map { "${it}반" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val outTypeList = listOf(stringResource(id = R.string.label_outgoing), stringResource(id = R.string.label_outsleeping))

    val refreshState = rememberPullRefreshState(
        refreshing = state.refreshing,
        onRefresh = {
            currentOutViewModel.getOutsRefresh()
        }
    )

    if (state.getOutsLoading)
        LoadInFullScreen()
    else {
        if (state.showPrompt) {
            state.currentSelectedOutItem?.let {
                DodamPrompt(
                    title = "${it.getOutItemNameInfo(state)}님의 ${getOutType(context, state.currentOutType)} 정보",
                    secondaryButton = {},
                    primaryButton = {
                        DodamMediumRoundedButton(
                            text = stringResource(id = R.string.label_approve_cancel),
                            type = ButtonType.Danger,
                        ) {
                            if (state.currentOutType == 0)
                                currentOutViewModel.cancelAllowOutgoing(id = it.id)
                            else
                                currentOutViewModel.cancelAllowOutsleeping(id = it.id)

                            currentOutViewModel.updateShowPrompt(false)
                        }
                    },
                    description = "\n시작 날짜 : ${it.startOutDate.toSimpleYearDateTime()} \n\n복귀 날짜 : ${it.endOutDate.toSimpleYearDateTime()} \n\n사유 : ${it.reason}",
                    onDismiss = {
                        currentOutViewModel.updateShowPrompt(false)
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DodamColor.White)
        ) {

            DodamAppBar(
                title = stringResource(id = R.string.title_out_current),
                onStartIconClick = {
                    navController.popBackStack()
                }
            )

            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

            Box(
                Modifier
                    .pullRefresh(refreshState)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = DodamDimen.ScreenSidePadding),
                        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
                    ) {
                        SelectBar(
                            selectIdx = state.currentGrade,
                            categoryList = gradeList,
                            onSelectedItem = { idx ->
                                currentOutViewModel.updateGrade(idx)
                            }
                        )

                        SelectBar(
                            categoryList = roomList,
                            selectIdx = state.currentClassroom,
                            onSelectedItem = { idx ->
                                currentOutViewModel.updateClassroom(idx)
                            }
                        )

                        SelectBar(
                            categoryList = outTypeList,
                            selectIdx = state.currentOutType,
                            onSelectedItem = { idx ->
                                currentOutViewModel.updateOutType(idx)
                            }
                        )
                    }

                    val outList = getFilteredOutList(state)

                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = DodamDimen.ScreenSidePadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
                        contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = 56.dp + DodamDimen.ScreenSidePadding)
                    ) {
                        items(outList) { outItem ->
                            val findStudent = state.students.find {
                                it.id == outItem.studentId
                            }
                            DodamStudentItem(
                                members = state.members,
                                findMemberId = findStudent?.member?.id ?: "",
                                modifier = Modifier.dodamClickable(rippleEnable = false) {
                                    currentOutViewModel.updateOutItem(outItem)
                                    currentOutViewModel.updateShowPrompt(showPrompt = true)
                                }
                            )
                        }
                    }
                }

                PullRefreshIndicator(
                    refreshing = state.refreshing,
                    state = refreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    contentColor = DodamTheme.color.MainColor400
                )
            }
        }
    }
}

private fun getFilteredOutList(state: CurrentOutState): List<OutItem> {
    val outList: List<OutItem> = if (state.currentOutType == 0)
        state.outGoings
    else
        state.outSleepings

    return if (state.currentGrade == 0 && state.currentClassroom == 0) {
        outList
    } else if (state.currentGrade == 0) {
        outList.filter {
            it.getOutItemRoomInfo(state) == state.currentClassroom
        }
    } else if (state.currentClassroom == 0) {
        outList.filter {
            it.getOutItemGradeInfo(state) == state.currentGrade
        }
    } else {
        outList.filter {
            (it.getOutItemGradeInfo(state) == state.currentGrade) && (it.getOutItemRoomInfo(state) == state.currentClassroom)
        }
    }
}

private fun OutItem.getOutItemRoomInfo(state: CurrentOutState): Int {
    val student = state.students.find {
        studentId == it.id
    } ?: return 0

    val classroom = state.classrooms.find {
        student.classroom.id == it.id
    } ?: return 0

    return classroom.room
}

private fun OutItem.getOutItemGradeInfo(state: CurrentOutState): Int {
    val student = state.students.find {
        studentId == it.id
    } ?: return 0

    val classroom = state.classrooms.find {
        student.classroom.id == it.id
    } ?: return 0

    return classroom.grade
}

private fun OutItem.getOutItemNameInfo(state: CurrentOutState): String {
    val student = state.students.find {
        studentId == it.id
    } ?: return ""

    val member = state.members.find {
        student.member.id == it.id
    } ?: return ""

    return member.name
}

private fun getOutType(context: Context, outType: Int): String = when(outType) {
    0 -> context.getString(R.string.label_outgoing)
    1 -> context.getString(R.string.label_outsleeping)
    else -> ""
}
