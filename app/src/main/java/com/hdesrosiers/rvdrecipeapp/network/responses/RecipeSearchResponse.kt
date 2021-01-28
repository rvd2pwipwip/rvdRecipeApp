package com.hdesrosiers.rvdrecipeapp.network.responses

import com.google.gson.annotations.SerializedName
import com.hdesrosiers.rvdrecipeapp.network.model.RecipeNetworkEntity

class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeNetworkEntity>,
)