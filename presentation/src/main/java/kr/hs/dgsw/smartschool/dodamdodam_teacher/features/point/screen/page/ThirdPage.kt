package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.theme.Label2
import kr.hs.dgsw.smartschool.components.theme.Title2
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select.SelectBar
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel
import kr.hs.dgsw.smartschool.domain.model.point.PointType

@Composable
fun ColumnScope.ThirdPage(
    state: PointState,
    viewModel: PointViewModel,
) {

    val context = LocalContext.current

    SelectBar(
        modifier = Modifier
            .padding(horizontal = DodamDimen.ScreenSidePadding),
        categoryList = listOf(stringResource(id = R.string.label_bonus), stringResource(id = R.string.label_minus)),
        selectIdx = state.currentPointType,
    ) { idx ->
        viewModel.updateCurrentPointType(idx)
    }

    LazyColumn(
        contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = DodamDimen.ScreenSidePadding),
        modifier = Modifier
            .padding(horizontal = DodamDimen.ScreenSidePadding)
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
    ) {
        items(
            if (state.currentPointType == 0)
                state.bonusReason
            else
                state.minusReason
        ) { pointReason ->
            Row(
                modifier = Modifier
                    .surface(
                        shape = RoundedCornerShape(100.dp),
                        backgroundColor = when (state.currentSelectedReason) {
                            null -> DodamTheme.color.Background
                            pointReason -> DodamTheme.color.MainColor400
                            else -> DodamTheme.color.Background
                        }
                    )
                    .height(40.dp)
                    .fillMaxWidth()
                    .dodamClickable {
                        viewModel.updateReason(pointReason)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Label1(
                    text = pointReason.reason,
                    textColor = when (state.currentSelectedReason) {
                        null -> DodamTheme.color.Black
                        pointReason -> DodamTheme.color.White
                        else -> DodamTheme.color.Black
                    },
                    modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding)
                )
            }
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
    ) {
        Title2(
            text = stringResource(id = R.string.title_point_info),
            textColor = DodamTheme.color.Black,
        )
        val type = when (state.currentSelectedReason?.type) {
            null -> ""
            PointType.BONUS -> context.getString(R.string.label_bonus)
            PointType.MINUS -> context.getString(R.string.label_minus)
            else -> ""
        }
        PointInfoItem(title = stringResource(id = R.string.label_point_type), content = type)
        PointInfoItem(title = stringResource(id = R.string.label_point_score), content = state.currentSelectedReason?.score?.toString() ?: "")
        PointInfoItem(title = stringResource(id = R.string.label_point_reason), content = state.currentSelectedReason?.reason ?: "")
        Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))
    }
}

@Composable
private fun PointInfoItem(title: String, content: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Label1(text = title, textColor = DodamTheme.color.Gray500)
        Spacer(modifier = Modifier.width(DodamDimen.ScreenSidePadding))
        Label2(text = content, textColor = DodamTheme.color.Black)
    }
}
