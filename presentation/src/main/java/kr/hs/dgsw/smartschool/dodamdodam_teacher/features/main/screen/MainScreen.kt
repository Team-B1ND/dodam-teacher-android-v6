package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.component.set.navtab.DodamNavBar
import kr.hs.dgsw.smartschool.components.component.set.navtab.DodamNavTab
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.IcHome
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcBurger
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcLocation
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcOut
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.screen.EtcScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.screen.HomeScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.screen.OutScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.screen.StudyroomScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.vm.MainViewModel
import org.orbitmvi.orbit.compose.collectAsState

private lateinit var mainViewModelAll: MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val state = mainViewModel.collectAsState().value
    mainViewModelAll = mainViewModel

    if (state.setClassroomLoading && state.setMembersLoading && state.setStudentsLoading && state.setTeachersLoading)
        LoadInFullScreen()
    else
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            BackHandler(state.selectedTab != 0) {
                mainViewModel.updateSelectedTab(0)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            ) {
                when (state.selectedTab) {
                    0 -> {
                        HomeScreen(navController = navController)
                    }
                    1 -> {
                        StudyroomScreen(navController = navController)
                    }
                    2 -> {
                        OutScreen(navController = navController)
                    }
                    3 -> {
                        EtcScreen(navController = navController)
                    }
                }
            }

            DodamNavBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                    )
                    .surface(
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
                        DodamTheme.color.White
                    )
                    .zIndex(2f)
            ) {
                DodamNavTab(
                    text = stringResource(id = R.string.label_home),
                    icon = { IcHome(contentDescription = null) },
                    onClick = { mainViewModel.updateSelectedTab(0) },
                    selected = state.selectedTab == 0,
                )
                DodamNavTab(
                    text = stringResource(id = R.string.label_studyroom),
                    icon = { IcLocation(contentDescription = null) },
                    onClick = { mainViewModel.updateSelectedTab(1) },
                    selected = state.selectedTab == 1,
                )
                DodamNavTab(
                    text = stringResource(id = R.string.label_out),
                    icon = { IcOut(contentDescription = null) },
                    onClick = { mainViewModel.updateSelectedTab(2) },
                    selected = state.selectedTab == 2,
                )
                DodamNavTab(
                    text = stringResource(id = R.string.label_etc),
                    icon = { IcBurger(contentDescription = null) },
                    onClick = { mainViewModel.updateSelectedTab(3) },
                    selected = state.selectedTab == 3,
                )
            }
        }


}

internal fun updateMainNavTabSelectedTab(tab: Int) {
    mainViewModelAll.updateSelectedTab(tab)
}
