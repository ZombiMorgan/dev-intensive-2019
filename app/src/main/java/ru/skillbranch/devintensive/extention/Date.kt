package ru.skillbranch.devintensive.extention

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}

//enum class TimeUnits {
//    SECOND,
//    MINUTE,
//    HOUR,
//    DAY
//}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY;

    fun plural(value: Int): String {
        val plurals = mapOf(
            SECOND to Triple("секунду","секунды","секунд"),
            MINUTE to Triple("минуту","минуты","минут"),
            HOUR to Triple("час","часа","часов"),
            DAY to Triple("день","дня","дней")
        )

        val rem = value % 10
        val rem100 = value % 100

        return when {
            rem100 in 10..20 -> "$value ${plurals[this]?.third}"
            rem in 2..4 -> "$value ${plurals[this]?.second}"
            rem == 1 -> "$value ${plurals[this]?.first}"
            else -> "$value ${plurals[this]?.third}"
        }
    }
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = abs(this.time -  date.time)
    val isPast = this.time < date.time

    return when {
        diff <= SECOND -> "только что"
        diff <= SECOND * 45 -> if (isPast) "несколько секунд назад" else "через несколько секунд"
        diff <= SECOND * 75 -> if (isPast) "минуту назад" else "через минуту"
        diff <= MINUTE * 45 -> if (isPast) "${TimeUnits.MINUTE.plural((diff / MINUTE).toInt())} назад"
        else "через ${TimeUnits.MINUTE.plural((diff / MINUTE).toInt())}"
        diff  <= MINUTE * 75 -> if (isPast) "час назад" else "через час"
        diff  <= HOUR * 22 -> if (isPast) "${TimeUnits.HOUR.plural((diff / HOUR).toInt())} назад"
        else "через ${TimeUnits.HOUR.plural((diff / HOUR).toInt())}"
        diff  <= HOUR * 26 -> if (isPast) "день назад" else "через день"
        diff  <= DAY * 360 -> if (isPast) "${TimeUnits.DAY.plural((diff / DAY).toInt())} назад"
        else "через ${TimeUnits.DAY.plural((diff / DAY).toInt())}"
        else -> if (isPast) "более года назад" else "более чем через год"
    }
}