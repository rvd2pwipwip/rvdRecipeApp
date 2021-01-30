package com.hdesrosiers.rvdrecipeapp.repository

import com.hdesrosiers.rvdrecipeapp.domain.model.Recipe
import com.hdesrosiers.rvdrecipeapp.network.RecipeService
import com.hdesrosiers.rvdrecipeapp.network.model.RecipeDtoMapper

class RecipeRepository_Impl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
): RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val result = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        val result = recipeService.get(token, id)
        return mapper.mapToDomainModel(result)
    }
}