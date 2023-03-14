package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamItemCard
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTab
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTabs
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamStudyRoomItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTableType
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StudyRoomScreen(
    navController: NavController,
    studyRoomViewModel: StudyRoomViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val studyRoomState = studyRoomViewModel.container.stateFlow.collectAsState().value

    val tabNavController = rememberNavController()
    val tabSelected = remember {
        mutableStateOf(1)
    }

    studyRoomViewModel.collectSideEffect {
        when (it) {
            is StudyRoomSideEffect.ToastError -> {
                context.shortToast(
                    it.exception.message
                        ?: context.getString(kr.hs.dgsw.smartschool.dodamdodam_teacher.R.string.content_unknown_exception)
                )
                Log.e("StudyRoomScreenError", it.exception.stackTraceToString())
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DodamColor.FeatureColor.MyInfoColor)
    ) {

        NavHost(
            navController = tabNavController,
            startDestination = "class_1"
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable("study_room") {
                studyRoomViewModel.getStudyRoomSheet()
                StudyRoomMain(studyRoomViewModel, navController, studyRoomState)
            }
            composable("class_1") {
                studyRoomViewModel.getSheetByTime(1)
                FirstClass(studyRoomViewModel, navController, studyRoomState)
            }
            composable("class_2") {
            }
            composable("class_3") {
            }
            composable("class_4") {
            }
        }
    }
}

@Composable
fun StudyRoomMain(viewModel : StudyRoomViewModel , navController : NavController, state : StudyRoomState){
    viewModel.getStudyRoomSheet()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = DodamDimen.ScreenSidePadding)
        ){
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))

        Row() {
            DodamItemCard(title = if(state.isWeekDay == false) "오전 1" else "자습 1", subTitle = "${state.firstClass} / ${state.totalStudents}", onClick = {
                navController.navigate("class_1")
            })
            Spacer(modifier = Modifier.width(DodamDimen.CardSidePadding))
            DodamItemCard(title = if(state.isWeekDay == false) "오전 2" else "자습 2", subTitle = "${state.secondClass} / ${state.totalStudents}", onClick = {
                navController.navigate("class_1")
            })
        }
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        Row() {
            DodamItemCard(title = if(state.isWeekDay == false) "오후 1" else "자습 3", subTitle = "${state.thirdClass} / ${state.totalStudents}", onClick = {
                navController.navigate("class_1")
            })
            Spacer(modifier = Modifier.width(DodamDimen.CardSidePadding))
            DodamItemCard(title = if(state.isWeekDay == false) "오후 2" else "자습 4", subTitle = "${state.fourthClass} / ${state.totalStudents}", onClick = {
                navController.navigate("class_1")
            })
        }
    }
}

@Composable
fun FirstClass(viewModel : StudyRoomViewModel,navController : NavController, state : StudyRoomState) {
    viewModel.getSheetByTime(1)
    Box(modifier = Modifier.fillMaxSize()) {
        DodamAppBar(onStartIconClick = { navController.popBackStack() }, title = if(state.isWeekDay == false) "오전 1" else "자습 1")
        LazyColumn() {
            items(state.studyRoomList!!.studyRoomList) { item ->
                DodamStudyRoomItem(
                    studyRoom = item,
                    member = item.student.member,
                    place = item.place.name,
                    status = item.status,
                    onClick = {
                        viewModel.checkStudyRoom(
                            item.id,
                            item.status == StudyRoomStatus.CHECKED
                        )
                        viewModel.getSheetByTime(1)
                    }
                )
            }
        }
    }
}