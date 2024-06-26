package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.common.DodamTeacherDimens
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamStudentItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.mvi.NightStudySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.mvi.NightStudyState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.vm.NightStudyViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.night_study.NightStudy
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NightStudyScreen(
    navController: NavController,
    nightStudyViewModel: NightStudyViewModel = hiltViewModel(),
) {
    val state = nightStudyViewModel.collectAsState().value
    val context = LocalContext.current

    val promptScrollState = rememberScrollState()

    nightStudyViewModel.collectSideEffect {
        when (it) {
            is NightStudySideEffect.ShowException -> {
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
                Log.e("NightStudyErrorLog", it.exception.stackTraceToString())
            }
            is NightStudySideEffect.SuccessControl -> {
                context.shortToast(message = it.message)
                nightStudyViewModel.getNightStudy()
            }
        }
    }

    val gradeList = listOf("전체", "1학년", "2학년", "3학년")
//        state.nightStudies.asSequence().map { it.student.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
//        stringResource(id = R.string.label_all)
//    ).toList().reversed()

    val roomList = listOf("전체", "1반", "2반", "3반", "4반")
//        state.nightStudies.asSequence().map { it.student.room }.distinct().sortedDescending().map { "${it}반" }.plus(
//        stringResource(id = R.string.label_all)
//    ).toList().reversed()

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

    val refreshState = rememberPullRefreshState(
        refreshing = state.refreshing,
        onRefresh = {
            nightStudyViewModel.getOutsRefresh()
        }
    )

    if (state.isLoading)
        LoadInFullScreen()
    else {
        if (state.showPrompt) {
            state.currentSelectedNightStudy?.let {
                DodamPrompt(
                    modifier = Modifier.verticalScroll(promptScrollState),
                    title = "${it.student.name}님의 심자 정보",
                    primaryButton = {
                        DodamMediumRoundedButton(
                            text = stringResource(id = R.string.label_approve),
                            type = ButtonType.PrimaryVariant,
                        ) {
                            nightStudyViewModel.allowNightStudy(it.id)
                            nightStudyViewModel.updateShowPrompt(false)
                        }
                    },
                    secondaryButton = {
                        DodamMediumRoundedButton(
                            text = stringResource(id = R.string.label_deny),
                            type = ButtonType.Danger,
                        ) {
                            nightStudyViewModel.denyNightStudy(it.id)
                            nightStudyViewModel.updateShowPrompt(false)
                        }
                    },
                    description = "\n시작 날짜 : ${it.startAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}\n\n" +
                        "종료 날짜 : ${it.endAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}\n\n" +
                        " 심자 장소 : ${it.place}\n\n" +
                        "학습 계획 : ${it.content}\n\n" +
                        if (it.isPhone) "휴대폰 사용 이유 : ${it.reason}" else "휴대폰 사용 : X",
                    onDismiss = {
                        nightStudyViewModel.updateShowPrompt(false)
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
                title = stringResource(id = R.string.title_night_study_approve),
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
                                nightStudyViewModel.updateGrade(converterGrade[idx])
                            }
                        )

                        SelectBar(
                            categoryList = roomList,
                            selectIdx = state.currentClassroom,
                            onSelectedItem = { idx ->
                                nightStudyViewModel.updateClassroom(convertedRoom[idx])
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
                            val findStudent = state.members.find {

                                it.student?.number == nightStudy.student.number &&
                                    it.student?.name == nightStudy.student.name
                            }
                            Log.d("TAG", "nightStudies: $nightStudies")
                            Log.d("TAG", "member: $findStudent")

                            DodamStudentItem(
                                members = state.members,
                                findMemberId = findStudent?.student?.id ?: 0,
                                modifier = Modifier.dodamClickable(rippleEnable = false) {
                                    nightStudyViewModel.updateNightStudy(nightStudy)
                                    nightStudyViewModel.updateShowPrompt(showPrompt = true)
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

private fun getFilteredNightStudies(state: NightStudyState): List<NightStudy> {

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
