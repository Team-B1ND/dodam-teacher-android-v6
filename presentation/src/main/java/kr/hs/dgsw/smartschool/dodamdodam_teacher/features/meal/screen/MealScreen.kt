package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.GetMealSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.vm.MealViewModel
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar

@Composable
fun MealScreen(
    navController: NavController,
    mealViewModel: MealViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val state = mealViewModel.collectAsState().value
    mealViewModel.collectSideEffect { handleSideEffect(context, it) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DodamAppBar(onStartIconClick = { navController.popBackStack() })
        Button(onClick = { mealViewModel.getMeal(LocalDate.now().minusMonths(3)) }) {
            Text(text = "SHOW!")
        }
        if (state.loading) {
            showToast(context, "로딩 중...")
        } else {
            state.meal?.let {
                MealBox(meal = it)
            }
        }
    }
}

@Composable
fun MealBox(meal: Meal) {
    Column {
    }
}

private fun handleSideEffect(context: Context, sideEffect: GetMealSideEffect) {
    when (sideEffect) {
        is GetMealSideEffect.Toast -> showToast(context, sideEffect.message)
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
