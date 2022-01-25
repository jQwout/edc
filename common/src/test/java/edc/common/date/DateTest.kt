package edc.common.date

import org.junit.Test
import java.util.*

class DateTest {

    @Test
    fun utcTest() {
        val dateStr = "2021-07-14T22:10:00.0000000Z"
        val f = DateUtils.parseUtc(dateStr)

        println(f)
    }

    @Test
    fun amPmTest() {
        val dateStr = "2021-07-14T22:10:00.0000000Z"
        val f = DateUtils.parseUtc(dateStr)
        val end = f.time - 50 * 60 * 1000
        val date = Date()
        date.time = end

        println(DateUtils.hhmmAmPmFormat.format(date))
    }

    @Test
    fun timezoneTest() {
        val dateStr = "2021-07-14T22:10:00.0000000Z"
        val date = DateUtils.m_ISO8601Local.apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.parse(dateStr)


        println(DateUtils.hhmmAmPmFormat.format(date))
    }

    @Test
    fun timezone2Test() {
        val dateStr = "2021-07-14T22:10:00.0000000Z"
        val date = DateUtils.m_ISO8601Local.apply {
            timeZone = TimeZone.getTimeZone("CST")
        }.parse(dateStr)


        println(DateUtils.hhmmAmPmFormat.format(date))
    }
}