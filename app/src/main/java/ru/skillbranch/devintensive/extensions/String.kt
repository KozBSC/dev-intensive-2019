package ru.skillbranch.devintensive.extensions

import kotlin.math.min

fun String.truncate(value: Int = 16): String {
    val temp = this.trimEnd()
    return temp.substring(0, min(temp.length,value)) + if (min(temp.length,value) != temp.length) "..." else ""
}

fun String.stripHtml() = this.replace("<.*?>".toRegex(), "").replace("&.*?;".toRegex(), "").replace("\\s+".toRegex(), " ")