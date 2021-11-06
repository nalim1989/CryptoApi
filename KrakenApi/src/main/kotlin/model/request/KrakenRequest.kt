package model.request

import model.TradablePair
import java.lang.System.currentTimeMillis
import java.math.BigDecimal
import java.sql.Date

class KrakenBuyRequest{
    companion object {
        fun buildParamRequest(pair: TradablePair, volume:BigDecimal, price:BigDecimal, stopLossPrice:BigDecimal) : Map<String,String> {
            val uriParams = HashMap<String,String>()

            uriParams["nonce"] = currentTimeMillis().toString()
            uriParams["pair"] = pair.name
            uriParams["type"] = "buy"
            uriParams["ordertype"] = "limit"
            uriParams["volume"] = volume.toString()
            uriParams["price"] = price.toString()
            uriParams["close[ordertype]"] = "stop-loss"
            uriParams["close[price]"] = stopLossPrice.toString()

            return uriParams
        }
    }
}


class KrakenSellRequest{
    companion object {
        fun buildParamRequest(pair: TradablePair, volume:BigDecimal, price:BigDecimal) : Map<String,String> {
            val uriParams = HashMap<String,String>()

            uriParams["nonce"] = currentTimeMillis().toString()
            uriParams["pair"] = pair.name
            uriParams["type"] = "sell"
            uriParams["volume"] = volume.toString()
            uriParams["price"] = price.toString()
            uriParams["ordertype"] = "limit"

            return uriParams
        }
    }
}