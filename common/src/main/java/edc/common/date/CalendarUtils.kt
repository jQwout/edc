package edc.common.date

import java.util.*

object CalendarUtils {

    fun getDayOfWeek(date: Date): Int {
        val c = Calendar.getInstance()
        c.time = date
        return c[Calendar.DAY_OF_WEEK]
    }

    fun getNumberOfMonth(date: Date): Int {
        val c = Calendar.getInstance()
        c.time = date
        return c[Calendar.DAY_OF_MONTH]
    }

    fun monthEq(date1: Date, date2: Date): Boolean {
        val c1 = Calendar(date1)
        val c2 = Calendar(date2)

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
    }

    fun dayEq(date1: Date?, date2: Date): Boolean {
        if(date1 == null) return false
        val c1 = Calendar(date1)
        val c2 = Calendar(date2)

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
    }

    fun Calendar(date: Date): Calendar {
        val c = Calendar.getInstance()
        c.time = date
        return c
    }

    fun Calendar(addDayOfMonthValue: Int): Calendar {
        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_MONTH, addDayOfMonthValue)
        return c
    }

    fun Calendar(minutes: Int, hour: Int): Calendar {
        val c = Calendar.getInstance()
        c.set(Calendar.MINUTE, minutes)
        c.set(Calendar.HOUR_OF_DAY, hour)
        return c
    }

    fun getMonthName(date: Date): String {
        return Calendar(date).getDisplayName(
            Calendar.MONTH,
            Calendar.LONG,
            Locale.ENGLISH
        )
    }

    fun getMonthNameOrNull(date: Date?): String? {
        if (date == null) return null
        return Calendar(date).getDisplayName(
            Calendar.MONTH,
            Calendar.LONG,
            Locale.ENGLISH
        )
    }

    fun getStartOfDay(date: Date): Date = CalendarUtils.Calendar(date).apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
    }.time

    fun getEndOfDay(date: Date): Date = CalendarUtils.Calendar(date).apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
    }.time

    fun getEndOfMonth(date: Date): Date = CalendarUtils.Calendar(date).apply {
        set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
    }.time

    fun getHour(date: Date?) =
        date?.let { CalendarUtils.Calendar(it).get(Calendar.HOUR_OF_DAY) } ?: 0

    fun getMinutes(date: Date?) = date?.let { CalendarUtils.Calendar(it).get(Calendar.MINUTE) } ?: 0
}
