package ru.skillbranch.devintensive.utils

import android.content.Context
import android.util.TypedValue

object Utils {
    fun parseFullName(fullName:String?) : Pair<String?,String?>{
        var string = fullName?.trim()
        if (string == "") string = null
        val parts : List<String>? = string?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
//        val firstInitial = if (firstName?.trim()?.length == 0) null else firstName?.first()?.toUpperCase()
//        val lastInitial = if (lastName?.trim()?.length == 0) null else lastName?.first()?.toUpperCase()
//        var result = "${if(firstInitial == null) {if(lastInitial == null) null else "$lastInitial"} else {if(lastInitial == null) "$firstInitial" else "$firstInitial$lastInitial"}}"

        return when {
            !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> "${firstName.trim().first()}${lastName.trim().first()}".toUpperCase()
            firstName.isNullOrBlank() -> if (lastName.isNullOrBlank()) null else lastName.trim().first().toUpperCase().toString()
            else -> firstName.trim().first().toUpperCase().toString()
        }

    }

    // version 2.0
    fun transliteration(payload: String, divider: String = " "): String {
        val map = fillTranslitMap()
        val builder = StringBuilder()

        for (char in payload.trim())
            builder.append(getTranslChar(char, map))

        return builder.toString().replace(" ", divider)
    }

    private fun getTranslChar(char: Char, map: HashMap<Char, String>): String {
        val transl  = map[char.toLowerCase()] ?: char.toString()

        return if (char.isUpperCase() && transl.isNotEmpty())
            transl.capitalize()
        else transl
    }

    private fun fillTranslitMap(): HashMap<Char, String> {
        val map = hashMapOf<Char, String>()
        map['а'] = "a"
        map['б'] = "b"
        map['в'] = "v"
        map['г'] = "g"
        map['д'] = "d"
        map['е'] = "e"
        map['ё'] = "e"
        map['ж'] = "zh"
        map['з'] = "z"
        map['и'] = "i"
        map['й'] = "i"
        map['к'] = "k"
        map['л'] = "l"
        map['м'] = "m"
        map['н'] = "n"
        map['о'] = "o"
        map['п'] = "p"
        map['р'] = "r"
        map['с'] = "s"
        map['т'] = "t"
        map['у'] = "u"
        map['ф'] = "f"
        map['х'] = "h"
        map['ц'] = "c"
        map['ч'] = "ch"
        map['ш'] = "sh"
        map['щ'] = "sh'"
        map['ъ'] = ""
        map['ы'] = "i"
        map['ь'] = ""
        map['э'] = "e"
        map['ю'] = "yu"
        map['я'] = "ya"

        return map
    }

    fun convertDpToPx(context: Context, dp: Int): Int = (dp * context.resources.displayMetrics.density + 0.5f).toInt()

    fun convertPxToDp(context: Context, px: Int): Int = (px / context.resources.displayMetrics.density + 0.5f).toInt()

    fun convertSpToPx(context: Context, sp: Int): Int = (sp * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()

}