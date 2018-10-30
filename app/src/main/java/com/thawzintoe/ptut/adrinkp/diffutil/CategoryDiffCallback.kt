package com.thawzintoe.ptut.adrinkp.diffutil

import android.support.v7.util.DiffUtil
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksItem

class CategoryDiffCallback : DiffUtil.ItemCallback<DrinksItem>() {

    override fun areItemsTheSame(
            oldItem: DrinksItem,
            newItem: DrinksItem
    ): Boolean {
        return oldItem.strCategory == newItem.strCategory
    }

    override fun areContentsTheSame(
            oldItem: DrinksItem,
            newItem: DrinksItem
    ): Boolean {
        return oldItem.strCategory == newItem.strCategory
    }
}