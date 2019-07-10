package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?) : Pair<String?,String?>{
        var string = fullName?.trimIndent()
        if (string == "") string = null
        val parts : List<String>? = string?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String {
        val array = payload.split(" ")
        val firstName = getTranslit(array[0]?.trimIndent())
        val lastName = getTranslit(array[1]?.trimIndent())

        return "${firstName}$divider$lastName"
    }

    private fun getTranslit(s: String): String {
        var result = ""

        for (el in s) {
            result += rusToEng(el)
        }

        return result
    }

    private fun rusToEng(ch: Char) = when(ch) {
        'а' -> "a"
        'б' -> "b"
        'в' -> "v"
        'г' -> "g"
        'д' -> "d"
        'е' -> "e"
        'ё' -> "e"
        'ж' -> "zh"
        'з' -> "z"
        'и' -> "i"
        'й' -> "i"
        'к' -> "k"
        'л' -> "l"
        'м' -> "m"
        'н' -> "n"
        'о' -> "o"
        'п' -> "p"
        'р' -> "r"
        'с' -> "s"
        'т' -> "t"
        'у' -> "u"
        'ф' -> "f"
        'х' -> "h"
        'ц' -> "c"
        'ч' -> "ch"
        'ш' -> "sh"
        'щ' -> "sh'"
        'ъ' -> ""
        'ы' -> "i"
        'ь' -> ""
        'э' -> "e"
        'ю' -> "yu"
        'я' -> "ya"
        'а'.toUpperCase() -> "a".toUpperCase()
        'б'.toUpperCase() -> "b".toUpperCase()
        'в'.toUpperCase() -> "v".toUpperCase()
        'г'.toUpperCase() -> "g".toUpperCase()
        'д'.toUpperCase() -> "d".toUpperCase()
        'е'.toUpperCase() -> "e".toUpperCase()
        'ё'.toUpperCase() -> "e".toUpperCase()
        'ж'.toUpperCase() -> "zh".toUpperCase()
        'з'.toUpperCase() -> "z".toUpperCase()
        'и'.toUpperCase() -> "i".toUpperCase()
        'й'.toUpperCase() -> "i".toUpperCase()
        'к'.toUpperCase() -> "k".toUpperCase()
        'л'.toUpperCase() -> "l".toUpperCase()
        'м'.toUpperCase() -> "m".toUpperCase()
        'н'.toUpperCase() -> "n".toUpperCase()
        'о'.toUpperCase() -> "o".toUpperCase()
        'п'.toUpperCase() -> "p".toUpperCase()
        'р'.toUpperCase() -> "r".toUpperCase()
        'с'.toUpperCase() -> "s".toUpperCase()
        'т'.toUpperCase() -> "t".toUpperCase()
        'у'.toUpperCase() -> "u".toUpperCase()
        'ф'.toUpperCase() -> "f".toUpperCase()
        'х'.toUpperCase() -> "h".toUpperCase()
        'ц'.toUpperCase() -> "c".toUpperCase()
        'ч'.toUpperCase() -> "ch".toUpperCase()
        'ш'.toUpperCase() -> "sh".toUpperCase()
        'щ'.toUpperCase() -> "sh'".toUpperCase()
        'ъ'.toUpperCase() -> "".toUpperCase()
        'ы'.toUpperCase() -> "i".toUpperCase()
        'ь'.toUpperCase() -> "".toUpperCase()
        'э'.toUpperCase() -> "e".toUpperCase()
        'ю'.toUpperCase() -> "yu".toUpperCase()
        'я'.toUpperCase() -> "ya".toUpperCase()
        else -> ch
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInitial = if (firstName?.trimIndent()?.length == 0) null else firstName?.first()
        val lastInitial = if (lastName?.trimIndent()?.length == 0) null else lastName?.first()

        return "${if(firstInitial == null) {if(lastInitial == null) null else "$lastInitial"} else {if(lastInitial == null) "$firstInitial" else "$firstInitial$lastInitial"}}"
    }
}