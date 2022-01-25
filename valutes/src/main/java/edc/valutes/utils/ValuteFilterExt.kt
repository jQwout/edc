package edc.valutes.utils

import edc.domain.model.api.Valute

val byCode: (Valute) -> Comparable<String> = { it: Valute -> it.charCode }
val byValue: (Valute) -> Comparable<Float> = { it: Valute -> it.value / it.nominal }