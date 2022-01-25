package edc.common.collection

fun <T> listBuilder(action: MutableList<T>.() -> Unit) : List<T> {
    return mutableListOf<T>().apply {
        action()
    }
}