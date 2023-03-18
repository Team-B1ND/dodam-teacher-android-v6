package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.place.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.input.DodamSelect
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PlaceScreen(
    navController: NavController,
    viewModel: StudyRoomViewModel = hiltViewModel(),
) {
    val state = viewModel.collectAsState().value
    val requestList = mutableListOf<StudyRoomRequest.RequestItem>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DodamAppBar(onStartIconClick = { navController.popBackStack() }, title = "자습실 신청 관리")
        Spacer(modifier = Modifier.height(60.dp))
        Title3(text = if (!state.isWeekDay) stringResource(id = R.string.label_forenoon_1) else stringResource(id = R.string.label_class_8))
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = { placeName ->
                state.placeList.forEach { place ->
                    if (place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestItem(
                                timeTableId = state.timeTableList[0].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if (!state.isWeekDay) stringResource(id = R.string.label_forenoon_2) else stringResource(id = R.string.label_class_9))
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = { placeName ->
                state.placeList.forEach { place ->
                    if (place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestItem(
                                timeTableId = state.timeTableList[1].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if (!state.isWeekDay) stringResource(id = R.string.label_afternoon_1) else stringResource(id = R.string.label_class_10))
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = { placeName ->
                state.placeList.forEach { place ->
                    if (place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestItem(
                                timeTableId = state.timeTableList[2].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(45.dp))
        Title3(text = if (!state.isWeekDay) stringResource(id = R.string.label_afternoon_2) else stringResource(id = R.string.label_class_11))
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        DodamSelect(
            itemList = state.placeList.map {
                it.name
            },
            hint = "교실을 선택해주세요",
            onItemClickListener = { placeName ->
                state.placeList.forEach { place ->
                    if (place.name == placeName)
                        requestList.add(
                            StudyRoomRequest.RequestItem(
                                timeTableId = state.timeTableList[3].id,
                                placeId = place.id
                            )
                        )
                }
            }
        )
        DodamMaxWidthButton(
            text = "수정", onClick = {
                viewModel.ctrlStudyRoom(state.student!!, StudyRoomRequest(requestList))
                navController.popBackStack()
            },
            modifier = Modifier.padding(DodamDimen.ScreenSidePadding)
        )
    }
}