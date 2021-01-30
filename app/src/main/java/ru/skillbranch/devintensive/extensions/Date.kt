package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String =
    SimpleDateFormat(pattern, Locale("ru")).format(this)

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    this.time += value * when (units) {
        TimeUnits.SECOND -> SECOND
        TimeUnits.MINUTE -> MINUTE
        TimeUnits.HOUR -> HOUR
        TimeUnits.DAY -> DAY
    }
    return this
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Long): String = when{
            value % 10 == 1L -> "$value секунду"
            value % 10 in 2..4 -> "$value секунды"
            else -> "$value секунд"
        }
    }, MINUTE {
        override fun plural(value: Long): String = when{
            value % 10 == 1L -> "$value минуту"
            value % 10 in 2..4 -> "$value минуты"
            else -> "$value минут"
        }
    }, HOUR {
        override fun plural(value: Long): String  = when{
            value % 10 == 1L -> "$value час"
            value % 10 in 2..4 -> "$value часа"
            else -> "$value часов"
        }
    }, DAY {
        override fun plural(value: Long): String  = when{
            value % 10 == 1L -> "$value день"
            value % 10 in 2..4 -> "$value дня"
            else -> "$value дней"
        }
    };

    abstract fun plural(value: Long): String
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - this.time
    return when {
        diff <= -360 * DAY -> "более чем через год"
        diff in -360 * DAY..-26 * HOUR -> "через ${TimeUnits.DAY.plural(abs((diff)) / DAY)}"
        diff in -26 * HOUR..-22 * HOUR -> "через день"
        diff in -22 * HOUR..-75 * MINUTE -> "через ${TimeUnits.HOUR.plural(abs((diff)) / HOUR)}"
        diff in -75 * MINUTE..-45 * MINUTE -> "через час"
        diff in -45 * MINUTE..-75 * SECOND -> "через ${TimeUnits.MINUTE.plural(abs((diff)) / MINUTE)}"
        diff in -75 * SECOND..-45 * SECOND -> "через минуту"
        diff in -45 * SECOND..-SECOND -> "через несколько секунд"
        diff in -SECOND..SECOND -> "только что"
        diff in SECOND..45 * SECOND -> "несколько секунд назад"
        diff in 45 * SECOND..75 * SECOND -> "минуту назад"
        diff in 75 * SECOND..45 * MINUTE -> "${TimeUnits.MINUTE.plural((diff) / MINUTE)} назад"
        diff in 45 * MINUTE..75 * MINUTE -> "час назад"
        diff in 75 * MINUTE..22 * HOUR -> "${TimeUnits.HOUR.plural((diff) / HOUR)} назад"
        diff in 22 * HOUR..26 * HOUR -> "день назад"
        diff in 26 * HOUR..360 * DAY -> "${TimeUnits.DAY.plural((diff) / DAY)} назад"
        else -> "более года назад"
    }
}
