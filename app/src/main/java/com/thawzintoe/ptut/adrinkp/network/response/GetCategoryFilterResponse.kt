package com.thawzintoe.ptut.adrinkp.network.response

import com.thawzintoe.ptut.adrinkp.vos.filterList.DrinksCategoryFilter

data class GetCategoryFilterResponse(
	val drinks: List<DrinksCategoryFilter>? = null
)
