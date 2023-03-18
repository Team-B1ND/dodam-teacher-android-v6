package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.place.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamContentCard
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm.StudyRoomViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun StudyRoomMainScreen(
    navController: NavController,
    viewModel: StudyRoomViewModel = hiltViewModel(),
) {

    val state = viewModel.collectAsState().value;

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamColor.Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(DodamDimen.CardSidePadding))
        DodamContentCard(
            title = if (state.isWeekDay) stringResource(id = R.string.label_class_8) else stringResource(
                id = R.string.label_forenoon_1
            ),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable {
                    viewModel.getSheetByTime(1)
                    navController.navigate("first")
                },
            hasLinkIcon = true,
            content = {
                Body2(text = "${state.firstClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)")
            }
        )
        Spacer(modifier = Modifier.height(11.dp))
        DodamContentCard(
            title = if (state.isWeekDay) stringResource(id = R.string.label_class_9) else stringResource(
                id = R.string.label_forenoon_2
            ),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable {
                    viewModel.getSheetByTime(2)
                    navController.navigate("second")
                },
            hasLinkIcon = true,
            content = {
                Body2(text = "${state.secondClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)")
            }
        )
        Spacer(modifier = Modifier.height(11.dp))
        DodamContentCard(
            title = if (state.isWeekDay) stringResource(id = R.string.label_class_10) else stringResource(
                id = R.string.label_afternoon_1
            ),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable {
                    viewModel.getSheetByTime(3)
                    navController.navigate("third")
                },
            hasLinkIcon = true,
            content = {
                Body2(text = "${state.thirdClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)")
            }
        )
        Spacer(modifier = Modifier.height(11.dp))
        DodamContentCard(
            title = if (state.isWeekDay) stringResource(id = R.string.label_class_11) else stringResource(
                id = R.string.label_afternoon_2
            ),
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .clickable {
                    viewModel.getSheetByTime(4)
                    navController.navigate("fourth")
                },
            hasLinkIcon = true,
            content = {
                Body2(text = "${state.fourthClassCount}명 (신청자) / ${state.totalStudentsCount}명 (전체 인원)")
            },
        )
    }
}