package kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.hs.dgsw.smartschool.components.component.basic.avatar.Avatar
import kr.hs.dgsw.smartschool.components.component.basic.surface
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Label1
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.domain.model.member.Member

@Composable
internal fun DodamStudentItem(
    members: List<Member>,
    findMemberId: Int?,
    modifier: Modifier = Modifier,
) {
    val member = members.find { it.student?.id == findMemberId }
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
            link = member?.profileImage ?: "",
            failureIconColor = DodamTheme.color.Gray400,
            failureIconSize = 15.dp,
            backgroundColor = DodamTheme.color.White,
        )

        Spacer(modifier = Modifier.width(11.dp))
        Label1(
            text = member?.student?.name ?: "",
            modifier = Modifier.weight(1f)
        )
    }
}
