package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
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
import com.hdesrosiers.rvdrecipeapp.presentation.components.RecipeCard
import com.hdesrosiers.rvdrecipeapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    // instantiate ViewModel inside fragment
    val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value // observable data

                // Mutable data structure that will be passed to the TextField
                val query = viewModel.query.value // from viewModel to persist configuration change
                // another way of persisting data with savedInstanceState
//                val _query = savedInstanceState{ "Beef" }

                Column {
                    TextField(
                        value = query,
//                        value = _query.value,
                        onValueChange = {
                            viewModel.onQueryChanged(it)
//                            _query.value = it
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(content = {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = { /*TODO*/ })
                        }
                    })
                }
            }
        }
    }
}