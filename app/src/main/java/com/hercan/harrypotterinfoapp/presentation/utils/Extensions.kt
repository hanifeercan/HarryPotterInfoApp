package com.hercan.harrypotterinfoapp.presentation.utils

fun String.toFirstCharUpperCase(): String {
    return this.replaceFirstChar { it.uppercase() }
}