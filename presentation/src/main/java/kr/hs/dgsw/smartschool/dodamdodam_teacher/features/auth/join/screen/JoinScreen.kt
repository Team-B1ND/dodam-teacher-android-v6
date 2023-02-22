package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMediumRoundedButton
import kr.hs.dgsw.smartschool.components.component.basic.input.DodamInput
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.mvi.JoinState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.vm.JoinViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import org.orbitmvi.orbit.compose.collectAsState

private val JOIN_FIELD_MARGIN = 32.dp

@Composable
fun JoinScreen(
    navController: NavController,
    joinViewModel: JoinViewModel = hiltViewModel(),
) {

    val scrollState = rememberScrollState()
    var currentPage by remember {
        mutableStateOf(0)
    }

    val joinState = joinViewModel.collectAsState().value

    Column(
        modifier = Modifier
            .background(color = DodamTheme.color.White)
            .fillMaxSize()
    ) {
        DodamAppBar(onStartIconClick = {
            if (currentPage == 0) {
                navController.navigate(NavGroup.Auth.LOGIN) {
                    popUpTo(NavGroup.Auth.JOIN) {
                        inclusive = true
                    }
                }
            } else {
                currentPage = 0
            }
        })

        Column(
            modifier = Modifier
                .padding(horizontal = DodamDimen.ScreenSidePadding)
                .fillMaxWidth()
                .weight(1f)
        ) {

            Spacer(modifier = Modifier.height(JOIN_FIELD_MARGIN / 2))

            Title3(text = stringResource(id = R.string.label_join))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                AnimatedVisibility(visible = currentPage == 0) {
                    JoinFirst(joinViewModel = joinViewModel, state = joinState)
                }
                AnimatedVisibility(visible = currentPage == 1) {
                    JoinSecond(joinViewModel = joinViewModel, state = joinState)
                }
            }
        }

        Box(modifier = Modifier.padding(all = DodamDimen.ScreenSidePadding)) {
            DodamMaxWidthButton(
                text = if (currentPage == 0)
                    stringResource(id = R.string.label_next)
                else
                    stringResource(id = R.string.label_join)
            ) {
                if (currentPage == 0)
                    currentPage = 1
                else
                    joinViewModel.join(
                        email = joinState.email,
                        id = joinState.id,
                        name = joinState.name,
                        phone = joinState.phone,
                        position = joinState.position,
                        pw = joinState.pw,
                        tel = joinState.tel,
                    )
            }
        }
    }
}

@Composable
private fun JoinFirst(joinViewModel: JoinViewModel, state: JoinState) {

    var idText by remember {
        mutableStateOf(state.id)
    }

    var pwText by remember {
        mutableStateOf(state.pw)
    }

    var checkedPwText by remember {
        mutableStateOf(state.checkedPw)
    }

    var nameText by remember {
        mutableStateOf(state.name)
    }

    var phoneText by remember {
         mutableStateOf(state.phone)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(JOIN_FIELD_MARGIN)
    ) {
        DodamInput(
            value = idText,
            onValueChange = {
                if (it.length <= 20) {
                    idText = it
                    joinViewModel.inputId(it)
                }
            },
            isError = idText.length in 1..4,
            errorMessage = stringResource(id = R.string.desc_id_login),
            modifier = Modifier
                .padding(top = JOIN_FIELD_MARGIN)
                .fillMaxWidth(),
            hint = stringResource(id = R.string.hint_id),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )
        )

        DodamInput(
            value = pwText,
            onValueChange = {
                if (it.length <= 20) {
                    pwText = it
                    joinViewModel.inputPw(it)
                }
            },
            isError = pwText.length in 1..6,
            errorMessage = stringResource(id = R.string.desc_pw_join),
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_pw),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        DodamInput(
            value = checkedPwText,
            onValueChange = {
                if (it.length <= 20) {
                    checkedPwText = it
                    joinViewModel.inputCheckedPw(it)
                }
            },
            isError = checkedPwText.isNotEmpty() && checkedPwText != pwText,
            errorMessage = stringResource(id = R.string.desc_checkedPw_join),
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_checkedPw),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        DodamInput(
            value = nameText,
            onValueChange = {
                if (it.length <= 20) {
                    nameText = it
                    joinViewModel.inputName(it)
                }
            },
            isError = nameText.length > 20,
            errorMessage = stringResource(id = R.string.desc_name_join),
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_name),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
        )

        DodamInput(
            value = phoneText,
            onValueChange = {
                if (it.length <= 11) {
                    phoneText = it
                    joinViewModel.inputPhone(it)
                }
            },
            isError = phoneText.length > 11,
            errorMessage = stringResource(id = R.string.desc_phone_join),
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_phone),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
        )
    }
}

@Composable
private fun JoinSecond(joinViewModel: JoinViewModel, state: JoinState) {

    var emailText by remember {
        mutableStateOf(state.email)
    }

    var telText by remember {
        mutableStateOf(state.tel)
    }

    var positionText by remember {
        mutableStateOf(state.position)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(JOIN_FIELD_MARGIN)
    ) {
        DodamInput(
            value = emailText,
            onValueChange = {
                if (it.length <= 30) {
                    emailText = it
                    joinViewModel.inputEmail(it)
                }
            },
            isError = emailText.length > 30,
            errorMessage = stringResource(id = R.string.desc_email_join),
            modifier = Modifier
                .padding(top = JOIN_FIELD_MARGIN)
                .fillMaxWidth(),
            hint = stringResource(id = R.string.hint_email),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
        )

        DodamInput(
            value = telText,
            onValueChange = {
                if (it.length <= 20) {
                    telText = it
                    joinViewModel.inputTel(it)
                }
            },
            isError = telText.length > 20,
            errorMessage = stringResource(id = R.string.desc_tel_join),
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_tel),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
        )

        DodamInput(
            value = positionText,
            onValueChange = {
                if (it.length <= 20) {
                    positionText = it
                    joinViewModel.inputPosition(it)
                }
            },
            isError = positionText.length > 20,
            errorMessage = stringResource(id = R.string.desc_position_join),
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_position),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
        )
    }
}
