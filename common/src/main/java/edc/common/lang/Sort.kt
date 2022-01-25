package edc.common.lang

class Sort<T : Any>(
    val ordering: Ordering,
    val keySelector: (T) -> Comparable<*>
) {
    enum class Ordering { ASC, DESC }
}