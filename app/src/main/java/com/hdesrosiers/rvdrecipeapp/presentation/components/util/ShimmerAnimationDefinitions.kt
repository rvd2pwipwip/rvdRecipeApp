package com.hdesrosiers.rvdrecipeapp.presentation.components.util

import androidx.compose.animation.core.*
import com.hdesrosiers.rvdrecipeapp.presentation.components.util.ShimmerAnimationDefinitions.ShimmerAnimationStates.*

class ShimmerAnimationDefinitions(
    private val widthPx: Float,
    private val heightPx: Float,
) {

    var gradientWidth: Float = 0.4f * heightPx

    //1 enum class to name states
    enum class ShimmerAnimationStates {
        START, END
    }

    //2 define prop keys
    val xShimmerPropKey = FloatPropKey(label = "xShimmer")
    val yShimmerPropKey = FloatPropKey(label = "yShimmer")

    //3 transition definitions, what happens in the animation
    val shimmerTransitionDefinition = transitionDefinition<ShimmerAnimationStates> {
        // animation keyframe values
        state(START) {
            this[xShimmerPropKey] = 0f
            this[yShimmerPropKey] = 0f
        }
        state(END) {
            this[xShimmerPropKey] = widthPx + gradientWidth
            this[yShimmerPropKey] = heightPx + gradientWidth
        }
        transition(
            START to END
        ) {
            //what happens from keyframe to keyframe
            xShimmerPropKey using infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    delayMillis = 300,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart // default value
            )

            yShimmerPropKey using infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    delayMillis = 300,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart // default value
            )
        }
    }
}