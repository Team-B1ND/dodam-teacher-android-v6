package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.input.DodamInput
import kr.hs.dgsw.smartschool.components.component.basic.toggle.DodamCheckBox
import kr.hs.dgsw.smartschool.components.component.set.appbar.DodamAppBar
import kr.hs.dgsw.smartschool.components.theme.Body3
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.mvi.JoinState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.vm.JoinViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectAsState

private val JOIN_FIELD_MARGIN = 32.dp

@Composable
fun JoinScreen(
    navController: NavController,
    joinViewModel: JoinViewModel = hiltViewModel(),
) {

    val scrollState = rememberScrollState()
    val joinState = joinViewModel.collectAsState().value
    val context = LocalContext.current

    BackHandler(joinState.currentPage == 1) {
        joinViewModel.setCurrentPage(0)
    }

    Column(
        modifier = Modifier
            .background(color = DodamTheme.color.White)
            .fillMaxSize()
    ) {
        DodamAppBar(onStartIconClick = {
            if (joinState.currentPage == 0) {
                navController.popBackStack()
            } else {
                joinViewModel.setCurrentPage(0)
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
                AnimatedVisibility(visible = joinState.currentPage == 0) {
                    JoinFirst(joinViewModel = joinViewModel, state = joinState)
                }
                AnimatedVisibility(visible = joinState.currentPage == 1) {
                    JoinSecond(joinViewModel = joinViewModel, state = joinState)
                }
            }
        }

        Box(modifier = Modifier.padding(all = DodamDimen.ScreenSidePadding)) {
            DodamMaxWidthButton(
                text = if (joinState.currentPage == 0)
                    stringResource(id = R.string.label_next)
                else
                    stringResource(id = R.string.label_join)
            ) {
                if (joinState.currentPage == 0)
                    joinViewModel.setCurrentPage(1)
                else {
                    if (checkJoinData(
                            context = context,
                            email = joinState.email,
                            id = joinState.id,
                            name = joinState.name,
                            phone = joinState.phone,
                            position = joinState.position,
                            pw = joinState.pw,
                            checkedPw = joinState.checkedPw,
                            tel = joinState.tel,
                            checkTerms = joinState.checkTerms
                        )
                    )
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

    val context = LocalContext.current

    var emailText by remember {
        mutableStateOf(state.email)
    }

    var telText by remember {
        mutableStateOf(state.tel)
    }

    var positionText by remember {
        mutableStateOf(state.position)
    }

    val personalInfoUrl = stringResource(id = R.string.url_personal_info)
    val serviceInfoUrl = stringResource(id = R.string.url_service_info)
    val personalInfo = remember { Intent(Intent.ACTION_VIEW, Uri.parse(personalInfoUrl)) }
    val serviceInfo = remember { Intent(Intent.ACTION_VIEW, Uri.parse(serviceInfoUrl)) }

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

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                DodamCheckBox(
                    isChecked = state.checkTerms
                ) {
                    joinViewModel.checkTerms(it)
                }
                Spacer(modifier = Modifier.width(4.dp))
                Body3(text = stringResource(id = R.string.text_check_terms))
            }
            Spacer(modifier = Modifier.height(6.dp))
            Body3(
                text = stringResource(id = R.string.text_private_term),
                textColor = DodamTheme.color.MainColor400,
                modifier = Modifier.padding(start = 16.dp),
                rippleEnabled = false,
                onClick = { context.startActivity(personalInfo) }
            )
            Spacer(modifier = Modifier.height(6.dp))
            Body3(
                text = stringResource(id = R.string.text_service_terms),
                textColor = DodamTheme.color.MainColor400,
                modifier = Modifier.padding(start = 16.dp),
                rippleEnabled = false,
                onClick = { context.startActivity(serviceInfo) }
            )
        }
    }
}

private fun checkJoinData(
    context: Context,
    email: String,
    id: String,
    name: String,
    phone: String,
    position: String,
    pw: String,
    checkedPw: String,
    tel: String,
    checkTerms: Boolean,
): Boolean {

    if (email.isEmpty() || id.isEmpty() || name.isEmpty() || phone.isEmpty() || position.isEmpty() || pw.isEmpty() || checkedPw.isEmpty() || tel.isEmpty()) {
        context.shortToast("빈 값이 없도록 입력해주세요")
        return false
    }

    if (pw != checkedPw) {
        context.shortToast("비밀번호가 일치하지 않아요")
        return false
    }

    if (checkTerms.not()) {
        context.shortToast("약관에 동의해주세요")
        return false
    }

    return true
}
