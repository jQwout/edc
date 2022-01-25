package edc.common.network.http.header

object Headers {
    const val CACHE_HEADER = "Cache-Control"

    const val CACHE_NO_CACHE = "no-cache"
    const val CACHE_ONE_MIN = "public, max-age=60"
}