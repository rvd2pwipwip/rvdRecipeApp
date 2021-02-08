package com.hdesrosiers.rvdrecipeapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.font
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (progressBar, text) = createRefs()
            val guideline = createGuidelineFromTop(0.3f)

            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(progressBar) {
                        centerHorizontallyTo(parent)
                        top.linkTo(guideline)
                    },
                color = MaterialTheme.colors.primary
            )
            Text(
                modifier = Modifier
                    .constrainAs(text) {
                        centerHorizontallyTo(progressBar)
                        top.linkTo(progressBar.bottom)
                    }
                    .padding(top = 8.dp),
                text = "Loading...",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = TextUnit.Companion.Sp(15)
                )
            )
        }
    }
}

    /////   decoupled constraints example below


//    if (isDisplayed) {
//        // access screen properties
//        WithConstraints(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val constraints = if (minWidth < 600.dp) { // portrait mode
//                myDecoupledConstraints(0.3f)
//            } else {
//                myDecoupledConstraints(0.7f)
//            }
//
//            ConstraintLayout(
//                modifier = Modifier.fillMaxSize(),
//                constraintSet = constraints
//            ) {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .layoutId("progressBar"),
//                    color = MaterialTheme.colors.primary
//                )
//                Text(
//                    modifier = Modifier
//                        .layoutId("text")
//                        .padding(top = 8.dp),
//                    text = "Loading...",
//                    style = TextStyle(
//                        color = Color.Black,
//                        fontSize = TextUnit.Companion.Sp(15)
//                    )
//                )
//            }
//        }
//    }
//}
//
//private fun myDecoupledConstraints(verticalBias: Float): ConstraintSet {
//    return ConstraintSet {
//        val guideline = createGuidelineFromTop(verticalBias)
//        val progressBar = createRefFor("progressBar")
//        val text = createRefFor("text")
//
//        constrain(progressBar) {
//            top.linkTo(guideline)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }
//
//        constrain(text) {
//            top.linkTo(progressBar.bottom)
//            centerHorizontallyTo(progressBar)
//        }
//    }
//}





















