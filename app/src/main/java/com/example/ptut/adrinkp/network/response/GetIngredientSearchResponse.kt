package com.example.ptut.adrinkp.network.response

import com.example.ptut.adrinkp.vos.searchList.IngredientsItem

data class GetIngredientSearchResponse(
	val ingredients: List<IngredientsItem>? = null
)
