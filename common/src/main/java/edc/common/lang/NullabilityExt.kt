package edc.common.lang

fun <T : Any> T?.required() = checkNotNull(this)
fun <T : Any> T?.required(lazyMessage: () -> Any) = checkNotNull(this, lazyMessage)
fun <T : Any> T?.isNotNull() = this != null