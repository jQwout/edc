package edc.common.date

import java.util.*

fun Date.getMin(): Long {
    return time / (60 * 1000)
}