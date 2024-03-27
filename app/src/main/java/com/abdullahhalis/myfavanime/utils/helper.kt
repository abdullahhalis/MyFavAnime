package com.abdullahhalis.myfavanime.utils

import kotlin.math.ceil

fun String.mediaTypes(): String {
    val newString = this.replace("_", " ")

    return when {
        (newString.length > 3 && newString.contains(" ")) -> {
            newString.split(" ")
                .let { words -> words.joinToString(separator = " ") { word -> word.replaceFirstChar { it.uppercase() } } }
        }
        (newString.length > 3) -> newString.replaceFirstChar { it.uppercase() }
        else -> newString.uppercase()
    }
}

fun Int.secondToMinutes(): Int {
    val floatValue = this.toFloat()
    return ceil(floatValue/60).toInt()
}

fun Int.formatNumber(): String {
    val string = this.toString()
    return buildString {
        for (i in string.indices) {
            if (i > 0 && (string.length - i) % 3 == 0 ) {
                append('.')
            }
            append(string[i])
        }
    }
}