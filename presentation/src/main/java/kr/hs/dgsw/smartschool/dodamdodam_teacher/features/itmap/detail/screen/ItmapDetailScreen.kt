package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.basic.badge.DodamBadge
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.Body3
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.theme.Label2
import kr.hs.dgsw.smartschool.components.theme.Title2
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.mvi.ItmapDetailSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.vm.ItmapDetailViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import kr.hs.dgsw.smartschool.domain.model.itmap.CompanyUser
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ItmapDetailScreen(
    companyId: Int,
    navController: NavController,
    itmapDetailViewModel: ItmapDetailViewModel = hiltViewModel(),
) {

    itmapDetailViewModel.getCompany(companyId)

    val state = itmapDetailViewModel.collectAsState().value
    val context = LocalContext.current

    itmapDetailViewModel.collectSideEffect {
        when (it) {
            is ItmapDetailSideEffect.ShowException -> {
                if (it.exception.message == context.getString(R.string.text_session)) {
                    navController.navigate(NavGroup.Auth.LOGIN) {
                        popUpTo(NavGroup.Feature.ITMAP_DETAIL) {
                            inclusive = true
                        }
                    }
                }
                context.shortToast(it.exception.message ?: context.getString(R.string.content_unknown_exception))
            }
        }
    }

    if (state.loading)
        LoadInFullScreen()
    else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DodamTheme.color.White),
        ) {
            DodamAppBar(
                onStartIconClick = { navController.popBackStack() }
            )

            state.company?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (it.symbolLogo.isEmpty())
                        Avatar(
                            modifier = Modifier.padding(DodamDimen.ScreenSidePadding),
                            name = it.name,
                            size = 50.dp,
                        )
                    else
                        Avatar(
                            modifier = Modifier.padding(DodamDimen.ScreenSidePadding),
                            link = it.symbolLogo,
                            size = 50.dp,
                        )

                    Column {
                        Title2(text = it.name)
                        Spacer(modifier = Modifier.height(3.dp))
                        Label2(text = it.address, textColor = DodamTheme.color.Gray500)
                    }
                }
                Spacer(modifier = Modifier.height(DodamDimen.ScreenSidePadding))

                it.companyUser?.let { companyUsers ->
                    Label1(
                        text = "${companyUsers.size}명의 졸업생",
                        textColor = DodamTheme.color.Gray500,
                        modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding)
                    )

                    LazyColumn(
                        modifier = Modifier.padding(horizontal = DodamDimen.ScreenSidePadding),
                        contentPadding = PaddingValues(vertical = DodamDimen.ScreenSidePadding),
                        verticalArrangement = Arrangement.spacedBy(DodamDimen.ScreenSidePadding)
                    ) {
                        items(companyUsers) { companyUser ->
                            CompanyUserItem(companyUser)
                        }
                    }
                }
            }
        }
}

@Composable
private fun CompanyUserItem(companyUser: CompanyUser) {

    val context = LocalContext.current
    val githubIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${companyUser.githubId}")) }

    Row {
        Avatar(
            shape = DodamTheme.shape.medium,
            size = 90.dp,
            link = companyUser.image,
        )
        Spacer(modifier = Modifier.width(DodamDimen.ScreenSidePadding))
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            DodamBadge(text = companyUser.position, background = DodamColor.FeatureColor.ItMapColor, textColor = DodamTheme.color.White)
            Label1(text = "${companyUser.generation}기 ${companyUser.name}")
            Body3(text = companyUser.info, textColor = DodamTheme.color.Gray500)
            Body3(
                text = "github.com/${companyUser.githubId}",
                textColor = DodamTheme.color.MainColor600,
                onClick = { context.startActivity(githubIntent) }
            )
        }
    }
}
