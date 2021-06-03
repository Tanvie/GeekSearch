package com.example.geeksearch.utils

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Avinash Vijayvargiya on 03-06-2021.
 */
fun AppCompatActivity.hideActionBar() {
    if (supportActionBar != null) {
        supportActionBar!!.hide()
    }
}
