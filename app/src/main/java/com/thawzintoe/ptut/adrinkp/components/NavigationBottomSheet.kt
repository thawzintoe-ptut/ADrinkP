package com.thawzintoe.ptut.adrinkp.components

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.activities.HomeActivity
import com.thawzintoe.ptut.adrinkp.utils.ALCOHOL_INDEX
import com.thawzintoe.ptut.adrinkp.utils.CATEGORY_INDEX
import com.thawzintoe.ptut.adrinkp.utils.GLASS_INDEX
import com.thawzintoe.ptut.adrinkp.utils.INGREDIENT_INDEX
import kotlinx.android.synthetic.main.navigation_bottom_sheet.*

class NavigationBottomSheet:
        BottomSheetDialogFragment(),
        View.OnClickListener{
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.navigation_bottom_sheet,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryFilter.setOnClickListener(this)
        glassFilter.setOnClickListener(this)
        ingredientFilter.setOnClickListener(this)
        alcoholicFilter.setOnClickListener(this)
        cancelSheet.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.categoryFilter->{
                dismiss()
                startActivity(HomeActivity.newIntent(context!!, CATEGORY_INDEX))
            }
            R.id.glassFilter->{
                startActivity(HomeActivity.newIntent(context!!, GLASS_INDEX))
                dismiss()
            }
            R.id.ingredientFilter->{
                startActivity(HomeActivity.newIntent(context!!, INGREDIENT_INDEX))
                dismiss()
            }
            R.id.alcoholicFilter->{
                startActivity(HomeActivity.newIntent(context!!, ALCOHOL_INDEX))
                dismiss()
            }
            R.id.cancelSheet->{
                dismiss()
            }
        }
    }
}