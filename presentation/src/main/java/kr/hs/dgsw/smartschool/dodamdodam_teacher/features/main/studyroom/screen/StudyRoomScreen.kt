package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

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
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamContentCard
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Title1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.common.DodamTeacherDimens
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamStudyRoomItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StudyRoomScreen(
    navController: NavController,
    viewModel: StudyRoomViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    viewModel.collectSideEffect {
        when (it) {
            is StudyRoomSideEffect.ToastError -> {
                context.shortToast(
                    it.exception.message ?: context.getString(R.string.content_unknown_exception)
                )
                Log.e("StudyRoomScreenError", it.exception.stackTraceToString())
            }
        }
    }
    if (state.loading)
        LoadInFullScreen()
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DodamColor.White)
        ) {
            Spacer(modifier = Modifier.height(DodamTeacherDimens.DefaultMainScreenTitleSpace))

            Title1(
                text = stringResource(id = R.string.title_studyroom_check),
                modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding),
            )

            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

            val pageList = if (state.isWeekDay) {
                listOf(
                    StudroomPage(
                        1,
                        context.getString(R.string.label_class_8),
                        "${state.firstClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)"
                    ) {
                        viewModel.getSheetByTime(1)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "1"
                                )
                        )
                    },
                    StudroomPage(
                        2,
                        context.getString(R.string.label_class_9),
                        "${state.secondClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)"
                    ) {
                        viewModel.getSheetByTime(2)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "2"
                                )
                        )
                    },
                    StudroomPage(
                        3,
                        context.getString(R.string.label_class_10),
                        "${state.thirdClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)"
                    ) {
                        viewModel.getSheetByTime(3)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "3"
                                )
                        )
                    },
                    StudroomPage(
                        4,
                        context.getString(R.string.label_class_11),
                        "${state.fourthClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)"
                    ) {
                        viewModel.getSheetByTime(4)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "4"
                                )
                        )
                    },
                )
            } else {
                listOf(
                    StudroomPage(1, context.getString(R.string.label_forenoon_1), "${state.firstClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)") {
                        viewModel.getSheetByTime(1)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "1"
                                )
                        )
                    },
                    StudroomPage(2, context.getString(R.string.label_forenoon_2), "${state.secondClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)") {
                        viewModel.getSheetByTime(2)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "2"
                                )
                        )
                    },
                    StudroomPage(3, context.getString(R.string.label_afternoon_1), "${state.thirdClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)") {
                        viewModel.getSheetByTime(3)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "3"
                                )
                        )
                    },
                    StudroomPage(4, context.getString(R.string.label_afternoon_2), "${state.fourthClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)") {
                        viewModel.getSheetByTime(4)
                        navController.navigate(
                            NavGroup.Studyroom.STUDYROOM_APPLY
                                .replace(
                                    oldValue = "{type}",
                                    newValue = "4"
                                )
                    )
                }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
                contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding, bottom = DodamTeacherDimens.BottomNavHeight + DodamDimen.ScreenSidePadding)
            ) {
                items(pageList) { page ->
                    DodamContentCard(
                        title = page.title,
                        modifier = Modifier
                            .dodamClickable {
                                page.onClick()
                                viewModel.getSheetByTime(page.idx)
                            },
                        hasLinkIcon = true,
                        content = {
                            Body2(text = page.content)
                        },
                        background = DodamTheme.color.Background
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ApplyList(navController: NavController, tabType: Int, state: StudyRoomState, viewModel: StudyRoomViewModel) {
    val refreshState = rememberPullRefreshState(
        refreshing = state.refreshing,
        onRefresh = {
            viewModel.getSheetByTime(state.classType)
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        when (tabType) {
            0 -> LazyColumn(
                modifier = Modifier
                    .padding(DodamDimen.ScreenSidePadding)
            ) {
                items(state.studyRoomList) { item ->
                    DodamStudyRoomItem(
                        member = item.student.member,
                        place = item.place.name,
                        status = item.status,
                        checkAction = {
                            viewModel.checkStudyRoom(
                                item.id,
                                item.status == StudyRoomStatus.CHECKED
                            )
                        },
                        ctrlAction = {
                            viewModel.addStudentOnState(item.student)
                            navController.navigate("place")
                        },
                        classroom = "${item.student.classroom.grade}학년 ${item.student.classroom.room}반"
                    )
                }
            }
            1 -> LazyColumn(
                modifier = Modifier
                    .padding(DodamDimen.ScreenSidePadding)
            ) {
                items(state.otherStudents) { item ->
                    DodamStudyRoomItem(
                        member = item.member,
                        place = null,
                        status = StudyRoomStatus.PENDING,
                        ctrlAction = {
                            viewModel.addStudentOnState(item)
                            navController.navigate("place")
                        },
                        checkAction = {
                            viewModel.addStudentOnState(item)
                            navController.navigate("place")
                        },
                        classroom = "${item.classroom.grade}학년 ${item.classroom.room}반"
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

data class StudroomPage(
    val idx: Int,
    val title: String,
    val content: String,
    val onClick: () -> Unit,
)

