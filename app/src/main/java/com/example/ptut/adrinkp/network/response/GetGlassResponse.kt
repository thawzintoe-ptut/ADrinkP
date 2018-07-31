package com.example.ptut.adrinkp.network.response

import com.example.ptut.adrinkp.vos.categoryList.DrinksGlass


data class GetGlassResponse(
        var drinks: List<DrinksGlass>? = null
)
