package edc.common.fastadapter.ext

import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import edc.common.fastadapter.items.EmptyItem
import edc.common.fastadapter.items.ErrorWithRefreshItem
import edc.common.fastadapter.items.LoaderItem

fun ItemAdapter<GenericItem>.setOrEmptyItem(list: List<GenericItem>) {
    if (list.isEmpty()) this.set(listOf(EmptyItem)) else this.set(list)
}

fun ItemAdapter<GenericItem>.setEmptyItem() {
    set(listOf(EmptyItem))
}

fun ItemAdapter<GenericItem>.setErrorItemWithRepeat(throwable: Throwable) {
    this.setAsList(ErrorWithRefreshItem)
}

fun ItemAdapter<GenericItem>.setLoaderItem() {
    this.setAsList(LoaderItem)
}

private fun ItemAdapter<GenericItem>.setAsList(item: GenericItem) {
    this.set(listOf(item))
}