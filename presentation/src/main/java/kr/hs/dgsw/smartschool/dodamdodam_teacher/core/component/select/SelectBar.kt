package kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.select

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label2

@Composable
fun SelectBar(
    modifier: Modifier = Modifier,
    categoryList: List<String>,
    selectIdx: Int,
    onSelectedItem: (idx: Int) -> Unit,
) {
    var currentSelectedIdx by remember { mutableStateOf(selectIdx) }

    Row(
        modifier = modifier
            .surface(RoundedCornerShape(100.dp), DodamColor.Background)
            .fillMaxWidth()
    ) {
        categoryList.forEachIndexed { idx, category ->
            Box(
                modifier = Modifier
                    .background(
                        shape = when (idx) {
                            0 -> RoundedCornerShape(
                                topStart = 100.dp,
                                bottomStart = 100.dp
                            )
                            categoryList.size - 1 -> RoundedCornerShape(
                                topEnd = 100.dp,
                                bottomEnd = 100.dp
                            )
                            else -> RectangleShape
                        },
                        color = if (currentSelectedIdx == idx) {
                            DodamTheme.color.MainColor400
                        } else
                            DodamTheme.color.Background
                    )
                    .dodamClickable(rippleEnable = false) {
                        onSelectedItem(idx)
                        currentSelectedIdx = idx
                    }
                    .weight(1f)
                    .height(30.dp)
            ) {
                Label2(
                    text = category,
                    textColor = if (currentSelectedIdx == idx)
                        DodamTheme.color.White
                    else
                        DodamTheme.color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}
