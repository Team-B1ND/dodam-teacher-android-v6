package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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

    SelectBar(
        modifier = Modifier
            .padding(horizontal = DodamDimen.ScreenSidePadding),
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
        onSelectedItem = { idx ->
            viewModel.updateClassroom(idx)
        }
    )

    LazyColumn(
        contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = DodamDimen.ScreenSidePadding),
        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
    ) {
        items(state.students) { pointStudent ->
            CheckStudentItem(
                modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
                pointStudent = pointStudent,
                pointViewModel = viewModel
            )
        }
    }
}