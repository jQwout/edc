package edc.common.dto

import androidx.annotation.Keep

@Keep
class Page<T : Any>(
    val totalItemsCount: Int,
    val pageNumber: Int,
    val hasMorePages: Boolean,
    val items: List<T>
)