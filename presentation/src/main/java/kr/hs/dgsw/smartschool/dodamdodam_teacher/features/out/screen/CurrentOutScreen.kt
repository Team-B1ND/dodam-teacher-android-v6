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
import kr.hs.dgsw.smartschool.domain.model.out.Out
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
                currentOutViewModel.getOutSleepingRemote()
            }
        }
    }

    val goingGradeList = state.outGoings.asSequence().map { it.student.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val goingRoomList = state.outGoings.asSequence().map { it.student.room }.distinct().sortedDescending().map { "${it}반" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val sleepingGradeList = state.outSleepings.asSequence().map { it.student.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val sleepingRoomList = state.outSleepings.asSequence().map { it.student.room }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val categoryRoomList = if (state.currentOutType == 0) {
        goingRoomList
    } else {
        sleepingRoomList
    }

    val categoryGradeList = if (state.currentOutType == 0) {
        goingGradeList
    } else {
        sleepingGradeList
    }

    val convertedRoom = categoryRoomList.map { grade ->
        when (grade) {
            "전체" -> 0
            else -> grade.substring(0, 1).toInt()
        }
    }

    val converterGrade = categoryGradeList.map { grade ->
        when (grade) {
            "전체" -> 0
            else -> grade.substring(0, 1).toInt()
        }
    }

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
                    description = "\n시작 날짜 : ${it.startOutDate} \n\n복귀 날짜 : ${it.endOutDate} \n\n사유 : ${it.reason}",
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
                            categoryList = categoryGradeList,
                            onSelectedItem = { idx ->
                                currentOutViewModel.updateGrade(converterGrade[idx])
                            }
                        )

                        SelectBar(
                            categoryList = categoryRoomList,
                            selectIdx = state.currentClassroom,
                            onSelectedItem = { idx ->

                                currentOutViewModel.updateClassroom(convertedRoom[idx])
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
                            val findStudent = state.members.find {
                                it.student?.id == outItem.student.id
                            }
                            DodamStudentItem(
                                members = state.members,
                                findMemberId = findStudent?.student?.id ?: 0,
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

private fun getFilteredOutList(state: CurrentOutState): List<Out> {
    val outList: List<Out> = if (state.currentOutType == 0)
        state.outGoings
    else
        state.outSleepings
    Log.d("TAG", "getFilteredOutList: ${state.currentOutType}")

    return if (state.currentGrade == 0 && state.currentClassroom == 0) {
        outList
    } else if (state.currentGrade == 0) {
        outList.filter {
            it.student.room == state.currentClassroom
        }
    } else if (state.currentClassroom == 0) {
        outList.filter {
            it.student.grade == state.currentGrade
        }
    } else {
        outList.filter {
            (it.student.grade == state.currentGrade) && (it.student.room == state.currentClassroom)
//            (it.getOutItemGradeInfo(state) == state.currentGrade) && (it.getOutItemRoomInfo(state) == state.currentClassroom)
        }
    }
}

private fun Out.getOutItemRoomInfo(state: CurrentOutState): Int {
    val student = state.members.find {
        this.id == it.student?.id
    } ?: return 0

    val classroom = state.classrooms.find {
        student.student?.id == it.id
    } ?: return 0

    return classroom.room
}

private fun Out.getOutItemGradeInfo(state: CurrentOutState): Int {
    val student = state.members.find {
        this.id == it.student?.id
    } ?: return 0

    val classroom = state.classrooms.find {
        student.student?.id == it.id
    } ?: return 0

    return classroom.grade
}

private fun Out.getOutItemNameInfo(state: CurrentOutState): String {
    val student = state.members.find {
        this.student.id == it.student?.id
    } ?: return ""

    val member = state.members.find {
        student.student?.id == it.student?.id
    } ?: return ""

    return member.student?.name ?: ""
}

private fun getOutType(context: Context, outType: Int): String = when (outType) {
    0 -> context.getString(R.string.label_outgoing)
    1 -> context.getString(R.string.label_outsleeping)
    else -> ""
}

fun getNextDay(): String {
    val currentDate = LocalDate.now()
    val nextDay = currentDate.plusDays(0)

    return nextDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}
