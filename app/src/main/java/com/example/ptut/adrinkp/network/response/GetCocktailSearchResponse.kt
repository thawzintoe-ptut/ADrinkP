package com.example.ptut.adrinkp.network.response

import com.example.ptut.adrinkp.vos.searchList.SearchDrinksItem

data class GetCocktailSearchResponse(
	val drinks: List<SearchDrinksItem>? = null
)
