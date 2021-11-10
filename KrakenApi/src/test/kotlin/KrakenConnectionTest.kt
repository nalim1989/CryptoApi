import connection.KrakenConnection
import model.TradablePair
import org.testng.Assert
import org.testng.annotations.Test
import java.math.BigDecimal

class KrakenConnectionTest {
    
    @Test
    public fun testServerStatus(){
        val connection = KrakenConnection()
        
        val response = connection.getServerStatus()
        Assert.assertNotNull(response)
        println(response.result.status)
        println(response.result.timestamp)
    }

    @Test
    public fun testTradablePairs(){
        val connection = KrakenConnection()

        val response = connection.getTradablePairs()
        Assert.assertNotNull(response)

        response.result.forEach { detail -> println(detail.key + " " + detail.value) }
    }

    @Test(expectedExceptions = [Exception::class])
    public fun testTickException(){
        val connection = KrakenConnection()
        connection.getTick(pair = TradablePair("USDBTC", "USD/BTC"), null)
    }

    @Test
    public fun testTick(){
        val connection = KrakenConnection()
        val response = connection.getTick(pair = TradablePair("BATUSD", "BAT/USD"), null)
        Assert.assertNotNull(response)

        response.result.forEach { detail -> println(detail.key + " " + detail.value) }
    }


    @Test
    public fun testBuy(){
        val connection = KrakenConnection()
        val response = connection.buy(TradablePair("XXBTZUSD", "XXBTZ/USD"), BigDecimal(1), BigDecimal(2), BigDecimal(1))

        Assert.assertNotNull(response)
    }

    @Test
    public fun testSell(){
        val connection = KrakenConnection()
        val response = connection.sell(TradablePair("XXBTZUSD", "XXBTZ/USD"), BigDecimal(1), BigDecimal(2))

        Assert.assertNotNull(response)
    }
}