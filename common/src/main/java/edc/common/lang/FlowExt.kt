package edc.common.lang

fun <T: Any> equalsByClass(old: T, new: T) : Boolean {
    return old.javaClass.simpleName == new.javaClass.simpleName
}