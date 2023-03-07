package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.component.basic.toggle.DodamCheckBox
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.theme.Label2
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm.PointViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PointScreen(
    navController: NavController,
    pointViewModel: PointViewModel = hiltViewModel(),
) {

    val state = pointViewModel.collectAsState()
    val context = LocalContext.current

    BackHandler {
        checkPage(navController, state.value.page, pointViewModel)
    }

    pointViewModel.collectSideEffect {
        when(it) {
            is PointSideEffect.FailToGetClassrooms -> {
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("PointErrorLog", it.exception.stackTraceToString())
                navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier.background(DodamTheme.color.White)
    ) {
        DodamAppBar(onStartIconClick = {
            checkPage(navController, state.value.page, pointViewModel)
        })

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            val gradeList = state.value.classrooms.asSequence().map { it.grade }.distinct().sortedDescending().map { "${it}학년" }.plus(
                stringResource(id = R.string.label_all)
            ).toList().reversed()

            val roomList = state.value.classrooms.asSequence().map { it.room }.distinct().sortedDescending().map { "${it}반" }.plus(
                stringResource(id = R.string.label_all)
            ).toList().reversed()

            PointCategorySelectBar(
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                categoryList = gradeList,
                onSelectedItem = { idx -> context.shortToast(idx.toString()) }
            )

            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

            PointCategorySelectBar(
                modifier = Modifier
                    .padding(horizontal = DodamDimen.ScreenSidePadding),
                categoryList = roomList,
                onSelectedItem = { idx -> context.shortToast(idx.toString()) }
            )

            val studentList = listOf(
                PointStudent("최민재"),
                PointStudent("금현호"),
                PointStudent("김도현"),
                PointStudent("기준"),
                PointStudent("임동현"),
                PointStudent("김상은"),
                PointStudent("김준호"),
                PointStudent("우준성"),
                PointStudent("크리스"),
                PointStudent("Mooooong"),
                PointStudent("Kim"),
                PointStudent("도현욱"),
                PointStudent("윤석규"),
            )

            LazyColumn(
                contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding * 2, bottom = DodamDimen.ScreenSidePadding),
                verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
            ) {
                items(studentList) { pointStudent ->
                    CheckStudentItem(
                        modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
                        pointStudent = pointStudent
                    )
                }
            }
        }

        Box(
            modifier = Modifier.padding(
                bottom = DodamDimen.ScreenSidePadding,
                start = DodamDimen.ScreenSidePadding,
                end = DodamDimen.ScreenSidePadding,
            )
        ) {
            DodamMaxWidthButton(
                modifier = Modifier.background(DodamTheme.color.MainColor400),
                text = stringResource(id = R.string.label_next),
            ) {
                when (state.value.page) {
                    1 -> pointViewModel.updatePage(2)
                    2 -> pointViewModel.updatePage(3)
                    3 -> {}
                }
            }
        }
    }
}

@Composable
private fun PointCategorySelectBar(
    modifier: Modifier = Modifier,
    categoryList: List<String>,
    onSelectedItem: (idx: Int) -> Unit,
) {
    var currentSelectedIdx by remember { mutableStateOf(0) }

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

@Composable
private fun CheckStudentItem(
    modifier: Modifier = Modifier,
    pointStudent: PointStudent
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .surface(RoundedCornerShape(100.dp), DodamTheme.color.Background)
            .height(44.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Avatar(
            modifier = Modifier
                .padding(start = DodamDimen.ScreenSidePadding)
                .size(30.dp),
            iconColor = DodamTheme.color.Gray400,
            iconSize = 15.dp,
            backgroundColor = DodamTheme.color.White,
        )

        Spacer(modifier = Modifier.width(11.dp))

        Label1(
            text = pointStudent.name,
            modifier = Modifier.weight(1f)
        )

        DodamCheckBox(
            modifier = Modifier
                .padding(end = DodamDimen.ScreenSidePadding),
            isChecked = pointStudent.isChecked,
            boxSize = 16.dp
        )
    }
}

data class PointStudent(
    val name: String,
    val isChecked: Boolean = false,
)

private fun checkPage(navController: NavController, page: Int, pointViewModel: PointViewModel) {
    when (page) {
        1 -> navController.popBackStack()
        2 -> pointViewModel.updatePage(1)
        else -> pointViewModel.updatePage(2)
    }
}
