package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.screen

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
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.mvi.CurrentNightStudySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.mvi.CurrentNightStudyState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.vm.CurrentNightStudyViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.evening_study.NightStudy
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrentNightStudyScreen(
    navController: NavController,
    currentNightStudyViewModel: CurrentNightStudyViewModel = hiltViewModel(),
) {
    val state = currentNightStudyViewModel.collectAsState().value
    val context = LocalContext.current

    currentNightStudyViewModel.collectSideEffect {
        when (it) {
            is CurrentNightStudySideEffect.ShowException -> {
                if (it.exception.message == context.getString(R.string.text_session)) {
                    navController.navigate(NavGroup.Auth.LOGIN) {
                        popUpTo(NavGroup.Feature.NIGHT_STUDY) {
                            inclusive = true
                        }
                    }
                }
                context.shortToast(
                    it.exception.message ?: context.getString(R.string.content_unknown_exception)
                )
                Log.e("CurrentNightStudyErrorLog", it.exception.stackTraceToString())
            }
            is CurrentNightStudySideEffect.SuccessControl -> {
                context.shortToast(message = it.message)
                currentNightStudyViewModel.getCurrentNightStudy()
            }
        }
    }

    val gradeList = state.classrooms.asSequence().map { it.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val roomList = state.classrooms.asSequence().map { it.room }.distinct().sortedDescending().map { "${it}반" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val refreshState = rememberPullRefreshState(
        refreshing = state.refreshing,
        onRefresh = {
            currentNightStudyViewModel.getOutsRefresh()
        }
    )

    if (state.isLoading)
        LoadInFullScreen()
    else {
        if (state.showPrompt) {
            state.currentSelectedNightStudy?.let {
                DodamPrompt(
                    title = "${it.student.name}님의 심자 정보",
                    secondaryButton = {},
                    primaryButton = {
                        DodamMediumRoundedButton(
                            text = stringResource(id = R.string.label_approve_cancel),
                            type = ButtonType.Danger,
                        ) {
                            currentNightStudyViewModel.denyNightStudy(it.id)
                            currentNightStudyViewModel.updateShowPrompt(false)
                        }
                    },
                    description = """
                        
                        시작 날짜 : ${it.startAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}
                        
                        종료 날짜 : ${it.endAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}
                        
                        심자 장소 : ${it.place}
                        
                        학습 계획 : ${it.content}
                        
                        ${it.reason?.let { reason ->
                        "휴대폰 사용 이유 : $reason"
                    }}
                    """.trimIndent(),
                    onDismiss = {
                        currentNightStudyViewModel.updateShowPrompt(false)
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
                text = stringResource(id = R.string.title_night_study_current),
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
                                currentNightStudyViewModel.updateGrade(idx)
                            }
                        )

                        SelectBar(
                            categoryList = roomList,
                            selectIdx = state.currentClassroom,
                            onSelectedItem = { idx ->
                                currentNightStudyViewModel.updateClassroom(idx)
                            }
                        )
                    }

                    val nightStudies = getFilteredNightStudies(state)

                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = DodamDimen.ScreenSidePadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
                        contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = DodamTeacherDimens.BottomNavHeight + DodamDimen.ScreenSidePadding)
                    ) {
                        items(nightStudies) { nightStudy ->
                            val findStudent = state.students.find {
                                it.number == nightStudy.student.number &&
                                    it.member.name == nightStudy.student.name
                            }
                            DodamStudentItem(
                                members = state.members,
                                findMemberId = findStudent?.member?.id ?: "",
                                modifier = Modifier.dodamClickable(rippleEnable = false) {
                                    currentNightStudyViewModel.updateNightStudy(nightStudy)
                                    currentNightStudyViewModel.updateShowPrompt(showPrompt = true)
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

private fun getFilteredNightStudies(state: CurrentNightStudyState): List<NightStudy> {

    return if (state.currentGrade == 0 && state.currentClassroom == 0) {
        state.nightStudies
    } else if (state.currentGrade == 0) {
        state.nightStudies.filter {
            it.student.room == state.currentClassroom
        }
    } else if (state.currentClassroom == 0) {
        state.nightStudies.filter {
            it.student.grade == state.currentGrade
        }
    } else {
        state.nightStudies.filter {
            (it.student.grade == state.currentGrade) && (it.student.room == state.currentClassroom)
        }
    }
}
