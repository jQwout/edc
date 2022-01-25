package edc.common.date


object WeekDaysUtils {

    fun getFirstSymbol(day: String): String {
        return if (day.equals("Thursday", true)) {
            "R"
        } else {
            day.first().toString()
        }
    }

    fun getFirstSymbols(days: List<String>): String {
        return days
            .map { getFirstSymbol(it) }
            .reduce { acc, s -> "$acc/$s" }
    }
}