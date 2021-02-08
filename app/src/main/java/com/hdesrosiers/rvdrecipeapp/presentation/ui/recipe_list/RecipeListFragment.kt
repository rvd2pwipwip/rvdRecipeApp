package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
// import androidx.fragment.app.activityViewModels //if we want to share the ViewModel between activities
import androidx.fragment.app.viewModels
import com.hdesrosiers.rvdrecipeapp.presentation.components.FoodCategoryChip
import com.hdesrosiers.rvdrecipeapp.presentation.components.RecipeCard
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

                val selectedCategory = viewModel.selectedCategory.value

                Column {
                    // custom tool bar
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.White,
                        elevation = 8.dp
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    value = query,
//                                  value = _query.value,
                                    onValueChange = {
                                        viewModel.onQueryChanged(it)
//                                       _query.value = it
                                    },
                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search
                                    ),
                                    leadingIcon = {
                                        Icon(imageVector = Icons.Filled.Search)
                                    },
                                    onImeActionPerformed = { action, softKeyboardController ->
                                        if (action == ImeAction.Search) {
                                            viewModel.newSearch()
                                            softKeyboardController?.hideSoftwareKeyboard()
                                        }
                                    },
                                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                    backgroundColor = MaterialTheme.colors.surface
                                )
                            }

                            ScrollableRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, bottom = 8.dp)
                            ) {
                                for (category in getAllFoodCategories()) {
                                    FoodCategoryChip(
                                        category = category.value,
                                        isSelected = selectedCategory == category,
                                        onSelectedCategoryChanged = {
                                            viewModel.onSelectedCategoryChanged(it)
                                        },
                                        onExecuteSearch = viewModel::newSearch,
                                    )
                                }
                            }
                        }
                    }

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