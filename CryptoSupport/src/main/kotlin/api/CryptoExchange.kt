package api

import model.Order
import model.ServerStatus
import model.TradablePair
import java.math.BigDecimal

interface CryptoExchange {

    fun getServerStatus():ServerStatus
    fun getTradablePairs():List<TradablePair>
    fun buy(pair:TradablePair, volume:BigDecimal, limitPrice:BigDecimal, stopLossPrice:BigDecimal): Order
}