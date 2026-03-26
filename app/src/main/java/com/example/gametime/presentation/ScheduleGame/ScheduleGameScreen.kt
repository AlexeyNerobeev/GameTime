package com.example.gametime.presentation.ScheduleGame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.Navigation
import com.example.gametime.R
import com.example.gametime.common.SuccessfulMessage
import com.example.uikit.Black
import com.example.uikit.Buttons.MainButton
import com.example.uikit.CustomCheckBox
import com.example.uikit.CustomDatePicker
import com.example.uikit.InputTF
import com.example.uikit.Theme

//26.03.2026
//Алексей
//метод для отображения экрана ScheduleGame
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleGameScreen(navController: NavController, vm: ScheduleVM = hiltViewModel()) {
    val state = vm.state.value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .blur(
                if (state.isComplete) {
                    14.dp
                } else {
                    0.dp
                }
            )
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 30.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 36.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Schedule Game",
                    color = Color(0xFFFA5075),
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily(
                        Font(
                            resId = com.example.uikit.R.font.poppins_bold
                        )
                    ),
                    fontSize = 22.sp
                )
                InputTF(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
                    value = state.name,
                    onValueChange = { vm.onEvent(ScheduleEvent.EnteredName(it)) },
                    placeholder = "Game Name",
                    withTrailingIcon = false
                )
                val expanded = remember { mutableStateOf(false) }
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .clickable {
                            expanded.value = true
                        }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = state.category.ifEmpty {
                                "Category"
                            },
                            style = Theme.typography.caption2Regular,
                            color = Black
                        )
                        Image(
                            painter = painterResource(com.example.uikit.R.drawable.dropdown_icon),
                            contentDescription = null
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(0xFFFA5075))
                    )
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = {
                            expanded.value = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Image")
                            },
                            onClick = {
                                vm.onEvent(ScheduleEvent.EnteredCategory("Image"))
                                expanded.value = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Circle")
                            },
                            onClick = {
                                vm.onEvent(ScheduleEvent.EnteredCategory("Circle"))
                                expanded.value = false
                            }
                        )
                    }
                }
                InputTF(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                    value = state.winningPrice,
                    onValueChange = { vm.onEvent(ScheduleEvent.EnteredPrice(it)) },
                    placeholder = "Winning Price",
                    withTrailingIcon = false
                )
                Row(
                    modifier = Modifier
                        .padding(top = 47.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val dateFrom = rememberDatePickerState()
                    CustomDatePicker(
                        title = "FROM",
                        value = dateFrom,
                        date = state.dateFrom
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                            }) {
                        Text(
                            text = "12:30 AM",
                            style = Theme.typography.caption2Regular,
                            color = Black
                        )
                        Icon(
                            painter = painterResource(com.example.uikit.R.drawable.dropdown_icon),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .padding(start = 16.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val dateTo = rememberDatePickerState()
                    CustomDatePicker(
                        title = "TO",
                        value = dateTo,
                        date = state.dateTo
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                            }) {
                        Text(
                            text = "12:30 AM",
                            style = Theme.typography.caption2Regular,
                            color = Black
                        )
                        Icon(
                            painter = painterResource(com.example.uikit.R.drawable.dropdown_icon),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .padding(start = 16.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputTF(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.description,
                        onValueChange = { vm.onEvent(ScheduleEvent.EnteredDescription(it)) },
                        placeholder = "Description",
                        withTrailingIcon = false
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 55.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "REMINDERS",
                            style = Theme.typography.caption2Regular,
                            color = Color(0xFFFA5075)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CustomCheckBox(
                                modifier = Modifier.size(14.dp),
                                value = state.notification,
                                onValueChange = { vm.onEvent(ScheduleEvent.ChangeNotification) }
                            )
                            Text(
                                text = "Notification",
                                color = Black,
                                style = Theme.typography.caption2Regular,
                                modifier = Modifier
                                    .padding(start = 9.dp)
                            )
                        }
                    }
                    MainButton(
                        modifier = Modifier.padding(top = 44.dp),
                        text = "Publish",
                        onCLick = {
                            vm.onEvent(ScheduleEvent.OnPublish)
                        }
                    )
                }
            }
        }
    }
    if (state.isComplete) {
        SuccessfulMessage(
            onDismiss = { navController.navigate(Navigation.Landing) },
            title = "Published\nSuccessful",
            text = "Wanna change/edit your\nscheduled game before it\nbegins?",
            buttonText = "Statistics",
            onButtonClick = { navController.navigate(Navigation.Landing) }
        )
    }
}