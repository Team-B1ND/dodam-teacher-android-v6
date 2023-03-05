package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.GetMealSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.vm.StudyRoomViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StudyRoomScreen(
    studyRoomViewModel: StudyRoomViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val state = studyRoomViewModel.collectAsState().value
    studyRoomViewModel.collectSideEffect{ handleSideEffect(context,it)}
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
    }

}

private fun handleSideEffect(context: Context, sideEffect: StudyRoomSideEffect) {
    when (sideEffect) {
        is StudyRoomSideEffect.Toast -> showToast(context, sideEffect.message)
    }
}
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}