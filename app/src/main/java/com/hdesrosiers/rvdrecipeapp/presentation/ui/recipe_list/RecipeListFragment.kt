package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
// import androidx.fragment.app.activityViewModels //if we want to share the ViewModel between activities
import androidx.fragment.app.viewModels
import com.hdesrosiers.rvdrecipeapp.presentation.components.CircularIndeterminateProgressBar
import com.hdesrosiers.rvdrecipeapp.presentation.components.RecipeCard
import com.hdesrosiers.rvdrecipeapp.presentation.components.SearchAppBar
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

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        LazyColumn(content = {
                            itemsIndexed(
                                items = recipes
                            ) { index, recipe ->
                                RecipeCard(recipe = recipe, onClick = { /*TODO*/ })
                            }
                        })

                        CircularIndeterminateProgressBar(isDisplayed = loading)
                    }
                }
            }
        }
    }
}