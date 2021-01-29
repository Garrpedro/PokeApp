package com.example.pokeapp.utils

import java.util.*

object TextUtils {
    @JvmStatic
    fun capitalizeString(str: String): String {
        var retStr = str
        try { // We can face index out of bound exception if the string is null
            retStr = str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1)
        } catch (e: Exception) {
        }
        return retStr
    }
}