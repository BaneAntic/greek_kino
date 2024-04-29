package com.greek.kino.base.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greek.kino.base.theme.HeaderGray
import com.greek.kino.base.theme.PrimaryOrange
import com.greek.kino.base.theme.SecondaryBlue
import com.greek.kino.base.theme.TextDarkGray
import com.greek.kino.base.theme.White

@Composable
fun NavigationText(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) = Text(
    text = text,
    modifier = modifier,
    color = if (isSelected) PrimaryOrange else White,
    fontSize = 14.sp,
)

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) = Text(
    modifier = modifier,
    text = text,
    color = Color.White,
    fontSize = 16.sp,
    textAlign = textAlign,
)

@Composable
fun SubHeaderText(
    text: String,
    modifier: Modifier = Modifier,
) = Text(
    text = text,
    modifier =
        modifier
            .background(HeaderGray)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    color = Color.White,
    fontSize = 16.sp,
    textAlign = TextAlign.Center,
)

@Composable
fun RegularText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) = Text(
    text = text,
    modifier = modifier,
    color = Color.White,
    fontSize = 15.sp,
    textAlign = textAlign,
)

@Composable
fun KinoText(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
) = Text(
    text = text,
    modifier = modifier,
    color = if (isSelected) Color.White else TextDarkGray,
    fontSize = 12.sp,
    textAlign = TextAlign.Center,
)

@Composable
fun ButtonText(
    text: String,
    modifier: Modifier = Modifier,
) = Text(
    text = text,
    modifier =
        modifier
            .background(SecondaryBlue)
            // use as padding after background is set
            .padding(8.dp),
    color = Color.White,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
)

@Composable
fun RoundedTextView(
    text: String,
    modifier: Modifier = Modifier,
) = Text(
    text = text,
    modifier =
        modifier
            .background(Color.Transparent, shape = CircleShape)
            .border(
                width = 1.dp,
                color = PrimaryOrange,
                shape = RoundedCornerShape(32.dp),
            )
            .padding(12.dp),
    color = Color.White,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
)

@Composable
fun RoundedCornerButton(
    modifier: Modifier = Modifier,
    title: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier =
            modifier
                .border(
                    width = 1.dp,
                    color = PrimaryOrange,
                    shape = RoundedCornerShape(32.dp),
                )
                .background(
                    color = if (isEnabled) PrimaryOrange else Color.Transparent,
                    shape = RoundedCornerShape(32.dp),
                ),
        shape = RoundedCornerShape(32.dp),
        onClick = onClick,
        elevation =
            ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                hoveredElevation = 0.dp,
            ),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                contentColor = Color.White,
                disabledContentColor = Color.Gray,
            ),
        enabled = isEnabled,
    ) {
        Text(text = title, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Medium)
    }
}
