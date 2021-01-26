package com.hdesrosiers.rvdrecipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment

class RecipeListFragment : Fragment() {

  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View {
    // Long way:
//        val view = ComposeView(requireContext())
//        view.apply {
//            setContent {
//                Text(text = "Composable in a Fragment")
//            }
//        }
//        return view

    // Concise way:
//        return ComposeView(requireContext()).apply {
//            setContent {
//                Text(text = "Recipe List Fragment")
//            }
//        }

    // Use both XML and Compose way:
    val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      Column(
              modifier = Modifier
                      .border(
                              border = BorderStroke(
                                      width = 1.dp,
                                      color = Color.Black))
                      .padding(16.dp)
      ) {
        Text(text = "This is a composable inside the fragment")
        Spacer(modifier = Modifier.height(10.dp))
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "NEAT")
        Spacer(modifier = Modifier.height(10.dp))
        // a custom view
        val customView = HorizontalDottedProgress(AmbientContext.current)
        AndroidView(viewBlock = { customView })

      }
    }
    return view
  }
}