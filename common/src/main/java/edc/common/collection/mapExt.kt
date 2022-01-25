package edc.common.collection

fun <K, V> mapBuilder(action: MutableMap<K, V>.() -> Unit): Map<K, V> {
    return mutableMapOf<K, V>().apply {
        action()
    }
}