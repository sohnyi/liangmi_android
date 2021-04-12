package com.sohnyi.liangmi.utils

import kotlin.random.Random


private const val UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
private const val LOWERCASE = "abcdefghijklmnopqrstuvwxyz"
private const val NUMBERS = "0123456789"
private const val SYMBOLS = "!@#\$%^&*()_-+=<>?/{}~|"

fun giveMeAPassword(
    length: Int,
    uppercaseAvailable: Boolean,
    lowercaseAvailable: Boolean,
    numbersAvailable: Boolean,
    symbolsAvailable: Boolean
) : String {

    if (length > 128) {
        return "The password too long"
    }

    val allowedCharacters = getAllowedCharacters(uppercaseAvailable, lowercaseAvailable, numbersAvailable, symbolsAvailable)
    if (allowedCharacters.isEmpty()) {
        return ""
    }

    val sb = StringBuilder()
    while (sb.length < length) {
        val char = allowedCharacters[Random.nextInt(allowedCharacters.size)]
        sb.append(char)
    }

    return sb.toString()
}

private fun getAllowedCharacters(
    uppercaseAvailable: Boolean,
    lowercaseAvailable: Boolean,
    numbersAvailable: Boolean,
    symbolsAvailable: Boolean): List<Char> {

    val chars = mutableListOf<Char>()
    if (uppercaseAvailable) {
        chars.addAll(UPPERCASE.toList())
    }
    if (lowercaseAvailable) {
        chars.addAll(LOWERCASE.toList())
    }
    if (numbersAvailable) {
        chars.addAll(NUMBERS.toList())
    }
    if (symbolsAvailable) {
        chars.addAll(SYMBOLS.toList())
    }
    return chars
}