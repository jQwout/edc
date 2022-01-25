package edc.domain.model.api

import java.util.*


class LatestPayload(
    val date: Date,
    val base: String,
    val rates: Map<String, Double>
)

/**
{
"disclaimer": "https://www.cbr-xml-daily.ru/#terms",
"date": "2022-01-22",
"timestamp": 1642809600,
"base": "RUB",
"rates": {
"AUD": 0.018128,
"AZN": 0.022154,
"GBP": 0.009615,
"AMD": 6.26806,
"BYN": 0.033551,
"BGN": 0.022492,
"BRL": 0.070669,
"HUF": 4.106776,
"HKD": 0.101554,
"DKK": 0.085586,
"USD": 0.013039,
"EUR": 0.011507,
"INR": 0.971015,
"KZT": 5.687797,
"CAD": 0.016337,
"KGS": 1.105864,
"CNY": 0.082664,
"MDL": 0.235818,
"NOK": 0.115213,
"PLN": 0.052012,
"RON": 0.056868,
"XDR": 0.009298,
"SGD": 0.017552,
"TJS": 0.147281,
"TRY": 0.174965,
"TMT": 0.045573,
"UZS": 141.283447,
"UAH": 0.369424,
"CZK": 0.279181,
"SEK": 0.119707,
"CHF": 0.011922,
"ZAR": 0.197568,
"KRW": 15.541996,
"JPY": 1.48552
}
}
 */