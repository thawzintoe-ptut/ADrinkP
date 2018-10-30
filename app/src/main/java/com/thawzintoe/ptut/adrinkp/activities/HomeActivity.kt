package com.thawzintoe.ptut.adrinkp.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.view.menu.ActionMenuItemView
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.components.NavigationBottomSheet
import com.thawzintoe.ptut.adrinkp.fragments.AlcoholFragment
import com.thawzintoe.ptut.adrinkp.fragments.CategoryFragment
import com.thawzintoe.ptut.adrinkp.fragments.GlassFragment
import com.thawzintoe.ptut.adrinkp.fragments.IngredientFragment
import com.thawzintoe.ptut.adrinkp.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity: BaseActivity() {
    private lateinit var transaction: FragmentTransaction
    private lateinit var fragment: Fragment
    private var fragmentIndex:Int=0

    companion object {
        fun newIntent(context: Context,fragmentIndex:Int):Intent{
            val intent=Intent(context,HomeActivity::class.java)
            intent.putExtra("FragmentIndex",fragmentIndex)
            return  intent
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentIndex=intent.getIntExtra("FragmentIndex",0)
        switchFragmentPage(fragmentIndex)

        bottomAppBar.replaceMenu(R.menu.category_item)
        bottomAppBar.setOnMenuItemClickListener{
             when(it.itemId){
                R.id.action_random->{
                    AnimationUtils.loadAnimation(this, R.anim.spin_rotate).also { hyperspaceJumpAnimation ->
                        findViewById<ActionMenuItemView>(R.id.action_random).startAnimation(hyperspaceJumpAnimation)
                    }.setAnimationListener(object:Animation.AnimationListener{
                        override fun onAnimationRepeat(p0: Animation?) {}
                        override fun onAnimationEnd(p0: Animation?) {
                            startActivity(RandomDrinkActivity.newIntent(applicationContext)) }
                        override fun onAnimationStart(p0: Animation?) {}
                    })
                    true
                }
                else -> { false }
            }
        }
        bottomAppBar.setNavigationOnClickListener {
            val bottomSheetFragment = NavigationBottomSheet()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        searchFab.setOnClickListener{
            presentActivity(it)
        }
    }


    private fun switchFragmentPage(index:Int){
        transaction = supportFragmentManager.beginTransaction()
        when(index){
            0->{
                fragment=CategoryFragment()
                replaceFragment(R.id.homeContent,fragment)
            }
            1->{
                fragment=GlassFragment()
                replaceFragment(R.id.homeContent,fragment)
            }
            2->{
                fragment=IngredientFragment()
                replaceFragment(R.id.homeContent,fragment)
            }
            3->{
                fragment=AlcoholFragment()
                replaceFragment(R.id.homeContent,fragment)
            }

        }

    }

    private fun presentActivity(view: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition")
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()

        val intent = Intent(this, SearchCocktailActivity::class.java)
        intent.putExtra(SearchCocktailActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
        intent.putExtra(SearchCocktailActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }


}