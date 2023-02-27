package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.theme.DodamColor

@Composable
fun StudyroomScreen(
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = DodamColor.FeatureColor.MyInfoColor)
    ) {

    }
}