package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.input.DodamSelect
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamItemCard
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTab
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTabs
import kr.hs.dgsw.smartschool.components.foundation.Text
import kr.hs.dgsw.smartschool.components.theme.Body3
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
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
    //TODO(카드 다지안 변경하기)
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
            items(state.studyRoomList!!.studyRoomList ?: emptyList()) { item ->
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
                        viewModel.getSheetById(item.student)
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
            items(state.studyRoomList!!.otherStudents ?: emptyList()) { item ->
                DodamStudyRoomItem(
                    member = item.member,
                    place = null,
                    status = StudyRoomStatus.PENDING,
                    ctrlAction = {
                        viewModel.getSheetById(item)
                        navController.navigate("place")
                    },
                    checkAction = {},
                    classroom = "${item.classroom.grade}학년 ${item.classroom.room}반"
                )
            }
        }
    }
}
//TODO Loading 오류 처리하기 state가 업데이트가 안 되어서 NPE 터짐
@Composable
fun PlaceScreen(viewModel : StudyRoomViewModel, navController : NavController, state : StudyRoomState) {
    val requestList = mutableListOf<StudyRoomRequest.RequestStudyRoom>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.Background)
            .padding(horizontal = DodamDimen.ScreenSidePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DodamAppBar(onStartIconClick = { navController.popBackStack() }, title = "자습실 신청 관리")
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if(state.isWeekDay == false) "오전 1" else "자습 1")
        DodamSelect(
            itemList = state.placeList!!.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = {placeName ->
                state.placeList.forEach{place ->
                    if(place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestStudyRoom(
                                timeTableId = state.timeTableList!![0].id,
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
                                timeTableId = state.timeTableList!![1].id,
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
                                timeTableId = state.timeTableList!![2].id,
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
                                timeTableId =state.timeTableList!![3].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        DodamMaxWidthButton(text = "수정", onClick = {
            viewModel.ctrlStudyRoom(state.student!!, StudyRoomRequest(requestList))
        })
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