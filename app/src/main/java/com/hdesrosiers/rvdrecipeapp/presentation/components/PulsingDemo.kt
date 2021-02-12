package com.hdesrosiers.rvdrecipeapp.presentation.components

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.hdesrosiers.rvdrecipeapp.presentation.components.PulseAnimationDefinitions.PulseState.*
import com.hdesrosiers.rvdrecipeapp.presentation.components.PulseAnimationDefinitions.pulseDefinition
import com.hdesrosiers.rvdrecipeapp.presentation.components.PulseAnimationDefinitions.pulsePropKey
import com.hdesrosiers.rvdrecipeapp.util.TAG

// composable that uses animation definitions
@Composable
fun PulsingDemo() {
    val color = MaterialTheme.colors.primary
    
    val pulseAnim = transition(
        definition = pulseDefinition,
        initState = INITIAL,
        toState = FINAL
    )

    val pulseMagnitude = pulseAnim[pulsePropKey]
//    Log.d(TAG, "PulsingDemo: $pulseMagnitude")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(pulseMagnitude.dp)
                .width(pulseMagnitude.dp),
            imageVector = Icons.Default.Favorite
        )
    }
    
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
    ) {
        drawCircle(
            radius = pulseMagnitude,
            brush = SolidColor(color)
        )
    }
}

object PulseAnimationDefinitions {
    // name the animation states
    enum class PulseState {
        INITIAL, FINAL
    }

    // property keys, type of value to animate
    val pulsePropKey = FloatPropKey(label = "pulseKey")

    // transition definition, what happens in the animation
    val pulseDefinition = transitionDefinition<PulseState> {
        // animation will go from 40f to 50f
        state(INITIAL) {
            this[pulsePropKey] = 40f
        }
        state(FINAL) {
            this[pulsePropKey] = 50f
        }

        // define transitions between states
        transition(
            INITIAL to FINAL
        ) {
            //what happens during initial to final
            pulsePropKey using infiniteRepeatable(
                animation = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart // default value
            )
        }
    }
}