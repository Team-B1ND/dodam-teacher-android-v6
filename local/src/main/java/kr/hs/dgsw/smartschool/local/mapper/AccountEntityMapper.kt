package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.account.Account
import kr.hs.dgsw.smartschool.local.entity.account.AccountEntity

internal fun AccountEntity?.toModel(): Account =
    Account(
        id = this?.id,
        pw = this?.pw
    )