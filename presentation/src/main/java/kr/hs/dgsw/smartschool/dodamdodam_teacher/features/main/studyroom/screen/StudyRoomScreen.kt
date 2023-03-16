package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.input.DodamSelect
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamContentCard
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTab
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTabs
import kr.hs.dgsw.smartschool.components.theme.*
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamStudyRoomItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StudyRoomScreen(
    navController: NavController,
    studyRoomViewModel: StudyRoomViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val studyRoomState = studyRoomViewModel.container.stateFlow.collectAsState().value

    val tabNavController = rememberNavController()

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
                startDestination = "main",
                modifier = Modifier
                    .fillMaxSize()
            ) {
                composable("main") {
                    studyRoomViewModel.getAllStudyRooms()
                    StudyRoomMain(studyRoomViewModel, tabNavController, studyRoomState)
                }
                composable("first") {
                    ApplyScreen(studyRoomViewModel, tabNavController, studyRoomState, 1)
                }
                composable("second") {
                    ApplyScreen(studyRoomViewModel, tabNavController, studyRoomState, 2)
                }
                composable("third") {
                    ApplyScreen(studyRoomViewModel, tabNavController, studyRoomState, 3)
                }
                composable("fourth") {
                    ApplyScreen(studyRoomViewModel, tabNavController, studyRoomState, 4)
                }
                composable("place"){
                    PlaceScreen(studyRoomViewModel, tabNavController , studyRoomState)
                }
            }
        }
}

@Composable
fun StudyRoomMain(viewModel : StudyRoomViewModel , navController : NavController, state : StudyRoomState){
    viewModel.getAllStudyRooms()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.Background),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        DodamContentCard(
            title = if(state.isWeekDay == true) stringResource(id = R.string.class_8) else stringResource(id = R.string.forenoon_1),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable { navController.navigate("first") },
            hasLinkIcon = true,
            content = {
                Body2(text = "${state.firstClassCount} / ${state.totalStudentsCount}") }
        )
        Spacer(modifier = Modifier.height(11.dp))
        DodamContentCard(
            title = if(state.isWeekDay == true) stringResource(id = R.string.class_9) else stringResource(id = R.string.forenoon_2),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable { navController.navigate("second") },
            hasLinkIcon = true,
            content = {
                Body2(text = "${state.secondClassCount} / ${state.totalStudentsCount}")}
        )
        Spacer(modifier = Modifier.height(11.dp))
        DodamContentCard(
            title = if(state.isWeekDay == true) stringResource(id = R.string.class_10) else stringResource(id = R.string.afternoon_1),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable { navController.navigate("third") }       ,
                    hasLinkIcon = true,
            content = {
                Body2(text = "${state.thirdClassCount} / ${state.totalStudentsCount}")}
        )
        Spacer(modifier = Modifier.height(11.dp))
        DodamContentCard(
            title = if(state.isWeekDay == true) stringResource(id = R.string.class_11) else stringResource(id = R.string.afternoon_2),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable { navController.navigate("fourth") },
            hasLinkIcon = true,
            content = {
                Body2(text = "${state.fourthClassCount} / ${state.totalStudentsCount}")
                      },
        )
    }
}

@Composable
fun ApplyScreen(viewModel : StudyRoomViewModel, navController : NavController, state : StudyRoomState, type : Int) {
    viewModel.getSheetByTime(type)
    val tabNavController = rememberNavController()
    var selectedTab by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.Background)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DodamAppBar(onStartIconClick = { navController.popBackStack() }, title =
        if(state.isWeekDay == false) {
            when (type){
                1 -> stringResource(id = R.string.forenoon_1)
                2 -> stringResource(id = R.string.forenoon_2)
                3 -> stringResource(id = R.string.afternoon_1)
                4 -> stringResource(id = R.string.afternoon_2)
                else -> stringResource(id = R.string.forenoon_1)
            }
        } else {
            when (type){
                1 -> stringResource(id = R.string.class_8)
                2 -> stringResource(id = R.string.class_9)
                3 -> stringResource(id = R.string.class_10)
                4 -> stringResource(id = R.string.class_11)
                else -> stringResource(id = R.string.class_8)
            }
        }
        )
        DodamTabs(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DodamTab(
                text = "신청",
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    tabNavController.navigate("apply")
                },
                modifier = Modifier.weight(1f)
            )
            DodamTab(
                text = "미신청",
                selected = selectedTab == 2,
                onClick = {
                    selectedTab = 2
                    tabNavController.navigate("un_apply")
                },
                modifier = Modifier.weight(1f)
            )
        }
        NavHost(
            navController = tabNavController,
            startDestination = "apply",
            modifier = Modifier.fillMaxSize()
        ){
            composable("apply"){
                ApplyList(tabType = 0, state = state, viewModel = viewModel, navController = navController)
            }
            composable("un_apply"){
                ApplyList(tabType = 1, state = state, viewModel = viewModel, navController = navController)
            }
        }
    }
}


@Composable
fun ApplyList(navController: NavController, tabType : Int, state: StudyRoomState, viewModel: StudyRoomViewModel){
    when(tabType){
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
                        viewModel.getTimeTable(item.student)
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
}
@Composable
fun PlaceScreen(viewModel : StudyRoomViewModel, navController : NavController, state : StudyRoomState) {
    val requestList = mutableListOf<StudyRoomRequest.RequestStudyRoom>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DodamAppBar(onStartIconClick = { navController.popBackStack() }, title = "자습실 신청 관리")
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if(state.isWeekDay == false) "오전 1" else "자습 1")
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = {placeName ->
                state.placeList.forEach{place ->
                    if(place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestStudyRoom(
                                timeTableId = state.timeTableList[0].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if(state.isWeekDay == false) "오전 2" else "자습 2")
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = {placeName ->
                state.placeList.forEach{place ->
                    if(place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestStudyRoom(
                                timeTableId = state.timeTableList[1].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if(state.isWeekDay == false) "오후 1" else "자습 3")
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = {placeName ->
                state.placeList.forEach{place ->
                    if(place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestStudyRoom(
                                timeTableId = state.timeTableList[2].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if(state.isWeekDay == false) "오후 2" else "자습 4")
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = {placeName ->
                state.placeList.forEach{place ->
                    if(place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestStudyRoom(
                                timeTableId = state.timeTableList[3].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.fillMaxHeight(1F))
        DodamMaxWidthButton(text = "수정", onClick = {
            viewModel.ctrlStudyRoom(state.student!!, StudyRoomRequest(requestList))
        },
           modifier = Modifier.padding(DodamDimen.ScreenSidePadding))
    }
}