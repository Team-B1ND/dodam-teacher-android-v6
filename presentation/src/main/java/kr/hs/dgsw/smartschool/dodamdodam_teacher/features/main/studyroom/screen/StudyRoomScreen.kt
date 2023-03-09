package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

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
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTab
import kr.hs.dgsw.smartschool.components.component.set.tab.DodamTabs
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
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
        DodamTabs() {
            DodamTab(text = "자습 1", selected = tabSelected.value == 1, onClick = {
                tabNavController.navigate("class 1")
            })
            DodamTab(text = "자습 2", selected = tabSelected.value == 2, onClick = {
                tabNavController.navigate("class 2")
            })
            DodamTab(text = "자습 3", selected = tabSelected.value == 3, onClick = {
                tabNavController.navigate("class 3")
            })
            DodamTab(text = "자습 4", selected = tabSelected.value == 4, onClick = {
                tabNavController.navigate("class 4")
            })
        }
        NavHost(
            navController = tabNavController,
            startDestination = "class 1",
            modifier = Modifier
        ) {
            composable("class 1") {
                FirstClass(studyRoomState)
            }
            composable("class2") {
            }
            composable("class3") {
            }
            composable("class4") {
            }
        }
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