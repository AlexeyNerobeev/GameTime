package com.example.gametime.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gametime.R
import com.example.uikit.Buttons.MainButton
import com.example.uikit.Theme

//26.03.2026
//Алексей
//метод для отображения successfulmessage
@Composable
fun SuccessfulMessage(
    onDismiss: () -> Unit,
    title: String,
    text: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFA5075).copy(alpha = 0.7f))
    ) {
        Column(
            modifier = Modifier
                .padding(top = 68.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier
                    .padding(end = 44.dp)
                    .align(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Icon(
                painter = painterResource(R.drawable.success_icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(top = 59.dp)
                    .size(138.dp)
            )
            Text(
                text = title,
                fontFamily = FontFamily(
                    Font(
                        resId = com.example.uikit.R.font.poppins_bold
                    )
                ),
                fontWeight = FontWeight(700),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 38.dp)
            )
            Text(text = text,
                style = Theme.typography.captionRegular,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 21.dp))
            MainButton(
                modifier = Modifier.padding(top = 33.dp),
                text = buttonText
            ) {
                onButtonClick()
            }
        }
    }
}