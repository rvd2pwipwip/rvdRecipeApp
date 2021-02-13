package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
// import androidx.fragment.app.activityViewModels //if we want to share the ViewModel between activities
import androidx.fragment.app.viewModels
import com.hdesrosiers.rvdrecipeapp.presentation.components.*
import com.hdesrosiers.rvdrecipeapp.presentation.components.HeartAnimationDefinition.HeartButtonState.*
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
                // mutable state values
                val recipes = viewModel.recipes.value // observable data

                // Mutable data structure that will be passed to the TextField
                val query = viewModel.query.value // from viewModel to persist configuration change
                // another way of persisting data with savedInstanceState
//                val _query = savedInstanceState{ "Beef" }

                val selectedCategory = viewModel.selectedCategory.value

                val loading = viewModel.loading.value

                Column {

                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged, // method references to delegate
                        onExecuteSearch = viewModel::onExecuteSearch,
                        scrollPosition = viewModel.categoryScrollPosition,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        onChangedCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .height(200.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val state = remember { mutableStateOf(IDLE) }

                        AnimatedHeartButton(
                            modifier = Modifier,
                            buttonState = state,
                            onToggle = {
                                state.value = if (state.value == IDLE) ACTIVE else IDLE
                            })
                    }

//                    PulsingDemo()

//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                    ) {
//                        LazyColumn(content = {
//                            itemsIndexed(
//                                items = recipes
//                            ) { index, recipe ->
//                                RecipeCard(recipe = recipe, onClick = { /*TODO*/ })
//                            }
//                        })
//
//                        CircularIndeterminateProgressBar(isDisplayed = loading)
//                    }
                }
            }
        }
    }
}