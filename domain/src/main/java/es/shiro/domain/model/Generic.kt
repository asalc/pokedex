package es.shiro.domain.model

class Generic(
    val name: String,
    val url: String
)

open class GenericId(
    open val id: Int
)