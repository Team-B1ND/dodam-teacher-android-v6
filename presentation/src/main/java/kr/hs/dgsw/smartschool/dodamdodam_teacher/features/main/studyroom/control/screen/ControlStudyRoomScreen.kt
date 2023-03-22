package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.ButtonType
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.input.area.DodamSelectArea
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.DodamTeacherSelectArea
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi.ControlStudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.vm.ControlStudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ControlStudyRoomScreen(
    studentId: Int,
    navController: NavController,
    viewModel: ControlStudyRoomViewModel = hiltViewModel(),
) {
    LaunchedEffect(1) {
        viewModel.getStudyRooms(studentId)
    }

    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    viewModel.collectSideEffect {
        when (it) {
            is ControlStudyRoomSideEffect.ShowException -> {
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("ControlStudyRoomScreen", it.exception.stackTraceToString())
            }
            is ControlStudyRoomSideEffect.SuccessCtrl -> {
                context.shortToast(context.getString(R.string.desc_success_ctrl_studyroom))
                navController.popBackStack()
            }
        }
    }

    if (state.ctrlStudyRoomLoading || state.getMyInfoLoading)
        LoadInFullScreen()
    else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DodamTheme.color.Background)
        ) {
            DodamAppBar(
                backgroundColor = DodamTheme.color.Background,
                onStartIconClick = { navController.popBackStack() }
            )

            LazyColumn(
                contentPadding = PaddingValues(vertical = DodamDimen.ScreenSidePadding),
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding)
                    .weight(1f),
            ) {
                val sortedStudyRooms = state.myStudyRooms.sortedBy { it.timeTable.id }.map {
                    StudyRoom(
                        id = it.id,
                        date = it.date,
                        timeTable = it.timeTable,
                        place = Place(
                            id = it.place.id,
                            name = state.places.find { place -> place.id == it.place.id }?.name ?: "",
                            type = Place.PlaceType(
                                id = -1,
                                name = ""
                            )
                        ),
                        studentId = it.studentId,
                        status = it.status,
                        teacher = it.teacher,
                    )
                }
                itemsIndexed(state.timeTables.sortedBy { it.startTime }) { idx, timeTable ->
                    PlaceSelectItem(
                        label = timeTable.name,
                        placeList = state.places,
                        initSelectedItem = sortedStudyRooms.find { studyRoom -> studyRoom.timeTable.id.let { timeId -> if (timeId > 4) timeId - 4 else timeId } == idx + 1 }?.place?.name ?: ""
                    ) { value, id ->
                        viewModel.updateSelectedStudyRoom(timeTable.id, StudyRoomItem(placeId = id, timeTableId = timeTable.id))
                    }
                }
            }

            DodamMaxWidthButton(
                modifier = Modifier
                    .padding(bottom = DodamDimen.ScreenSidePadding, start = DodamDimen.ScreenSidePadding, end = DodamDimen.ScreenSidePadding),
                type = ButtonType.PrimaryVariant,
                text = stringResource(id = R.string.label_modify)
            ) {
                viewModel.ctrlStudyRoom(studentId, state.selectedStudyRoom.toList().map { it.second })
            }
        }

}

@Composable
private fun PlaceSelectItem(
    label: String,
    placeList: List<Place>,
    initSelectedItem: String,
    onChangedItem: (String, Int) -> Unit,
) {
    Column {
        Label1(
            text = label
        )
        Spacer(modifier = Modifier.height(5.dp))
        DodamTeacherSelectArea(
            itemList = placeList.map { it.name },
            modifier = Modifier
                .fillMaxWidth(),
            hint = stringResource(id = R.string.desc_hint_select_place),
            initSelectedItem = initSelectedItem
        ) {
            onChangedItem(it, placeList.find { place -> place.name == it }?.id ?: -1)
        }
    }
}
