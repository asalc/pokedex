package es.shiro.domain.helpers

class Pager<T> {

    var count: Int = 0
    var next: String? = null
    var previous: String? = null
    var results: ArrayList<T>? = null

}