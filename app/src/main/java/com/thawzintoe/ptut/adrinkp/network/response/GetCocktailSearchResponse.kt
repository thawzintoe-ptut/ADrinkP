package com.thawzintoe.ptut.adrinkp.network.response

import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem

data class GetCocktailSearchResponse(
	val drinks: List<SearchDrinksItem>? = null
)
