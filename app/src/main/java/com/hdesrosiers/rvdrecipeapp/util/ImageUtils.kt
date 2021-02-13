package com.hdesrosiers.rvdrecipeapp.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AmbientContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hdesrosiers.rvdrecipeapp.R

const val DEFAULT_RECIPE_IMAGE = R.drawable.empty_plate

@Composable
fun loadPicture(
    url: String,
    @DrawableRes defaultImage: Int,
): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // load placeholder image
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // not used
            }
        })
    // asynchronous load real image
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // not used
            }
        })

    return bitmapState
}

@Composable
fun loadPicture(
    @DrawableRes drawable: Int,
): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // load placeholder image
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(drawable)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // not used
            }
        })

    return bitmapState
}