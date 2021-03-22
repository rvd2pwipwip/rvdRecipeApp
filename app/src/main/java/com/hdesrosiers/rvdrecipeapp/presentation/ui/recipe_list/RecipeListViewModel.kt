package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hdesrosiers.rvdrecipeapp.domain.model.Recipe
import com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list.RecipeListEvent.*
import com.hdesrosiers.rvdrecipeapp.repository.RecipeRepository
import com.hdesrosiers.rvdrecipeapp.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeListViewModel
// constructor needed to inject dependencies into ViewModel
@Inject
constructor(
    // [RetrofitService] <- [Repository] <- [ViewModel]
    // repository passed as view model constructor arg
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String
) : ViewModel() {
    // observe data from repository
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    val query = mutableStateOf("")

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    // track position (no need to react = no need for mutable state)
    var categoryScrollPosition: Float = 0f

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)

    private var recipeListScrollPosition = 0

    //get data from repository
    init {
        onTriggerEvent(NewSearchEvent)
    }

    fun onTriggerEvent(event: RecipeListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is NewSearchEvent -> {
                        newSearch()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "onTriggerEvent: Exception: $e, ${e.cause}")
            }
        }
    }

    private suspend fun newSearch() {
        loading.value = true

        resetSearchState()

        delay(2000)

        val result = repository.search(
            token = token,
            page = 1,
            query = query.value
        )
        recipes.value = result
        loading.value = false
    }

    private suspend fun nextPage() {
        // prevent duplicate events due to recompose happening too quickly
        if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            loading.value = true
            incrementPage()
            Log.d(TAG, "nextPage: triggered: ${page.value}")

            // just to show pagination, api is too quick
            delay(1000)

            if (page.value > 1) {
                val result = repository.search(
                    token = token,
                    page = page.value,
                    query = query.value
                )
                Log.d(TAG, "nextPage: $result")
                appendRecipes(result)
            }
            loading.value = false
        }
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrollPosition = position
    }

    // Append new recipes to the current list of recipes
    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    // Called when a new search is executed
    private fun resetSearchState() {
        recipes.value = emptyList()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if (selectedCategory.value?.value != query.value) clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    //change the value of query here to pass it to RecipeListFragment
    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float) {
        categoryScrollPosition = position
    }
}