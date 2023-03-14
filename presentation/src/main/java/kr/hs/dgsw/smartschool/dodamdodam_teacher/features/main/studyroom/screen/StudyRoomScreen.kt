package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamItemCard
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTab
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTabs
import kr.hs.dgsw.smartschool.components.foundation.Text
import kr.hs.dgsw.smartschool.components.theme.Body3
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamPlaceItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item.DodamStudyRoomItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
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
            startDestination = "main",
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable("main") {
                studyRoomViewModel.getStudyRoomSheet()
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
    viewModel.getStudyRoomSheet()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.Background),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))

        Row() {
            DodamItemCard(title = if(state.isWeekDay == false) "오전 1" else "자습 1", subTitle = "${state.firstClass} / ${state.totalStudents}", onClick = {
                navController.navigate("first")
            })
            Spacer(modifier = Modifier.width(DodamDimen.CardSidePadding))
            DodamItemCard(title = if(state.isWeekDay == false) "오전 2" else "자습 2", subTitle = "${state.secondClass} / ${state.totalStudents}", onClick = {
                navController.navigate("second")
            })
        }
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        Row() {
            DodamItemCard(title = if(state.isWeekDay == false) "오후 1" else "자습 3", subTitle = "${state.thirdClass} / ${state.totalStudents}", onClick = {
                navController.navigate("third")
            })
            Spacer(modifier = Modifier.width(DodamDimen.CardSidePadding))
            DodamItemCard(title = if(state.isWeekDay == false) "오후 2" else "자습 4", subTitle = "${state.fourthClass} / ${state.totalStudents}", onClick = {
                navController.navigate("fourth")
            })
        }
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
                1 -> "오전 1"
                2 -> "오전 2"
                3 -> "오후 1"
                4 -> "오후 2"
                else -> "오전 1"
            }
        } else {
            when (type){
                1 -> "자습 1"
                2 -> "자습 2"
                3 -> "자습 3"
                4 -> "자습 4"
                else -> "자습 1"
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
                }
            )
            DodamTab(
                text = "미신청",
                selected = selectedTab == 2,
                onClick = {
                    selectedTab = 2
                    tabNavController.navigate("un_apply")
                }
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
            items(state.studyRoomList!!.studyRoomList ?: emptyList()) { item ->
                DodamStudyRoomItem(
                    member = item.student.member,
                    place = item.place.name,
                    status = item.status,
                    onClick = {
                        viewModel.checkStudyRoom(
                            item.id,
                            item.status == StudyRoomStatus.CHECKED
                        )
                    }
                )
            }
        }
        1 -> LazyColumn(
            modifier = Modifier
                .padding(DodamDimen.ScreenSidePadding)
        ) {
            items(state.studyRoomList!!.otherStudents ?: emptyList()) { item ->
                DodamStudyRoomItem(
                    member = item.member,
                    place = "미신청",
                    status = StudyRoomStatus.PENDING,
                    onClick = {
                        navController.navigate("place")
                    }
                )
            }
        }
    }
}

@Composable
fun PlaceScreen(viewModel : StudyRoomViewModel, navController : NavController, state : StudyRoomState) {
    viewModel.studentId()
    viewModel.getPlaces()
    val tabNavController = rememberNavController()
    var selectedTab by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DodamAppBar(onStartIconClick = { navController.popBackStack() }, title = "교실을 선택해주세요")
        DodamTabs(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DodamTab(
                text = if(state.isWeekDay == false) "오전 1" else "자습 1",
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    tabNavController.navigate("apply")
                }
            )
            DodamTab(
                text = if(state.isWeekDay == false) "오전 2" else "자습 2",
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    tabNavController.navigate("apply")
                }
            )
            DodamTab(
                text = if(state.isWeekDay == false) "오후 1" else "자습 3",
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    tabNavController.navigate("apply")
                }
            )
            DodamTab(
                text = if(state.isWeekDay == false) "오후 2" else "자습 4",
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    tabNavController.navigate("apply")
                }
            )
        }
        NavHost(
            navController = tabNavController,
            startDestination = "apply",
            modifier = Modifier.fillMaxSize()
        ){
            composable("first"){
                PlaceList(state = state, viewModel = viewModel)
            }
            composable("second"){
                PlaceList(state = state, viewModel = viewModel)
            }
            composable("third"){
                PlaceList(state = state, viewModel = viewModel)
            }
            composable("fourth"){
                PlaceList(state = state, viewModel = viewModel)
            }
        }
    }
}
@Composable
fun PlaceList(state: StudyRoomState, viewModel: StudyRoomViewModel){
    LazyVerticalGrid(
        modifier = Modifier
            .padding(DodamDimen.ScreenSidePadding),
        columns = GridCells.Fixed(3)
    ) {
        items(state.placeList ?: emptyList()) { item ->
            DodamPlaceItem(
                place = item,
                selected = state.hasStudyRoom ?: false,
                onClick = {
                    viewModel.ctrlStudyRoom(
                        state.selectedStudent!!.id, StudyRoomRequest(
                            listOf(
                                StudyRoomRequest.RequestStudyRoom(
                                    placeId = item.id,
                                    timeTableId = 23
                                )
                            )
                        )
                    )
                }
            )
        }
    }
}


@Composable
private fun CardDetailItem(
    title: String,
    content: String,
    icon: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Body3(text = title, textColor = DodamTheme.color.Gray500)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = content,
                color = DodamTheme.color.Black,
                style = DodamTheme.typography.label1.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
    }
}