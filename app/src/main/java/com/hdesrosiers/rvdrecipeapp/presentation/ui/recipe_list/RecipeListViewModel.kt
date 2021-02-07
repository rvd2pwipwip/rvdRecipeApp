package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hdesrosiers.rvdrecipeapp.domain.model.Recipe
import com.hdesrosiers.rvdrecipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

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

    //get data from repository
    init {
        newSearch(query.value)
    }

    fun newSearch(query: String) {
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = query
            )
            recipes.value = result
        }
    }

    //change the value of query here to pass it to RecipeListFragment
    fun onQueryChanged(query: String) {
        this.query.value = query
    }
}