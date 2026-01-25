package es.shiro.data.helpers

class Pager<T>(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: ArrayList<T>? = null
)