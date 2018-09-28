package com.thawzintoe.ptut.adrinkp.components

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_pod_empty.view.*

open class EmptyViewPod : RelativeLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(emptyImageId: Int, emptyMsg: String) {
        ivEmpty!!.setImageResource(emptyImageId)
        tvEmpty!!.text = emptyMsg
    }

    fun setEmptyData(emptyMsg: String) {
        tvEmpty!!.text = emptyMsg
    }
}