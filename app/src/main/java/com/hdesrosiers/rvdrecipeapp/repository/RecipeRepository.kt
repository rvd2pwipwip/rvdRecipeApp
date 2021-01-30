package com.hdesrosiers.rvdrecipeapp.repository

import com.hdesrosiers.rvdrecipeapp.domain.model.Recipe

interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Recipe> // return domain model to UI (not entity or Dto)

    suspend fun get(token: String, id: Int): Recipe
}