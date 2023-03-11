package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.schedule.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.Divider
import kr.hs.dgsw.smartschool.components.component.organization.calendar.DodamCalendar
import kr.hs.dgsw.smartschool.components.component.organization.calendar.schedule.DodamScheduleItem
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.Body1
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.IcCalendar
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.schedule.mvi.ScheduleSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.schedule.vm.ScheduleViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ScheduleScreen(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = scheduleViewModel.collectAsState().value

    scheduleViewModel.collectSideEffect {
        when (it) {
            is ScheduleSideEffect.ShowException -> {
                Log.e("ScheduleLog", it.exception.stackTraceToString())
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DodamTheme.color.White)
    ) {
        DodamAppBar(onStartIconClick = {
            navController.popBackStack()
        })

        DodamCalendar(schedules = state.schedules) { date, daySchedules ->
            if (date.monthValue != state.currentDate.monthValue) {
                scheduleViewModel.getSchedules(date)
            }
            scheduleViewModel.updateDate(date)
            scheduleViewModel.updateTodaySchedules(daySchedules.map { it.schedule })
        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider(thickness = 1.dp, color = DodamTheme.color.Gray100)
        Spacer(modifier = Modifier.height(10.dp))
        Body2(
            text = "${state.currentDate.monthValue}월 ${state.currentDate.dayOfMonth}일",
            textColor = DodamTheme.color.Gray500,
            modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding)
        )
        Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))
        if (state.todaySchedules.isEmpty()) {
            Body1(
                text = stringResource(id = R.string.desc_empty_schedule),
                modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding)
            )
        } else {
            Row {
                Spacer(modifier = Modifier.width(24.dp))
                IcCalendar(
                    contentDescription = null,
                    tint = DodamTheme.color.Gray500,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(11.dp))
                LazyColumn {
                    items(state.todaySchedules) { schedule ->
                        DodamScheduleItem(schedule = schedule)
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}
