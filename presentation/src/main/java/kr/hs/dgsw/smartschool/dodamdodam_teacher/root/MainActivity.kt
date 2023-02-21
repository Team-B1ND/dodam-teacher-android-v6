package kr.hs.dgsw.smartschool.dodamdodam_teacher.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavigationGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val systemUiController = rememberSystemUiController()
            val navController = rememberNavController()

            systemUiController.setSystemBarsColor(
                color = DodamTheme.color.White
            )

            DodamTheme {
                Box {
                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}

