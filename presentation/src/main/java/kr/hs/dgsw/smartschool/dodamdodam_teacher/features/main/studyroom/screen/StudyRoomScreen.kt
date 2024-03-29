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
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StudyRoomScreen(
    navController: NavController,
    viewModel: StudyRoomViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    viewModel.collectSideEffect {
        when (it) {

            is StudyRoomSideEffect.ShowException -> {
                if (it.exception.message == context.getString(R.string.text_session)) {
                    navController.navigate(NavGroup.Auth.LOGIN) {
                        popUpTo(NavGroup.Main.STUDYROOM) {
                            inclusive = true
                        }
                    }
                }
                context.shortToast(
                    it.exception.message ?: context.getString(R.string.content_unknown_exception)
                )
                Log.e("StudyRoomScreenError", it.exception.stackTraceToString())
            }
        }
    }

    val refreshState = rememberPullRefreshState(
        refreshing = state.refreshing,
        onRefresh = {
            viewModel.getStudyRoomRefresh()
        }
    )

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

        val wholeSize = state.students.size
        val firstClassCount = state.studyRooms.filter { it.timeTable.id == 1 || it.timeTable.id == 5 }.size
        val secondClassCount = state.studyRooms.filter { it.timeTable.id == 2 || it.timeTable.id == 6 }.size
        val thirdClassCount = state.studyRooms.filter { it.timeTable.id == 3 || it.timeTable.id == 7 }.size
        val fourthClassCount = state.studyRooms.filter { it.timeTable.id == 4 || it.timeTable.id == 8 }.size

        val pageList = if (LocalDate.now().dayOfWeek.value in 1..5) {
            listOf(
                StudyroomPage(
                    1,
                    context.getString(R.string.label_class_8),
                    "${firstClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)"
                ) {
                    navController.navigate(
                        NavGroup.Studyroom.STUDYROOM_APPLY
                            .replace(
                                oldValue = "{type}",
                                newValue = "1"
                            )
                    )
                },
                StudyroomPage(
                    2,
                    context.getString(R.string.label_class_9),
                    "${secondClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)"
                ) {
                    navController.navigate(
                        NavGroup.Studyroom.STUDYROOM_APPLY
                            .replace(
                                oldValue = "{type}",
                                newValue = "2"
                            )
                    )
                },
                StudyroomPage(
                    3,
                    context.getString(R.string.label_class_10),
                    "${thirdClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)"
                ) {
                    navController.navigate(
                        NavGroup.Studyroom.STUDYROOM_APPLY
                            .replace(
                                oldValue = "{type}",
                                newValue = "3"
                            )
                    )
                },
                StudyroomPage(
                    4,
                    context.getString(R.string.label_class_11),
                    "${fourthClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)"
                ) {
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
                StudyroomPage(1, context.getString(R.string.label_forenoon_1), "${firstClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)") {
                    navController.navigate(
                        NavGroup.Studyroom.STUDYROOM_APPLY
                            .replace(
                                oldValue = "{type}",
                                newValue = "1"
                            )
                    )
                },
                StudyroomPage(2, context.getString(R.string.label_forenoon_2), "${secondClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)") {
                    navController.navigate(
                        NavGroup.Studyroom.STUDYROOM_APPLY
                            .replace(
                                oldValue = "{type}",
                                newValue = "2"
                            )
                    )
                },
                StudyroomPage(3, context.getString(R.string.label_afternoon_1), "${thirdClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)") {
                    navController.navigate(
                        NavGroup.Studyroom.STUDYROOM_APPLY
                            .replace(
                                oldValue = "{type}",
                                newValue = "3"
                            )
                    )
                },
                StudyroomPage(4, context.getString(R.string.label_afternoon_2), "${fourthClassCount}명 (신청자) / ${wholeSize}명 (전체 인원)") {
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

        Box(
            Modifier
                .pullRefresh(refreshState)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
                contentPadding = PaddingValues(
                    top = DodamDimen.ScreenSidePadding,
                    bottom = DodamTeacherDimens.BottomNavHeight + DodamDimen.ScreenSidePadding
                )
            ) {
                items(pageList) { page ->
                    DodamContentCard(
                        title = page.title,
                        modifier = Modifier
                            .dodamClickable {
                                page.onClick()
                            },
                        hasLinkIcon = true,
                        content = {
                            Body2(text = page.content)
                        },
                        background = DodamTheme.color.Background
                    )
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

data class StudyroomPage(
    val idx: Int,
    val title: String,
    val content: String,
    val onClick: () -> Unit,
)
