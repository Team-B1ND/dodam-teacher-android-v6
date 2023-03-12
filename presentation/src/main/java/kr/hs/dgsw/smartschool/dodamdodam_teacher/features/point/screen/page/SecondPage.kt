package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kr.hs.dgsw.smartschool.components.theme.Title2
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.item.StudentItem
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel

@Composable
fun ColumnScope.SecondPage(
    state: PointState,
    viewModel: PointViewModel,
) {
    SelectBar(
        modifier = Modifier
            .padding(horizontal = DodamDimen.ScreenSidePadding),
        categoryList = listOf(stringResource(id = R.string.label_dormitory), stringResource(id = R.string.label_school)),
        selectIdx = state.currentPlace,
    ) { idx ->
        viewModel.updateCurrentPlace(idx)
    }

    Title2(
        text = stringResource(id = R.string.title_student_list),
        modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding, top = DodamDimen.ScreenSidePadding * 2)
    )

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = DodamDimen.ScreenSidePadding),
        contentPadding = PaddingValues(vertical = DodamDimen.ScreenSidePadding),
        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding),
    ) {
        items(state.pointStudents.filter { it.isChecked }) { pointStudent ->
            StudentItem(pointStudent = pointStudent)
        }
    }
}
