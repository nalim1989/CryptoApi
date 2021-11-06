package util

import model.response.KrakenGenericResponse
import model.Tick
import model.TradablePair
import java.math.BigDecimal
import kotlin.collections.ArrayList

class KrakenDataConverter {

    companion object {
        fun convertKrakenDataToTradablePairs(response: KrakenGenericResponse): List<TradablePair> {
            return response.result.entries.map { result ->
                @Suppress("UNCHECKED_CAST")
                val data: Map<String, String> = result.value as Map<String, String>
                TradablePair(data["altname"]!!, data["wsname"]!!)
            }
        }

        fun convertKrakenDataToTick(response: KrakenGenericResponse): List<Tick> {
            if (response.result.keys.size > 2){
                throw Exception("Unformatted data")
            }

            val name: String = response.result.keys.first()
            @Suppress("UNCHECKED_CAST")
            val data: ArrayList<ArrayList<String>> = response.result[name] as ArrayList<ArrayList<String>>

            return data.map { tick ->
                val timestamp = tick[0] as Int
                val openPrice:BigDecimal = tick[1].toBigDecimal()
                val highPrice:BigDecimal = tick[2].toBigDecimal()
                val lowPrice:BigDecimal = tick[3].toBigDecimal()
                val closePrice:BigDecimal = tick[4].toBigDecimal()

                Tick(name, openPrice, closePrice, highPrice, lowPrice, timestamp.toLong())
            }

        }
    }
}