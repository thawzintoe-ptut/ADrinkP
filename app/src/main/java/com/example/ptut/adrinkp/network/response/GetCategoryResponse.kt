package com.example.ptut.adrinkp.network.response

import com.example.ptut.adrinkp.vos.categoryList.DrinksItem

data class GetCategoryResponse(
	var drinks: List<DrinksItem>? = null
)
