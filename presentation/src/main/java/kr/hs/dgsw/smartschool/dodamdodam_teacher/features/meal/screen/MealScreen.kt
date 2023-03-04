package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.component.organization.calendar.dialog.DodamCalendarDialog
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamMealCard
import kr.hs.dgsw.smartschool.components.component.organization.card.MealType
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.foundation.Text
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.Body1
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.IcLeftArrow
import kr.hs.dgsw.smartschool.components.theme.IcRightArrow
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.anim.shimmerEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.MealSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.MealState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.vm.MealViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.dayOfWeek
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

@Composable
fun MealScreen(
    navController: NavController,
    mealViewModel: MealViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val state = mealViewModel.collectAsState().value
    mealViewModel.collectSideEffect { handleSideEffect(context, it) }

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_runner)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    mealViewModel.getMeal(state.currentDate)

    val scrollState = rememberScrollState()

    if (state.showDialog) {
        DodamCalendarDialog { date ->
            date?.let {
                mealViewModel.updateDate(it)
            }
            mealViewModel.changeShowDialogState()
        }
    }

    Column(
        modifier = Modifier
            .background(DodamTheme.color.Background)
            .fillMaxSize()
    ) {
        DodamAppBar(
            backgroundColor = DodamTheme.color.Background,
            onStartIconClick = { navController.popBackStack() },
        )

        ChangeDateCard(mealViewModel, state)

        Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
        ) {
            DodamMealCard(
                content = state.meal?.breakfast ?: stringResource(id = R.string.desc_empty_breakfast),
                mealType = MealType.BreakFast
            )
            DodamMealCard(
                content = state.meal?.lunch ?: stringResource(id = R.string.desc_empty_lunch),
                mealType = MealType.Lunch
            )
            DodamMealCard(
                content = state.meal?.dinner ?: stringResource(id = R.string.desc_empty_dinner),
                mealType = MealType.Dinner
            )

            Row(
                modifier = Modifier
                    .padding(
                        vertical = 80.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .size(140.dp),
                    composition = composition,
                    progress = { progress },
                )
                Column {
                    Body2(text = LocalDate.now().toString(), textColor = DodamTheme.color.Gray500)
                    Body1(text = stringResource(id = R.string.label_calorie))
                    Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        if (state.getCalorieLoading)
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(20.dp)
                                    .shimmerEffect()
                            )
                        else
                            Text(
                                text = state.calorie.let { it.ifEmpty { stringResource(id = R.string.desc_empty_calorie) } },
                                style = DodamTheme.typography.headline1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ),
                            )
                        Spacer(modifier = Modifier.width(10.dp))
                        Label1(text = stringResource(id = R.string.label_kcal), modifier = Modifier.padding(bottom = 5.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ChangeDateCard(mealViewModel: MealViewModel, state: MealState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DodamDimen.ScreenSidePadding)
            .surface(DodamTheme.shape.large, DodamTheme.color.White)
            .height(60.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            IcLeftArrow(
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = DodamDimen.ScreenSidePadding)
                    .dodamClickable(bounded = false) {
                        mealViewModel.minusDay()
                    },
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .dodamClickable(rippleEnable = false) {
                    mealViewModel.changeShowDialogState()
                }
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                Body2(
                    text = "${state.currentDate} (${state.currentDate.dayOfWeek()})"
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            IcRightArrow(
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = DodamDimen.ScreenSidePadding)
                    .dodamClickable(bounded = false) {
                        mealViewModel.plusDay()
                    },
            )
        }
    }
}

private fun handleSideEffect(context: Context, sideEffect: MealSideEffect) {
    when (sideEffect) {
        is MealSideEffect.ToastError -> context.shortToast(sideEffect.exception?.message ?: context.getString(R.string.content_unknown_exception))
    }
}
