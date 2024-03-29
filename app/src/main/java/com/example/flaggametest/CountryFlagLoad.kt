package com.example.flaggametest

import androidx.compose.runtime.Composable


@Composable
fun getDrawableResourceId(resourceName: String): Int {
    return R.drawable::class.java.getField(resourceName).getInt(null)
}
