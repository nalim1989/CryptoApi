import connection.KrakenConnection
import logic.KrakenExchange
import model.Tick
import model.TickListener
import model.TradablePair
import org.testng.Assert
import org.testng.annotations.Test

class KrakenExchangeTest: TickListener {

    @Test
    public fun testTick(){
        val exchange = KrakenExchange()

        val response = exchange.getTick(TradablePair("BATUSD", "BAT/USD"), null)
        Assert.assertNotNull(response)
    }

    @Test
    public fun testSubscribe(){
        val exchange = KrakenExchange()

        exchange.subscribeToTick(TradablePair("BATUSD", "BAT/USD"), this)
        Thread.sleep(120000)
        exchange.stopAllListening()
    }

    override fun acceptTicks(ticks: List<Tick>) {
        ticks.forEach { tick -> println(tick.stockName + " " + tick.high) }
    }
}