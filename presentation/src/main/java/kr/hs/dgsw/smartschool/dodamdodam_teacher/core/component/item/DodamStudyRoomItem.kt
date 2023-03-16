package kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.theme.Body1
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.DodamColor
import kr.hs.dgsw.smartschool.components.theme.DodamShape
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label2
import kr.hs.dgsw.smartschool.components.theme.Label3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus

@Composable
internal fun DodamStudyRoomItem(
    member: Member,
    place: String?,
    classroom: String?,
    status: StudyRoomStatus,
    modifier: Modifier = Modifier,
    checkAction: () -> Unit,
    ctrlAction: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { ctrlAction() }
            .background(DodamColor.Background),
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
        Body1(
            text = member.name,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Label3(
            text = classroom ?: "알 수 없음",
        )
        Spacer(modifier = Modifier.width(11.dp))
        Label2(
            text = place ?: "미신청",
            textColor = DodamColor.MainColor,
            modifier = Modifier.weight(0.8f)
        )
        if (!place.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .clickable { checkAction() }
                    .surface(
                        DodamShape().medium,
                        when (status) {
                            StudyRoomStatus.CHECKED -> DodamColor.Check
                            StudyRoomStatus.PENDING -> DodamColor.MainColor
                        }
                    )
                    .padding(10.dp)
                    .width(40.dp)
                    .height(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Body2(
                    text =
                    when (status) {
                        StudyRoomStatus.CHECKED -> "확인됨"
                        StudyRoomStatus.PENDING -> "확인"
                    },
                    textColor = DodamColor.White
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .clickable { checkAction() }
                    .surface(
                        DodamShape().medium,
                        DodamColor.MainColor
                    )
                    .padding(10.dp)
                    .width(40.dp)
                    .height(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Body2(text = "신청", textColor = DodamColor.White)
            }
        }
    }
}
