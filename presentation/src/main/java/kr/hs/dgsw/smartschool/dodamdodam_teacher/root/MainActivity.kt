package kr.hs.dgsw.smartschool.dodamdodam_teacher.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.theme.Dodam_Teacher_V6Theme
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.screen.LoginScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.screen.MealScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val systemUiController = rememberSystemUiController()

            systemUiController.setSystemBarsColor(
                color = DodamTheme.color.White
            )

            DodamTheme {
                LoginScreen()
            }
        }
    }
}

