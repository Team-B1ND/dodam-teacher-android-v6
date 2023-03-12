package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcRefresh
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.vm.ItmapViewModel

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun ItmapScreen(
    navController: NavController,
    itmapViewModel: ItmapViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DodamAppBar(
            onStartIconClick = { 
                navController.popBackStack()
            },
            endContents = {
                IcRefresh(
                    tint = DodamTheme.color.Black,
                    modifier = Modifier
                        .size(DodamDimen.AppBarDefaultIconSize)
                        .align(Alignment.CenterVertically),
                    contentDescription = null,
                )
            }
        )
        NaverMap(
            modifier = Modifier.fillMaxSize()
        )
    }
}