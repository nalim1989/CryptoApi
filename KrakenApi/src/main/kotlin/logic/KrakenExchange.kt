package logic

import api.CryptoExchange
import connection.KrakenConnection
import model.ServerStatus
import model.Tick
import model.TickListener
import model.TradablePair
import util.KrakenDataConverter
import kotlin.concurrent.thread

class KrakenExchange:CryptoExchange {

    private val connection = KrakenConnection()
    private var listeners: MutableMap<TradablePair, Thread> =  HashMap()

    override fun getServerStatus(): ServerStatus {
        val response = connection.getServerStatus()
        return ServerStatus(response.result.status.equals("online", ignoreCase = true))
    }

    override fun getTradablePairs(): List<TradablePair> {
        val response = connection.getTradablePairs()
        return KrakenDataConverter.convertKrakenDataToTradablePairs(response)
    }

    fun getTick(pair:TradablePair, last:String?): List<Tick> {
        val response = connection.getTick(pair, last)
        return KrakenDataConverter.convertKrakenDataToTick(response)
    }

    fun subscribeToTick(pair:TradablePair, listener: TickListener){

        val thread = Thread {
            var lastTickTime: String? =null
            while(true) {
                val ticks = getTick(pair, lastTickTime)
                val currentLastTickTime= ticks.stream().max(Comparator.comparing(Tick::timestamp)).get().timestamp.toString()
                if(currentLastTickTime != lastTickTime) {
                    listener.acceptTicks(ticks)
                    lastTickTime=currentLastTickTime
                }
                Thread.sleep(2000)
            }
        }
        listeners[pair] = thread
        thread.start()
    }

    fun stopListeningToTick(pair:TradablePair){
        try {
            listeners[pair]?.interrupt()
        } catch (e: Exception){
            // Do nothing
        }

        listeners.remove(pair)
    }

    fun stopAllListening(){
        try {
            listeners.values.forEach{ thread -> thread.interrupt()}
        } catch (e:Exception){
            // Do nothing
        }

        listeners.clear()
    }
}