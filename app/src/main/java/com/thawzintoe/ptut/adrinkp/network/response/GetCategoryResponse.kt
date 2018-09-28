package com.thawzintoe.ptut.adrinkp.network.response

import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksItem

data class GetCategoryResponse(
	var drinks: List<DrinksItem>? = null
)
