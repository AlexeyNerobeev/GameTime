package com.example.gametime.presentation.SignIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.Navigation
import com.example.gametime.R
import com.example.gametime.common.InputTFData
import com.example.uikit.Black
import com.example.uikit.Buttons.MainButton
import com.example.uikit.InputTF
import com.example.uikit.Theme

//25.03.2026
//Алексей
//метод для отображения экрана авторизации. Принимает контроллер навигации и viewmodel
@Composable
fun SignInScreen(navController: NavController, vm: SignInVM = hiltViewModel()) {
    val state = vm.state.value
    val tfList = listOf(
        InputTFData(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth(),
            value = state.email,
            onValueChange = { vm.onEvent(SignInEvent.EnteredEmail(it)) },
            placeholder = "Email",
            withTrailingIcon = false
        ),
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = state.password,
            onValueChange = { vm.onEvent(SignInEvent.EnteredPassword(it)) },
            placeholder = "Password",
            withTrailingIcon = true
        )
    )
    LaunchedEffect(key1 = state.isComplete) {
        if (state.isComplete) {
            navController.navigate(Navigation.Landing)
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Image(
                        painter = painterResource(R.drawable.sigin_image),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 51.dp)
                            .padding(end = 52.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 41.dp)
                            .padding(start = 52.dp)
                            .padding(end = 60.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Welcome Back!",
                            color = Color(0xFFFA5075),
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(
                                Font(
                                    resId = com.example.uikit.R.font.poppins_bold
                                )
                            ),
                            fontSize = 22.sp
                        )
                        Text(
                            text = "Hi, Kindly login to continue battle",
                            color = Black,
                            style = Theme.typography.caption2Regular,
                            modifier = Modifier
                                .padding(top = 12.dp)
                        )
                        tfList.forEach {
                            InputTF(
                                modifier = it.modifier,
                                value = it.value,
                                onValueChange = it.onValueChange,
                                placeholder = it.placeholder,
                                withTrailingIcon = it.withTrailingIcon
                            )
                        }
                        Text(
                            text = "Forgot Password?",
                            style = Theme.typography.caption2Regular,
                            color = Black,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.End)
                        )
                        MainButton(
                            text = "Let’s Combat!",
                            onCLick = {
                                vm.onEvent(SignInEvent.SignIn)
                            },
                            modifier = Modifier
                                .padding(top = 27.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Column(
                            modifier = Modifier
                                .padding(top = 23.dp)
                                .padding(bottom = 9.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Connect With:",
                                style = Theme.typography.caption2Bold,
                                color = Color(0xFFFA5075),
                                textAlign = TextAlign.Center
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 7.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.google_icon),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                                Icon(
                                    painter = painterResource(R.drawable.facebook_icon),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .padding(start = 15.dp)
                                )
                            }
                            Text(
                                text = "Don’t have an account?",
                                style = Theme.typography.caption2Regular,
                                color = Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 18.dp)
                            )
                            Text(
                                text = "Create Account",
                                style = Theme.typography.caption2Bold,
                                color = Color(0xFFFA5075),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Navigation.Registration)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}