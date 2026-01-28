package es.shiro.pokedex.common.helpers

import es.shiro.pokedex.common.extensions.EMPTY_STRING

data class Pager<T>(
    val count: Int = 0,
    val next: String = String.EMPTY_STRING,
    val previous: String = String.EMPTY_STRING,
    val results: List<T> = emptyList()
)