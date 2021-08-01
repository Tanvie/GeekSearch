package com.example.geeksearch.utils

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideActionBar() {
    if (supportActionBar != null) {
        supportActionBar!!.hide()
    }
}
