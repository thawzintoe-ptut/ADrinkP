package com.thawzintoe.ptut.adrinkp.network.response

import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksGlass


data class GetGlassResponse(
        var drinks: List<DrinksGlass>? = null
)
