package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
// import androidx.fragment.app.activityViewModels //if we want to share the ViewModel between activities
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.hdesrosiers.rvdrecipeapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    // instantiate ViewModel inside fragment
    val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("RecipeListFragment: ${viewModel.getRepo()}")
        println("RecipeListFragment: ${viewModel.getRandomString()}")
        println("RecipeListFragment: ${viewModel.getToken()}")
    }

    override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
        setContent {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Recipe List",
                    style = TextStyle(fontSize = 21.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    findNavController().navigate(R.id.viewRecipe) //not the fragment id, the navigation action id
                }) {
                    Text(text = "To recipe fragment")
                }
            }
        }
    }
  }
}