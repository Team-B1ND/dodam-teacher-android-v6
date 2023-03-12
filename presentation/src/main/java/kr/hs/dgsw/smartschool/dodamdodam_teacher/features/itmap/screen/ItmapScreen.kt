package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.screen

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcRefresh
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.mvi.ItmapSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.vm.ItmapViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

private const val FULL_ROTATION = 360f

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun ItmapScreen(
    navController: NavController,
    itmapViewModel: ItmapViewModel = hiltViewModel(),
) {

    val state = itmapViewModel.collectAsState().value
    val context = LocalContext.current

    itmapViewModel.collectSideEffect {
        when (it) {
            is ItmapSideEffect.ShowException -> {
                Log.e("ItmapLog", it.exception.stackTraceToString())
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
            }
        }
    }

    val rotationAngle by animateFloatAsState(
        targetValue = if (state.isRotated) FULL_ROTATION else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = false)
        )
    }

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
                        .align(Alignment.CenterVertically)
                        .dodamClickable(rippleEnable = false) {
                            if (!state.isRefreshing) {
                                itmapViewModel.updateRotated()
                                itmapViewModel.setCompanies()
                            }
                        }
                        .rotate(rotationAngle),
                    contentDescription = null,
                )
            }
        )
        NaverMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = mapUiSettings,
        ) {
            state.companies.forEach {
                Marker(
                    width = 30.dp,
                    height = 40.dp,
                    state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                    iconTintColor = DodamColor.FeatureColor.ItMapColor,
                    captionText = it.name,
                    captionColor = DodamColor.FeatureColor.ItMapColor,
                ) { marker ->
                    navController.navigate(
                        NavGroup.Feature.ITMAP_DETAIL
                            .replace(
                                oldValue = "{companyId}",
                                newValue = "${it.id}"
                            )
                    )
                    true
                }
            }
        }
    }
}

