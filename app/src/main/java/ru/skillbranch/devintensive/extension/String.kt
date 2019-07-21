package ru.skillbranch.devintensive.extension

fun String.stripHtml() = this.replace(Regex("(<.*?>)|(&[^ а-я]{1,4}?;)"), "").replace(Regex(" {2,}"), " ")

fun String.truncate(length: Int = 16): String {
    val truncStr = this.trim()
    return if (truncStr.length <= length) truncStr else truncStr.substring(0, length).trim() + "..."
}