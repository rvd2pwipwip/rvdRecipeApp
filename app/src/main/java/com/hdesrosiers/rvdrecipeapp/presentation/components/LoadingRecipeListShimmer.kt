package com.hdesrosiers.rvdrecipeapp.presentation.components

import android.util.Log
import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hdesrosiers.rvdrecipeapp.presentation.components.util.ShimmerAnimationDefinitions
import com.hdesrosiers.rvdrecipeapp.presentation.components.util.ShimmerAnimationDefinitions.ShimmerAnimationStates.*
import com.hdesrosiers.rvdrecipeapp.util.TAG
import java.lang.reflect.Modifier

@Composable
fun LoadingRecipeListShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp
) {
    // WithConstraints = access screen properties and measurements
    WithConstraints {
        // px values calculated from device density (dp)
        val cardWidthPx = with(AmbientDensity.current) {
            (maxWidth - (padding * 2)).toPx()
        }
        val cardHeightPx = with(AmbientDensity.current) {
            (imageHeight - (padding)).toPx()
        }

        val cardAnimationDefinition = remember {
            ShimmerAnimationDefinitions(
                widthPx = cardWidthPx,
                heightPx = cardHeightPx,
            )
        }
        // get transition from animation definitions
        val cardShimmerTranslateAnim = transition(
            definition = cardAnimationDefinition.shimmerTransitionDefinition,
            initState = START,
            toState = END
        )

        val colors = listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.5f),
            Color.LightGray.copy(alpha = 0.9f),
        )

        val xCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.xShimmerPropKey]
        val yCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.yShimmerPropKey]
        Log.d(TAG, "LoadingRecipeListShimmer x: $xCardShimmer")
        Log.d(TAG, "LoadingRecipeListShimmer y: $yCardShimmer")

        ScrollableColumn {
            repeat(5) {
                ShimmerRecipeCardItem(
                    colors = colors,
                    cardHeight = imageHeight,
                    xShimmer = xCardShimmer,
                    yShimmer = yCardShimmer,
                    padding = padding,
                    gradientWidth = cardAnimationDefinition.gradientWidth
                )
            }
        }
    }
}