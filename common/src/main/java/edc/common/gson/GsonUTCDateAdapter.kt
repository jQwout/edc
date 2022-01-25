package edc.common.gson

import edc.common.date.DateUtils
import com.google.gson.*
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GsonUTCDateAdapter : JsonSerializer<Date?>, JsonDeserializer<Date?> {

    private val dateFormat: SimpleDateFormat = DateUtils.m_ISO8601Local.apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    @Synchronized
    override fun serialize(
        date: Date?,
        type: Type?,
        jsonSerializationContext: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(dateFormat.format(date))
    }

    @Synchronized
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type?,
        jsonDeserializationContext: JsonDeserializationContext?
    ): Date {
        return try {
            dateFormat.parse(jsonElement.getAsString())
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }
}