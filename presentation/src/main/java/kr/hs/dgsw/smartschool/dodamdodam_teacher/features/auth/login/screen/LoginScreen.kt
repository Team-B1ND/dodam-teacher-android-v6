package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.hs.dgsw.smartschool.components.component.basic.button.DodamMaxWidthButton
import kr.hs.dgsw.smartschool.components.component.basic.input.DodamInput
import kr.hs.dgsw.smartschool.components.component.basic.toggle.DodamCheckBox
import kr.hs.dgsw.smartschool.components.modifier.dodamClickable
import kr.hs.dgsw.smartschool.components.theme.Body2
import kr.hs.dgsw.smartschool.components.theme.Body3
import kr.hs.dgsw.smartschool.components.theme.DodamTheme
import kr.hs.dgsw.smartschool.components.theme.IcRightArrow
import kr.hs.dgsw.smartschool.components.theme.Title3
import kr.hs.dgsw.smartschool.components.utlis.DodamDimen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.R
import kr.hs.dgsw.smartschool.dodamdodam_teacher.core.component.loading.LoadInFullScreen
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi.LoginSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.vm.LoginViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.navigation.NavGroup
import kr.hs.dgsw.smartschool.dodamdodam_teacher.utils.shortToast
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val loginState = loginViewModel.container.stateFlow.collectAsState().value

    var idText by remember {
        mutableStateOf(loginState.id)
    }

    var pwText by remember {
        mutableStateOf(loginState.pw)
    }

    loginViewModel.collectSideEffect {
        when (it) {
            is LoginSideEffect.NavigateToHomeScreen -> {
                navController.navigate(NavGroup.Main.MAIN) {
                    popUpTo(NavGroup.Auth.LOGIN) {
                        inclusive = true
                    }
                }
            }
            is LoginSideEffect.ToastLoginErrorMessage -> {
                Toast.makeText(context, it.errMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    if (loginState.isLoading)
        LoadInFullScreen()
    else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DodamTheme.color.White)
                .padding(DodamDimen.ScreenSidePadding)
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                LogoInLogin(
                    modifier = Modifier.align(Alignment.Center),
                    label = stringResource(id = R.string.app_name)
                )
            }

            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Title3(
                        text = stringResource(id = R.string.title_login)
                    )

                    DodamInput(
                        value = idText,
                        onValueChange = {
                            if (it.length <= 20) {
                                idText = it
                                loginViewModel.inputId(it)
                            }
                        },
                        isError = idText.length in 1..4,
                        errorMessage = stringResource(id = R.string.desc_id_login),
                        modifier = Modifier.fillMaxWidth(),
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
                                loginViewModel.inputPw(it)
                            }
                        },
                        isError = pwText.length in 1..6,
                        errorMessage = stringResource(id = R.string.desc_pw_login),
                        modifier = Modifier.fillMaxWidth(),
                        hint = stringResource(id = R.string.hint_pw),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DodamCheckBox(
                            isChecked = loginState.enableAutoLogin,
                            boxSize = 14.dp
                        ) { isChecked ->
                            loginViewModel.changeChecked(isChecked)
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Body3(text = stringResource(id = R.string.desc_auto_login))
                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DodamMaxWidthButton(
                        text = stringResource(id = R.string.label_login)
                    ) {
                        if (checkLoginData(context, loginState.id, loginState.pw))
                            loginViewModel.login(loginState.id, loginState.pw, loginState.enableAutoLogin)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Body2(
                            text = stringResource(id = R.string.desc_navigate_join),
                            textColor = DodamTheme.color.Gray500,
                            onClick = { navigateToJoinScreen(navController) },
                            rippleEnabled = false,
                        )
                        IcRightArrow(
                            contentDescription = null,
                            tint = DodamTheme.color.Gray500,
                            modifier = Modifier
                                .size(14.dp)
                                .dodamClickable(rippleEnable = false) {
                                    navigateToJoinScreen(
                                        navController
                                    )
                                }
                        )
                    }
                }
            }
        }
}

private fun checkLoginData(
    context: Context,
    id: String,
    pw: String,
): Boolean {
    if (id.isEmpty() || pw.isEmpty()) {
        context.shortToast(context.getString(R.string.warn_empty_field))
        return false
    }
    if ((id.length in 5..20).not()) {
        context.shortToast(context.getString(R.string.desc_id_login))
        return false
    }

    if ((pw.length in 7..20).not()) {
        context.shortToast(context.getString(R.string.desc_pw_login))
        return false
    }
    return true
}

private fun navigateToJoinScreen(navController: NavController) {
    navController.navigate(NavGroup.Auth.JOIN)
}

@Composable
private fun LogoInLogin(
    modifier: Modifier = Modifier,
    label: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(62.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.logo_b1nd),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Title3(text = label)
    }
}

@Preview
@Composable
private fun PreviewLogoInLogin() {
    LogoInLogin(label = "도담도담 T")
}
