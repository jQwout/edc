package edc.common.gson

import com.google.gson.GsonBuilder
import java.util.*

object GsonUTCBuilder {
    val gsonBuilderWithUTC =
        GsonBuilder().registerTypeAdapter(Date::class.java, GsonUTCDateAdapter())
}