package com.hdesrosiers.rvdrecipeapp.di

import com.hdesrosiers.rvdrecipeapp.network.RecipeService
import com.hdesrosiers.rvdrecipeapp.network.model.RecipeDtoMapper
import com.hdesrosiers.rvdrecipeapp.repository.RecipeRepository
import com.hdesrosiers.rvdrecipeapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeDtoMapper
        )
    }

}