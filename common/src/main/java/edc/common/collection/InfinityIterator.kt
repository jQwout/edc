package edc.common.collection

class InfinityIterator<T>(private val values: List<T>) : Iterator<T> {

    private var iterator = values.iterator()

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): T {
        return if (iterator.hasNext()) {
            iterator.next()
        } else {
            iterator = values.iterator()
            next()
        }
    }
}