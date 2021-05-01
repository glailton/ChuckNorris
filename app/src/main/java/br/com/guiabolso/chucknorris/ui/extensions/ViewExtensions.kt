package br.com.guiabolso.chucknorris.ui.extensions

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun RecyclerView.hide() {
    visibility = View.GONE
}

fun RecyclerView.show() {
    visibility = View.VISIBLE
}