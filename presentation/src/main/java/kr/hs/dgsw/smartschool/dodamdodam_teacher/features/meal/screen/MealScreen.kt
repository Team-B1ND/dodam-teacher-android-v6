package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.vm.MealViewModel


@Composable
fun MealScreen(
    mealViewModel: MealViewModel = hiltViewModel(),
) {

    val getMealContainer = mealViewModel.container
    val getMealState = getMealContainer.stateFlow.collectAsState().value
    val getMealSideEffect = getMealContainer.sideEffectFlow

    val coroutineScope = rememberCoroutineScope()

    

}