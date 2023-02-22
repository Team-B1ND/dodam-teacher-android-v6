package kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.mvi.MainSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.mvi.MainState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.vm.MainViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavigationGraph
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        mainViewModel.observe(lifecycleOwner = this, state = ::render, sideEffect = ::handleSideEffect)
        mainViewModel.getEnableAutoLogin()
    }

    private fun render(state: MainState) {
        state.enableAutoLogin?.let {
            setContent {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()

                systemUiController.setSystemBarsColor(
                    color = DodamTheme.color.White
                )

                DodamTheme {
                    Box {
                        NavigationGraph(navController = navController, enableAutoLogin = it)
                    }
                }
            }
        }
    }

    private fun handleSideEffect(sideEffect: MainSideEffect) {
        when (sideEffect) {
            is MainSideEffect.ToastGetEnableAutoLoginErrorMessage -> {
                Log.e("MainActivity", sideEffect.throwable.stackTraceToString())
                Toast.makeText(this, sideEffect.throwable.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
