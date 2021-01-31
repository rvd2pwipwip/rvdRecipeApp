package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
    // constructor needed to inject dependencies into ViewModel
//    @ViewModelInject //deprecated; use @HiltViewModel and @Inject instead
    @Inject
    constructor(
        private val randomString: String
    ): ViewModel() {
        init {
            println("ViewModel: $randomString")
        }
}