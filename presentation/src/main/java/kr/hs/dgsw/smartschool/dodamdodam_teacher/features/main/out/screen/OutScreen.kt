package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.Title1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.vm.OutViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OutScreen(
    navController: NavController,
    outViewModel: OutViewModel = hiltViewModel(),
) {

    val state = outViewModel.collectAsState().value
    val context = LocalContext.current

    outViewModel.collectSideEffect {
        when (it) {
            is OutSideEffect.ShowException -> {
                context.shortToast(
                    it.exception.message ?: context.getString(R.string.content_unknown_exception)
                )
                Log.e("OutErrorLog", it.exception.stackTraceToString())
                navController.popBackStack()
            }
        }
    }

    val gradeList = state.classrooms.asSequence().map { it.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val roomList = state.classrooms.asSequence().map { it.room }.distinct().sortedDescending().map { "${it}반" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()
    
    val outTypeList = listOf(stringResource(id = R.string.label_outgoing), stringResource(id = R.string.label_outsleeping))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DodamColor.White)
    ) {

        Spacer(modifier = Modifier.height(45.dp))

        Title1(
            text = stringResource(id = R.string.title_out_approve),
            modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding),
        )

        Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))
        
        Column(
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding),
            verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
        ) {
            SelectBar(
                selectIdx = state.currentGrade,
                categoryList = gradeList,
                onSelectedItem = { idx ->
                    outViewModel.updateGrade(idx)
                }
            )
            
            SelectBar(
                categoryList = roomList,
                selectIdx = state.currentClassroom,
                onSelectedItem = { idx ->
                    outViewModel.updateClassroom(idx)
                }
            )
            
            SelectBar(
                categoryList = outTypeList,
                selectIdx = state.currentOutType,
                onSelectedItem = { idx ->
                    outViewModel.updateOutType(idx)
                }
            )
        }


    }
}
