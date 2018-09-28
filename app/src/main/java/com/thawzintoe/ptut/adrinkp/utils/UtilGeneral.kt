package com.thawzintoe.ptut.adrinkp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.thawzintoe.ptut.adrinkp.R
import java.text.SimpleDateFormat
import java.util.*


fun showNetworkError(rootLayout: View, context: Context, error: NetworkError) {
    Snackbar.make(rootLayout, error.msg, Snackbar.LENGTH_LONG)
            .setAction("Sorry for not available", null)
            .setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent)).show()

}

fun randomColor(view: View) {
    val androidColors = view.resources.getIntArray(R.array.androidcolors)
    val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
    view.setBackgroundColor(randomAndroidColor)
}

@SuppressLint("SimpleDateFormat")
fun prettyTime(string: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd kk:mm:ss")
    val date = dateFormat.parse(string)
    return "${date}"
}
fun ImageView.load(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}

fun profileDialog(context: Context, title: String, desc: String) {
    MaterialStyledDialog.Builder(context)
            .setTitle(title)
            .setDescription(desc)
            .setIcon(R.drawable.ic_appicon)
            .setHeaderDrawable(R.color.midnightblue)
            .withIconAnimation(true)
            .setPositiveText("Dismiss")
            .show()
}
