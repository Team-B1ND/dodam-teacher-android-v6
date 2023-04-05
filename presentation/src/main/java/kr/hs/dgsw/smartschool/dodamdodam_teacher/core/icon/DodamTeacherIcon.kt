package kr.hs.dgsw.smartschool.dodamdodam_teacher.core.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kr.hs.dgsw.smartschool.components.foundation.Icon
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R

@Composable
fun IcLocation(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_location),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IcOut(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_out),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IcBurger(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_burger),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IcRefresh(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_refresh),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IcGrinningFace3D(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_grinning_face_3d),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IcSleepingFace3D(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_sleeping_face_3d),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IcThinkingFace3D(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_thinking_face_3d),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IcCalendar3D(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_calendar_3d),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}
