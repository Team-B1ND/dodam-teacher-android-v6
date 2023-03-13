package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.Divider
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.basic.button.ButtonType
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMediumRoundedButton
import kr.hs.dgsw.smartschool.components.component.organization.prompt.DodamPrompt
import kr.hs.dgsw.smartschool.components.foundation.Text
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.IcRightArrow
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.theme.Title1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.common.DodamTeacherDimens
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi.EtcSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.vm.EtcViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun EtcScreen(
    etcViewModel: EtcViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val etcState = etcViewModel.container.stateFlow.collectAsState().value

    etcViewModel.collectSideEffect {
        when (it) {
            is EtcSideEffect.ShowException -> {
                Log.e("EtcLog", it.throwable.stackTraceToString())
                Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
            }
            is EtcSideEffect.SuccessLogout -> {
                navController.navigate(NavGroup.Auth.LOGIN) {
                    popUpTo(NavGroup.Main.MAIN) {
                        inclusive = true
                    }
                }
            }
        }
    }

    if (etcState.showPrompt)
        DodamPrompt(
            title = stringResource(id = R.string.desc_logout_main),
            description = stringResource(id = R.string.desc_logout_sub),
            primaryButton = {
                DodamMediumRoundedButton(
                    text = stringResource(id = R.string.label_logout),
                    type = ButtonType.PrimaryVariant,
                ) {
                    etcViewModel.logout()
                    etcViewModel.updateShowPrompt(false)
                }
            },
            secondaryButton = {
                DodamMediumRoundedButton(
                    text = stringResource(id = R.string.label_cancel),
                    type = ButtonType.Danger,
                ) {
                    etcViewModel.updateShowPrompt(false)
                }
            },
            onDismiss = { etcViewModel.updateShowPrompt(false) }
        )

    if (etcState.isLoading)
        LoadInFullScreen()
    else
        Column(
            modifier = Modifier
                .background(DodamTheme.color.White)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(DodamTeacherDimens.DefaultMainScreenTitleSpace))

            Title1(
                text = stringResource(id = R.string.label_etc),
                modifier = Modifier.padding(start = DodamDimen.ScreenSidePadding),
            )

            val versionInfo = stringResource(id = R.string.url_version_info)
            val serviceInfoUrl = stringResource(id = R.string.url_service_info)
            val privateInfoUrl = stringResource(id = R.string.url_personal_info)

            val infoList = listOf(
                InfoItemParam(
                    title = stringResource(id = R.string.label_version_info),
                    action = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(versionInfo))) }
                ),
            )

            val termList = listOf(
                InfoItemParam(
                    title = stringResource(id = R.string.label_service_term),
                    action = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(serviceInfoUrl))) }
                ),
                InfoItemParam(
                    title = stringResource(id = R.string.label_private_term),
                    action = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(privateInfoUrl))) }
                ),
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(top = DodamDimen.ScreenSidePadding, bottom = DodamTeacherDimens.BottomNavHeight + DodamDimen.ScreenSidePadding)
            ) {
                etcState.myInfo?.let {
                    item {
                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = DodamDimen.ScreenSidePadding)
                                    .dodamClickable(rippleEnable = false) {
                                        etcViewModel.updateShowPrompt(true)
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                EtcMemberItem(myInfo = etcState.myInfo)
                            }

                            Divider(
                                thickness = 14.dp,
                                color = DodamTheme.color.Background
                            )
                        }
                    }
                }
                items(infoList) {
                    InfoItem(
                        infoItemParam = InfoItemParam(
                            title = it.title,
                            action = it.action,
                        )
                    )
                }

                item {
                    Divider(
                        thickness = 14.dp,
                        color = DodamTheme.color.Background
                    )
                }

                items(termList) {
                    InfoItem(
                        infoItemParam = InfoItemParam(
                            title = it.title,
                            action = it.action,
                        )
                    )
                }
            }
        }
}

@Composable
private fun RowScope.EtcMemberItem(
    myInfo: Teacher
) {
    Avatar(
        link = myInfo.member.profileImage ?: "",
        backgroundColor = DodamTheme.color.Background,
        size = 36.dp,
        failureIconSize = 18.dp,
        failureIconColor = DodamTheme.color.Gray400,
        modifier = Modifier.padding(vertical = DodamDimen.ScreenSidePadding)
    )
    Spacer(modifier = Modifier.width(DodamDimen.ScreenSidePadding))
    Text(
        text = myInfo.member.name,
        color = DodamTheme.color.Black,
        style = DodamTheme.typography.label1.copy(
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = Modifier.weight(1f)
    )

    Label1(
        text = stringResource(id = R.string.label_logout),
        textColor = DodamTheme.color.Gray500,
    )

    Spacer(modifier = Modifier.width(DodamDimen.ScreenSidePadding))

    IcRightArrow(
        contentDescription = null,
        modifier = Modifier.size(14.dp),
        tint = DodamTheme.color.Gray400,
    )
}

@Composable
private fun InfoItem(
    infoItemParam: InfoItemParam,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(DodamDimen.ScreenSidePadding)
            .dodamClickable(rippleEnable = false) {
                infoItemParam.action()
            }
    ) {
        Text(
            text = infoItemParam.title,
            color = DodamTheme.color.Gray600,
            style = DodamTheme.typography.label1.copy(
                fontSize = 15.sp,
            ),
            modifier = Modifier.weight(1f),
        )

        IcRightArrow(
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = DodamTheme.color.Gray400,
        )
    }
}

private data class InfoItemParam(
    val title: String,
    val action: () -> Unit,
)
