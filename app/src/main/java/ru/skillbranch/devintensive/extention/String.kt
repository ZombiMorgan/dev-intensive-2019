package ru.skillbranch.devintensive.extention

fun String.stripHtml() = this.replace(Regex("<[^<]*?>|&\\d+;"), "").replace(Regex("\\s+"), " ")

fun String.truncate(length: Int = 16): String {
    this.trimEnd()
    if (this.substring(length - 1, length) == " ") {
        var truncatedStr = this.trimEnd()
        if (truncatedStr.length < length) {
            return truncatedStr
        }
        return this.substring(0, length - 1) + "..."
    } else {
        return this.substring(0, length) + "..."
    }
}