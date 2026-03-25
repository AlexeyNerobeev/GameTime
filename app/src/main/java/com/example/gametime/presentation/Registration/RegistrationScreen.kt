package com.example.gametime.presentation.Registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
//метод для отображения экрана регистрации. Принимает контроллер навигации и viewmodel
@Composable
fun RegistrationScreen(navController: NavController, vm: RegistrationVM = hiltViewModel()) {
    val state = vm.state.value

    LaunchedEffect(key1 = state.isComplete) {
        if(state.isComplete){
            navController.navigate(Navigation.Landing)
        }
    }

    val nameTfList = listOf(
        InputTFData(
            modifier = Modifier
                .padding(top = 37.dp)
                .fillMaxWidth(),
            value = state.fullName,
            onValueChange = { vm.onEvent(RegistrationEvent.EnteredFullName(it)) },
            placeholder = "Full Name",
            withTrailingIcon = false
        ),
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = state.userName,
            onValueChange = { vm.onEvent(RegistrationEvent.EnteredUserName(it)) },
            placeholder = "User Name",
            withTrailingIcon = false
        )
    )
    val loginTfList = listOf(
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = state.email,
            onValueChange = { vm.onEvent(RegistrationEvent.EnteredEmail(it)) },
            placeholder = "Email",
            withTrailingIcon = false
        ),
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = state.password,
            onValueChange = { vm.onEvent(RegistrationEvent.EnteredPassword(it)) },
            placeholder = "Password",
            withTrailingIcon = true
        ),
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            value = state.confirmPassword,
            onValueChange = { vm.onEvent(RegistrationEvent.EnteredConfirmPassword(it)) },
            placeholder = "Confirm Password",
            withTrailingIcon = true
        )
    )
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
                        painter = painterResource(R.drawable.registration_image),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 21.dp)
                            .padding(horizontal = 27.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .padding(start = 52.dp)
                            .padding(end = 60.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Create Account",
                            fontFamily = FontFamily(
                                Font(
                                    resId = com.example.uikit.R.font.poppins_bold
                                )
                            ),
                            color = Color(0xFFFA5075),
                            fontWeight = FontWeight(700),
                            fontSize = 22.sp
                        )
                        Text(
                            text = "Hi, kindly fill in the form to proceed \n" +
                                    "combat",
                            style = Theme.typography.caption2Regular,
                            color = Black,
                            modifier = Modifier
                                .padding(top = 12.dp)
                        )
                        nameTfList.forEach {
                            InputTF(
                                modifier = it.modifier,
                                value = it.value,
                                onValueChange = it.onValueChange,
                                placeholder = it.placeholder,
                                withTrailingIcon = it.withTrailingIcon
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 24.dp)
                        ) {
                            InputTF(
                                modifier = Modifier.width(35.dp),
                                value = "+234",
                                onValueChange = {},
                                placeholder = "+234",
                                withTrailingIcon = false
                            )
                            InputTF(
                                modifier = Modifier
                                    .padding(start = 18.dp)
                                    .fillMaxWidth(),
                                value = state.phone,
                                onValueChange = { vm.onEvent(RegistrationEvent.EnteredPhone(it)) },
                                placeholder = "Your Phone",
                                withTrailingIcon = false
                            )
                        }
                        loginTfList.forEach {
                            InputTF(
                                modifier = it.modifier,
                                value = it.value,
                                onValueChange = it.onValueChange,
                                placeholder = it.placeholder,
                                withTrailingIcon = it.withTrailingIcon
                            )
                        }
                        MainButton(
                            text = "Create Account",
                            onCLick = {
                                vm.onEvent(RegistrationEvent.CreateAccount)
                            },
                            modifier = Modifier
                                .padding(top = 44.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Column(
                            modifier = Modifier
                                .padding(top = 106.dp)
                                .padding(end = 5.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Already have an account?",
                                style = Theme.typography.caption2Regular,
                                color = Black,
                                textAlign = TextAlign.Center)
                            Text(text = "Login",
                                color = Color(0xFFFA5075),
                                style = Theme.typography.caption2Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .clickable{
                                        navController.navigate(Navigation.SignIn)
                                    })
                        }
                    }
                }
            }
        }
    }
}