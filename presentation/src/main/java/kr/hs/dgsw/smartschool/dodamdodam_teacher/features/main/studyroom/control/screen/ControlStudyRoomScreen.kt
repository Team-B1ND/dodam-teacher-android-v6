package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.input.area.DodamSelectArea
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi.ControlStudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.vm.ControlStudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ControlStudyRoomScreen(
    navController: NavController,
    viewModel: ControlStudyRoomViewModel = hiltViewModel(),
) {

    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    viewModel.collectSideEffect {
        when (it) {
            is ControlStudyRoomSideEffect.ShowException -> {
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("ControlStudyRoomScreen", it.exception.stackTraceToString())
            }
        }
    }

    if (state.ctrlStudyRoomLoading || state.getMyInfoLoading)
        LoadInFullScreen()
    else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DodamTheme.color.White)
        ) {
            DodamAppBar(onStartIconClick = { navController.popBackStack() })

            LazyColumn {

            }
        }

}

@Composable
private fun PlaceSelectItem(
    label: String,
    placeList: List<String>
) {
    Column {
        Label1(
            text = label
        )
        Spacer(modifier = Modifier.height(5.dp))
        DodamSelectArea(
            itemList = placeList,
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}
