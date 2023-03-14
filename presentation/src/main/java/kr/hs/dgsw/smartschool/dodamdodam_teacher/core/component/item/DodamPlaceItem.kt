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
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomStatus

@Composable
internal fun DodamPlaceItem(
    //studyRoom: StudyRoom,
    place : Place,
    selected : Boolean,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    var isSelected = selected
    Row(
        modifier = modifier
            .fillMaxWidth()
            .surface(RoundedCornerShape(100.dp), DodamTheme.color.Background)
            .height(44.dp)
            .clickable { onClick }
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
            Body1(text = place.name)
    }
}
