package es.shiro.pokedex.common.extensions

val String.Companion.EMPTY_STRING: String
    get() = ""

fun String.toFormattedPrice(): String {
    val stringBuilder = StringBuilder()
    val reversedString = this.reversed()
    for (i in reversedString.indices) {
        if (i % 3 == 0 && i != 0)
            stringBuilder.append(".")
        stringBuilder.append(reversedString[i])
    }
    return stringBuilder.toString().reversed()
}