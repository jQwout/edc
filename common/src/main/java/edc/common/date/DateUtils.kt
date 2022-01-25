package edc.common.date

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    val formatISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    val hhmmAmPmFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)

    val m_ISO8601Local: SimpleDateFormat = SimpleDateFormat(formatISO8601, Locale.ENGLISH)

    val dayMonthYearFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

    val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)

    fun parseUtc(dateString: String) = m_ISO8601Local.parse(dateString)

    fun parseDayMonthYear(dateString: String) = dayMonthYearFormat.parse(dateString)

    fun formatDayMonthYear(date: Date) = dayMonthYearFormat.format(date)

    fun reformatDayMonthYear(date: Date): Date = dayMonthYearFormat.parse(
        dayMonthYearFormat.format(date)
    )

    fun getMonthYearDate(date: Date): Date {
        val c = CalendarUtils.Calendar(date)
        c.set(Calendar.DAY_OF_MONTH, 0)
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        return c.time
    }
}