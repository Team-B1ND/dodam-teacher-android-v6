package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.item.CheckStudentItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel

@Composable
fun ColumnScope.FirstPage(
    state: PointState,
    viewModel: PointViewModel,
) {
    val gradeList = state.classrooms.asSequence().map { it.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    val roomList = state.classrooms.asSequence().map { it.room }.distinct().sortedDescending().map { "${it}반" }.plus(
        stringResource(id = R.string.label_all)
    ).toList().reversed()

    LaunchedEffect(key1 = state) {
        Log.d("TAG", "FirstPage: ${state.pointStudents}")
    }

    SelectBar(
        modifier = Modifier
            .padding(horizontal = DodamDimen.ScreenSidePadding),
        selectIdx = state.currentGrade,
        categoryList = gradeList,
        onSelectedItem = { idx ->
            viewModel.updateGrade(idx)
        }
    )

    Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

    SelectBar(
        modifier = Modifier
            .padding(horizontal = DodamDimen.ScreenSidePadding),
        categoryList = roomList,
        selectIdx = state.currentClassroom,
        onSelectedItem = { idx ->
            viewModel.updateClassroom(idx)
        }
    )

    LazyColumn(
        contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = DodamDimen.ScreenSidePadding),
        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
    ) {
        items(getStudentList(state)) { pointStudent ->
            CheckStudentItem(
                modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
                pointStudent = pointStudent,
                pointViewModel = viewModel
            )
//            PointState.PointStudent()
        }
    }
}

private fun getStudentList(state: PointState): List<PointState.PointStudent> {
    return if (state.currentGrade == 0 && state.currentClassroom == 0) {
        Log.d("LOGLOG", "CURRENT GRADE 0 ${state.currentGrade}  ${state.currentClassroom}")
        state.pointStudents
    } else if (state.currentGrade == 0) {
        Log.d("LOGLOG", "CURRENT GRADE 1 ${state.currentGrade}  ${state.currentClassroom}")
        state.pointStudents.filter {
            // Log.d("LOGLOG", "${it.room} / ${state.currentGrade}")
            it.room == state.currentClassroom
        }
    } else if (state.currentClassroom == 0) {
        Log.d("LOGLOG", "CURRENT GRADE 2 ${state.currentGrade}  ${state.currentClassroom}")
        state.pointStudents.filter {
            it.grade == state.currentGrade
        }
    } else {
        Log.d("LOGLOG", "CURRENT GRADE 3 ${state.currentGrade}  ${state.currentClassroom}")
        state.pointStudents.filter {
            it.grade == state.currentGrade && it.room == state.currentClassroom
        }
    }
}
