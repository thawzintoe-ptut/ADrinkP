package com.example.ptut.adrinkp.network.response

import com.example.ptut.adrinkp.vos.filterList.DrinksCategoryFilter

data class GetCategoryFilterResponse(
	val drinks: List<DrinksCategoryFilter>? = null
)
