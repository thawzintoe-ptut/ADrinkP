package com.thawzintoe.ptut.adrinkp.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class CustomImageView:ImageView{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val threeTwoHeight=MeasureSpec.getSize(widthMeasureSpec)*2/3
        val threeTwoHeightSpec=MeasureSpec.makeMeasureSpec(threeTwoHeight,MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, threeTwoHeightSpec)
    }
}