package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.GetMealSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.vm.MealViewModel
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MealScreen(
    mealViewModel: MealViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val state = mealViewModel.collectAsState().value
    mealViewModel.collectSideEffect { handleSideEffect(context, it) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
        Text(text = "아침 : ${meal.breakfast}")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "점심 : ${meal.lunch}")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "저녁 : ${meal.dinner}")
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
