package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamContentCard
import kr.hs.dgsw.smartschool.components.component.organization.card.DodamItemCard
import kr.hs.dgsw.smartschool.components.component.set.banner.DodamBanner
import kr.hs.dgsw.smartschool.components.foundation.Text
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.Body3
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.IcBreakfast3D
import kr.hs.dgsw.smartschool.components.theme.IcDinner3D
import kr.hs.dgsw.smartschool.components.theme.IcItmap3D
import kr.hs.dgsw.smartschool.components.theme.IcLunch3D
import kr.hs.dgsw.smartschool.components.theme.IcPoint3D
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.common.DodamTeacherDimens
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcCalendar3D
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcGrinningFace3D
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcSleepingFace3D
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon.IcThinkingFace3D
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm.HomeViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.toSimpleYearDateTime
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val homeState = homeViewModel.container.stateFlow.collectAsState().value

    homeViewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.ToastError -> {
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
                Log.e("HomeScreenError", it.exception.stackTraceToString())
            }
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(DodamColor.Background)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DodamDimen.ScreenSidePadding)
        ) {
            Image(
                modifier = Modifier
                    .width(70.dp),
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(DodamTheme.color.Gray200),
                painter = painterResource(id = R.drawable.logo_dodam),
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {

            DodamContentCard(
                title = stringResource(id = R.string.title_out_approve),
                modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
                hasLinkIcon = true,
                content = { OutApproveCardContent(homeState) }
            )

            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

            DodamContentCard(
                title = stringResource(id = R.string.title_studyroom_check),
                modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
                hasLinkIcon = true,
                content = { OutStudyroomCheckCardContent() }
            )

            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

            Box(modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding)) {
                DodamBanner(
                    imageUrls = listOf(
                        "https://dodam.kr.object.ncloudstorage.com/dodam/c70c7b96-dd67-4467-a49c-2d0baa459624TEAM%20B1ND%20Banner.png",
                        "https://i.ytimg.com/vi/rX0eNVqEn10/maxresdefault.jpg"
                    )
                )
            }

            HomeMealCard(navController, homeState)

            Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

            val itemCardList = listOf(
                ItemCardContent(
                    subTitle = stringResource(id = R.string.label_manage),
                    title = stringResource(id = R.string.title_prize),
                    icon = {
                        IcPoint3D(
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                ),
                ItemCardContent(
                    subTitle = stringResource(id = R.string.label_manage),
                    title = stringResource(id = R.string.title_schedule),
                    icon = {
                        IcCalendar3D(
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                ),
                ItemCardContent(
                    subTitle = stringResource(id = R.string.label_employ),
                    title = stringResource(id = R.string.title_itmap),
                    icon = {
                        IcItmap3D(
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                ),
            )

            LazyRow(
                contentPadding = PaddingValues(start = DodamDimen.ScreenSidePadding),
                horizontalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
            ) {
                items(itemCardList) { item: ItemCardContent ->
                    DodamItemCard(title = item.title, subTitle = item.subTitle, icon = item.icon)
                }
            }

            Spacer(modifier = Modifier.height(72.dp)) // 56 + 16
        }
    }
}

private val CardItemIconSize = 35.dp

@Composable
private fun OutApproveCardContent(
    homeState: HomeState,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Body3(
            text = homeState.outUpdateDate?.toSimpleYearDateTime() ?: LocalDateTime.now().toSimpleYearDateTime(),
            textColor = DodamTheme.color.Gray500,
        )
        Spacer(modifier = Modifier.height(DodamTeacherDimens.DefaultCardContentHeight))
        HomeCardDetailItem(
            title = stringResource(id = R.string.text_outgoing_application),
            content = "${homeState.outgoingCount}명",
            icon = { IcGrinningFace3D(contentDescription = null, modifier = Modifier.size(CardItemIconSize)) }
        )
        Spacer(modifier = Modifier.height(DodamTeacherDimens.DefaultCardContentHeight))
        HomeCardDetailItem(
            title = stringResource(id = R.string.text_outsleeping_application),
            content = "${homeState.outsleepingCount}명",
            icon = { IcSleepingFace3D(contentDescription = null, modifier = Modifier.size(CardItemIconSize)) }
        )
    }
}

@Composable
private fun OutStudyroomCheckCardContent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Body3(
            text = LocalDateTime.now().toSimpleYearDateTime(),
            textColor = DodamTheme.color.Gray500,
        )
        Spacer(modifier = Modifier.height(DodamTeacherDimens.DefaultCardContentHeight))
        HomeCardDetailItem(
            title = stringResource(id = R.string.text_studyroom_check),
            content = "50명 / 70명",
            icon = { IcThinkingFace3D(contentDescription = null, modifier = Modifier.size(CardItemIconSize)) }
        )
    }
}

@Composable
private fun HomeCardDetailItem(
    title: String,
    content: String,
    icon: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Body3(text = title, textColor = DodamTheme.color.Gray500)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = content,
                color = DodamTheme.color.Black,
                style = DodamTheme.typography.label1.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
    }
}

@Composable
private fun MealCardItem(
    content: String,
    icon: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(19.dp))
        Body3(text = content, textColor = DodamTheme.color.Black)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeMealCard(navController: NavController, state: HomeState) {
    val initialPage = when (LocalDateTime.now().hour) {
        in 20..23 -> -1
        in 1..8 -> 0
        in 9..13 -> 1
        in 14..19 -> 2
        else -> 0
    }

    val pagerState = rememberPagerState(initialPage = if (initialPage == -1) 0 else initialPage)

    Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

    HorizontalPager(
        count = 3,
        state = pagerState,
        modifier = Modifier.dodamClickable(rippleEnable = false) {
            navController.navigate(NavGroup.Feature.MEAL)
        }
    ) {
        DodamContentCard(
            modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
            title = getCurrentPageTitle(page = it),
            hasLinkIcon = true,
        ) {
            MealCardItem(
                icon = {
                    when (it) {
                        0 -> IcBreakfast3D(contentDescription = null, modifier = Modifier.size(32.dp))
                        1 -> IcLunch3D(contentDescription = null, modifier = Modifier.size(32.dp))
                        2 -> IcDinner3D(contentDescription = null, modifier = Modifier.size(32.dp))
                        else -> IcBreakfast3D(contentDescription = null)
                    }
                },
                content = when (it) {
                    0 -> state.meal?.breakfast ?: stringResource(id = R.string.desc_empty_breakfast)
                    1 -> state.meal?.lunch ?: stringResource(id = R.string.desc_empty_lunch)
                    2 -> state.meal?.dinner ?: stringResource(id = R.string.desc_empty_dinner)
                    else -> state.meal?.dinner ?: stringResource(id = R.string.desc_empty_dinner)
                }
            )
        }
    }
}

@Composable
private fun getCurrentPageTitle(page: Int): String {
    val title = if (LocalDateTime.now().hour in 20..23) {
        when (page) {
            0 -> stringResource(id = R.string.title_home_tomorrow_breakfast)
            1 -> stringResource(id = R.string.title_home_tomorrow_lunch)
            2 -> stringResource(id = R.string.title_home_tomorrow_dinner)
            else -> stringResource(id = R.string.title_home_tomorrow_breakfast)
        }
    } else {
        when (page) {
            0 -> stringResource(id = R.string.title_home_breakfast)
            1 -> stringResource(id = R.string.title_home_lunch)
            2 -> stringResource(id = R.string.title_home_dinner)
            else -> stringResource(id = R.string.title_home_breakfast)
        }
    }
    return title
}

private data class ItemCardContent(
    val subTitle: String,
    val title: String,
    val icon: @Composable () -> Unit,
)
