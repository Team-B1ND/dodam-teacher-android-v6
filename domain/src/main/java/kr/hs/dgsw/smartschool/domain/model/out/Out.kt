package kr.hs.dgsw.smartschool.domain.model.out

data class Out(
    val outgoings: List<OutItem>,
    val outsleepings: List<OutItem>,
)
