package com.thawzintoe.ptut.adrinkp.network.response

import com.thawzintoe.ptut.adrinkp.vos.searchList.IngredientsItem

data class GetIngredientSearchResponse(
	val ingredients: List<IngredientsItem>? = null
)
