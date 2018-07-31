package com.example.ptut.adrinkp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.activities.base.BaseActivity
import com.example.ptut.adrinkp.vos.searchList.SearchDrinksItem
import kotlinx.android.synthetic.main.activity_search_detail.*
import kotlinx.android.synthetic.main.content_search_detail.*

class SearchCocktailDetailActivity:BaseActivity() {
    private lateinit var searchDrinksItem:SearchDrinksItem
    companion object {
        fun newIntent(context: Context,searchDrinksItem: SearchDrinksItem): Intent {
            val intent:Intent= Intent(context, SearchCocktailDetailActivity::class.java)
            var bundle:Bundle= Bundle()
            bundle.putSerializable("DetailItem",searchDrinksItem)
            intent.putExtra("DetailBundle",bundle)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var bundle:Bundle= Bundle()
        searchDrinksItem= (intent.getBundleExtra("DetailBundle").getSerializable("DetailItem") as SearchDrinksItem?)!!
        supportActionBar!!.title=searchDrinksItem.strDrink
        Glide.with(applicationContext)
                .load(searchDrinksItem.strDrinkThumb)
                .into(backdrop)
        detailCategory.text=searchDrinksItem.strCategory
        detailDate.text=searchDrinksItem.dateModified
        detailAlcoholic.text=searchDrinksItem.strAlcoholic
        detailGlass.text=searchDrinksItem.strGlass
        detailInstruction.text=searchDrinksItem.strInstructions
        strIngredient1.text=searchDrinksItem.strIngredient1
        strMeasure1.text=searchDrinksItem.strMeasure1
        strIngredient2.text=searchDrinksItem.strIngredient2
        strMeasure2.text=searchDrinksItem.strMeasure2
        strIngredient3.text=searchDrinksItem.strIngredient3
        strMeasure3.text=searchDrinksItem.strMeasure3
        strIngredient4.text=searchDrinksItem.strIngredient4
        strMeasure4.text=searchDrinksItem.strMeasure4
        strIngredient5.text=searchDrinksItem.strIngredient5
        strMeasure5.text=searchDrinksItem.strMeasure5
        strIngredient6.text=searchDrinksItem.strIngredient6
        strMeasure6.text=searchDrinksItem.strMeasure6


    }
}