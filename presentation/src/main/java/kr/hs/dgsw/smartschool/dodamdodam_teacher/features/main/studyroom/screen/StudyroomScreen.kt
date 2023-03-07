package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StudyroomScreen(
    navController: NavController,
    studyRoomViewModel: StudyRoomViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val studyRoomState = studyRoomViewModel.container.stateFlow.collectAsState().value

    studyRoomViewModel.collectSideEffect {
        /*when (it) {
            is StudyRoomSideEffect.ToastError -> {
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("HomeScreenError", it.exception.stackTraceToString())
            }
        }*/
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DodamColor.FeatureColor.MyInfoColor)
    ) {
        Button(onClick = {}) {
            
        }
    }
}
