package com.hdesrosiers.rvdrecipeapp.network.responses

import com.google.gson.annotations.SerializedName
import com.hdesrosiers.rvdrecipeapp.network.model.RecipeDto

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)