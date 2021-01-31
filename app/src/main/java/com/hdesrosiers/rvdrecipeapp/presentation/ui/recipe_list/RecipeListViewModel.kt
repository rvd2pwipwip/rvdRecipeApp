package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

//import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.hdesrosiers.rvdrecipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel
    // constructor needed to inject dependencies into ViewModel
//    @ViewModelInject //deprecated; use @HiltViewModel and @Inject instead
    @Inject
    constructor(
        private val randomString: String,
        private val repository: RecipeRepository,
        @Named("auth_token") private val token: String
    ): ViewModel() {
        init {
            println("ViewModel: $randomString")
            println("ViewModel: $repository")
            println("ViewModel: $token")
        }
    fun getRepo() = repository
    fun getRandomString() = randomString
    fun getToken() = token
}