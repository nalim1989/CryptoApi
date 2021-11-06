package factory

import api.CryptoExchange
import logic.KrakenExchange
import model.CryptoExchanges

object CryptoExchangeFactory {

    fun createExchange(exchange: CryptoExchanges):CryptoExchange{
        when (exchange) {
            CryptoExchanges.KRAKEN -> return KrakenExchange()
            else -> {
                throw RuntimeException("Not allowed")
            }
        }
    }
}