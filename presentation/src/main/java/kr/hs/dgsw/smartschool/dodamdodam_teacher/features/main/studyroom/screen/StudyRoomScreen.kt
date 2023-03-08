package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StudyRoomScreen(
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

    val studyRoomItemList = listOf<ItemCardContent>(
        ItemCardContent(
            title = "8교시",
            subTitle = "신청 : ${studyRoomState.firstClass}",
            icon = {}
        ),
        ItemCardContent(
            title = "9교시",
            subTitle = "신청 : ${studyRoomState.secondClass}",
            icon = {}
        ),
        ItemCardContent(
            title = "10교시",
            subTitle = "신청 : ${studyRoomState.thirdClass}",
            icon = {}
        ),
        ItemCardContent(
            title = "11교시",
            subTitle = "신청 : ${studyRoomState.fourthClass}",
            icon = {}
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DodamColor.FeatureColor.MyInfoColor)
    ) {
        LazyRow(){
            items(studyRoomState.studyRoomList!!.studyRoomList){

            }
        }
    }
}

@Preview
@Composable
fun StudyRoomPreview(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StudyRoomScreen(navController = rememberNavController())
    }
}

private data class ItemCardContent(
    val subTitle: String,
    val title: String,
    val icon: @Composable () -> Unit,
)
