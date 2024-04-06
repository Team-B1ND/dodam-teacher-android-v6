package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.screen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.ButtonType
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMediumRoundedButton
import kr.hs.dgsw.smartschool.components.component.organization.prompt.DodamPrompt
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Title1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.common.DodamTeacherDimens
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamStudentItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.vm.OutViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.toSimpleYearDateTime
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutScreen(
    navController: NavController,
    outViewModel: OutViewModel = hiltViewModel(),
) {

    val state = outViewModel.collectAsState().value
    val context = LocalContext.current

    outViewModel.collectSideEffect {
        when (it) {
            is OutSideEffect.ShowException -> {
                if (it.exception.message == context.getString(R.string.text_session)) {
                    navController.navigate(NavGroup.Auth.LOGIN) {
                        popUpTo(NavGroup.Main.OUT) {
                            inclusive = true
                        }
                    }
                }
                context.shortToast(
                    it.exception.message ?: context.getString(R.string.content_unknown_exception)
                )
                Log.e("OutErrorLog", it.exception.stackTraceToString())
                // updateMainNavTabSelectedTab(0)
            }
            is OutSideEffect.SuccessControl -> {
                context.shortToast(message = it.message)
                outViewModel.getOutsRemote()
            }
        }
    }

    val gradeList = state.outGoings.asSequence().map { it.student.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val roomList = state.outGoings.asSequence().map { it.student.room }.distinct().sortedDescending().map { "${it}반" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()


    val convertedRoom = roomList.map { grade ->
        when (grade) {
            "전체" -> 0
            else -> grade.substring(0, 1).toInt()
        }
    }

    val converterGrade = gradeList.map { grade ->
        when (grade) {
            "전체" -> 0
            else -> grade.substring(0, 1).toInt()
        }
    }

    val outTypeList = listOf(stringResource(id = R.string.label_outgoing), stringResource(id = R.string.label_outsleeping))

    val refreshState = rememberPullRefreshState(
        refreshing = state.refreshing,
        onRefresh = {
            outViewModel.getOutsRefresh()
        }
    )

    if (state.getOutsLoading)
        LoadInFullScreen()
    else {
        if (state.showPrompt) {
            state.currentSelectedOutItem?.let {
                DodamPrompt(
                    title = "${it.getOutItemNameInfo(state)}님의 ${getOutType(context, state.currentOutType)} 정보",
                    primaryButton = {
                        DodamMediumRoundedButton(
                            text = stringResource(id = R.string.label_approve),
                            type = ButtonType.PrimaryVariant,
                        ) {
                            if (state.currentOutType == 0)
                                outViewModel.allowOutgoing(id = it.id)
                            else
                                outViewModel.allowOutsleeping(id = it.id)

                            outViewModel.updateShowPrompt(false)
                        }
                    },
                    secondaryButton = {
                        DodamMediumRoundedButton(
                            text = stringResource(id = R.string.label_deny),
                            type = ButtonType.Danger,
                        ) {
                            if (state.currentOutType == 0)
                                outViewModel.denyOutgoing(id = it.id)
                            else
                                outViewModel.denyOutsleeping(id = it.id)

                            outViewModel.updateShowPrompt(false)
                        }
                    },
                    description = "\n시작 날짜 : ${it.startOutDate} \n\n복귀 날짜 : ${it.endOutDate} \n\n사유 : ${it.reason}",
                    onDismiss = {
                        outViewModel.updateShowPrompt(false)
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DodamColor.White)
        ) {

            Spacer(modifier = Modifier.height(DodamTeacherDimens.DefaultMainScreenTitleSpace))

            Title1(
                text = stringResource(id = R.string.title_out_approve),
                modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding),
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
                                outViewModel.updateGrade(converterGrade[idx])
                            }
                        )

                        SelectBar(
                            selectIdx = state.currentClassroom,
                            categoryList = roomList,
                            onSelectedItem = { idx ->
                                outViewModel.updateClassroom(convertedRoom[idx])
                            }
                        )

                        SelectBar(
                            categoryList = outTypeList,
                            selectIdx = state.currentOutType,
                            onSelectedItem = { idx ->
                                outViewModel.updateOutType(idx)
                            }
                        )
                    }

                    val outList = getFilteredOutList(state)

                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = DodamDimen.ScreenSidePadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
                        contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = DodamTeacherDimens.BottomNavHeight + DodamDimen.ScreenSidePadding)
                    ) {
                        items(outList) { outItem ->
                            val findStudent = state.members.find {
                                it.student?.id == outItem.student.id
                            }
                            DodamStudentItem(
                                members = state.members,
                                findMemberId = findStudent?.student?.id ?: 0,
                                modifier = Modifier.dodamClickable(rippleEnable = false) {
                                    outViewModel.updateOutItem(outItem)
                                    outViewModel.updateShowPrompt(showPrompt = true)
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

private fun getFilteredOutList(state: OutState): List<Out> {
    val outList: List<Out> = if (state.currentOutType == 0)
        state.outGoings
    else
        state.outSleepings

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

        }
    }
}

private fun Out.getOutItemRoomInfo(state: OutState): Int {
    val student = state.members.find {
        this.id == it.student?.id
    } ?: return 0

    val classroom = state.classrooms.find {
        student.student?.id == it.id
    } ?: return 0

    return classroom.room
}

private fun Out.getOutItemGradeInfo(state: OutState): Int? {
    val student = state.members.find {
        this.id == it.student?.id
    } ?: return 0

    val classroom = state.members.find {
        student.id == it.id
    } ?: return 0

    return classroom.student?.grade
}

private fun Out.getOutItemNameInfo(state: OutState): String {
    val student = state.members.find {
        this.id == it.student?.id
    } ?: return ""

    val member = state.members.find {
        student.id == it.id
    } ?: return ""

    return member.name
}

private fun getOutType(context: Context, outType: Int): String = when (outType) {
    0 -> context.getString(R.string.label_outgoing)
    1 -> context.getString(R.string.label_outsleeping)
    else -> ""
}
