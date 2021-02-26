package com.hdesrosiers.rvdrecipeapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.hdesrosiers.rvdrecipeapp.R

private  val Quicksand = fontFamily(
        font(
                resId = R.font.quicksand_light,
                weight = FontWeight.Light //300
        ),
        font(
                resId = R.font.quicksand_regular,
                weight = FontWeight.Normal //400
        ),
        font(
                resId = R.font.quicksand_medium,
                weight = FontWeight.Medium //500
        ),
        font(
                resId = R.font.quicksand_bold,
                weight = FontWeight.Bold //600
        ),
)

val QuicksandTypography = Typography(
        h1 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp
        ),
        h2 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
        ),
        h3 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
        ),
        h4 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
        ),
        h5 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
        ),
        h6 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
        ),
        subtitle1 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
        ),
        body1 = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        body2 = TextStyle(
                fontFamily = Quicksand,
                fontSize = 14.sp
        ),
        button = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
        ),
        caption = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
        ),
        overline = TextStyle(
                fontFamily = Quicksand,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
        )
)

// Template defaults

//// Set of Material typography styles to start with
//val typography = Typography(
//        body1 = TextStyle(
//                fontFamily = FontFamily.Default,
//                fontWeight = FontWeight.Normal,
//                fontSize = 16.sp
//        )
//        /* Other default text styles to override
//    button = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.W500,
//        fontSize = 14.sp
//    ),
//    caption = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )
//    */
//)