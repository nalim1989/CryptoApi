package api

import model.ServerStatus
import model.TradablePair

interface CryptoExchange {

    fun getServerStatus():ServerStatus
    fun getTradablePairs():List<TradablePair>
}