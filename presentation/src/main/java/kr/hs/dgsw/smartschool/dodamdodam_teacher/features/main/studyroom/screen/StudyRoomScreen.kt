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
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTab
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTabs
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
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
                context.shortToast(it.exception.message ?: context.getString(kr.hs.dgsw.smartschool.dodamdodam_teacher.R.string.content_unknown_exception))
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
            startDestination = "class 1",
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable("study_room"){
                StudyRoomMain(studyRoomState)
            }
            composable("class_1") {
                FirstClass(studyRoomState)
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
@Composable
fun StudyRoomMain(navController : NavController, state : StudyRoomState){
    Column(modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))

        Row() {
            DodamItemCard(title = "수업 1", subTitle = "30/50", onClick = {
                navController.navigate("class_1")
            })
            Spacer(modifier = Modifier.width(DodamDimen.CardSidePadding))
            DodamItemCard(title = "수업 1", subTitle = "30/50", onClick = {
                navController.navigate("class_1")
            })
        }
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        Row() {
            DodamItemCard(title = "수업 1", subTitle = "30/50", onClick = {
                navController.navigate("class_1")
            })
            Spacer(modifier = Modifier.width(DodamDimen.CardSidePadding))
            DodamItemCard(title = "수업 1", subTitle = "30/50", onClick = {
                navController.navigate("class_1")
            })
        }
    }
}

@Composable
fun FirstClass(state : StudyRoomState){
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(){
            items(state.studyRoomList!!.studyRoomList){item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    ) {
                        Avatar(
                            iconColor = DodamColor.MainColor,
                            modifier = Modifier
                                .size(20.dp),
                        )
                        Body2(text = "")
                    }
                }
            }
        }
    }
}