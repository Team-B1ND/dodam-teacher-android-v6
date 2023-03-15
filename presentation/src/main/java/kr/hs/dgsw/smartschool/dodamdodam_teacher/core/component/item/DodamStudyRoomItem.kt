package kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamSmallRoundedButton
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.theme.Body1
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus

@Composable
internal fun DodamStudyRoomItem(
    member: Member,
    place : String?,
    status: StudyRoomStatus,
    modifier: Modifier = Modifier,
    checkAction : () -> Unit,
    ctrlAction: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .surface(RoundedCornerShape(100.dp), DodamTheme.color.Background)
            .height(44.dp)
            .clickable { ctrlAction() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Avatar(
            modifier = Modifier
                .padding(start = DodamDimen.ScreenSidePadding)
                .size(30.dp),
            link = member.profileImage ?: "",
            failureIconColor = DodamTheme.color.Gray400,
            failureIconSize = 15.dp,
            backgroundColor = DodamTheme.color.White,
        )

        Spacer(modifier = Modifier.width(11.dp))
        Body1(text = member.name)
        Spacer(modifier = Modifier.width(11.dp))
        Label1(
            text = place ?: "미신청",
            modifier = Modifier.weight(1f)
        )
        if (!place.isNullOrEmpty()) {
            DodamSmallRoundedButton(
                modifier = Modifier
                    .background(
                        if (status == StudyRoomStatus.CHECKED) DodamColor.Check
                        else DodamColor.MainColor
                    )
                    .width(50.dp)
                    .height(30.dp),
                text = "확인",
                onClick = {
                    checkAction()
                }
            )

        }
    }
}
