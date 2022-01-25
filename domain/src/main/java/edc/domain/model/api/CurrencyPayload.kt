package edc.domain.model.api

import com.google.gson.annotations.SerializedName
import java.util.*


class DailyPayload(
    @SerializedName("Date")
    val date: Date,
    @SerializedName("PreviousDate")
    val previousDate: Date,
    @SerializedName("Valute")
    val valute: Map<String, Valute>
)

class Valute(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Float,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Float,
    @SerializedName("Previous")
    val previous: Float
)

/**
 * {
"Date": "2022-01-22T11:30:00+03:00",
"PreviousDate": "2022-01-21T11:30:00+03:00",
"PreviousURL": "\/\/www.cbr-xml-daily.ru\/archive\/2022\/01\/21\/daily_json.js",
"Timestamp": "2022-01-21T23:00:00+03:00",
"Valute": {
"AUD": {
"ID": "R01010",
"NumCode": "036",
"CharCode": "AUD",
"Nominal": 1,
"Name": "Австралийский доллар",
"Value": 55.1633,
"Previous": 55.3355
},
"AZN": {
"ID": "R01020A",
"NumCode": "944",
"CharCode": "AZN",
"Nominal": 1,
"Name": "Азербайджанский манат",
"Value": 45.1385,
"Previous": 44.9916
}
}
}
 */