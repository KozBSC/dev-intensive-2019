package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.split(" ")
        return (if (parts?.getOrNull(0) == "") null else parts?.getOrNull(0)) to
                (if (parts?.getOrNull(1) == "") null else parts?.getOrNull(1))
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val res = "${firstName?.replace("\\s+".toRegex(), "")?.getOrNull(0)?.toUpperCase() ?: ""}" +
                "${lastName?.replace("\\s+".toRegex(), "")?.getOrNull(0)?.toUpperCase() ?: ""}"
        return if (res == "") null else res
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val mp: Map<String, String> = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
        )
        var res = ""
        payload.replace(" ", divider).forEach { c ->
            if (c.isUpperCase()) {
                var temp = mp[c.toString().toLowerCase(Locale.ROOT)] ?: c.toString()
                temp = temp[0].toUpperCase() + temp.substring(1)
                res += temp
            } else res += mp[c.toString()] ?: c
        }
        return res
    }
}