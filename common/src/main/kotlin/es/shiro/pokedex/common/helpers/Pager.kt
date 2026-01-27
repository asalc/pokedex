package es.shiro.pokedex.common.helpers

data class Pager<T>(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>? = null
)