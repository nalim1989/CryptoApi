import connection.KrakenRestConnection
import model.TradablePair
import org.testng.Assert
import org.testng.annotations.Test
import java.math.BigDecimal

class KrakenRestConnectionTest {
    
    @Test
    fun testServerStatus(){
        val connection = KrakenRestConnection()
        
        val response = connection.getServerStatus()
        Assert.assertNotNull(response)
        println(response.result.status)
        println(response.result.timestamp)
    }

    @Test
    fun testTradablePairs(){
        val connection = KrakenRestConnection()

        val response = connection.getTradablePairs()
        Assert.assertNotNull(response)

        response.result.forEach { detail -> println(detail.key + " " + detail.value) }
    }

    @Test(expectedExceptions = [Exception::class])
    fun testTickException(){
        val connection = KrakenRestConnection()
        connection.getTick(pair = TradablePair("USDBTC", "USD/BTC"), null)
    }

    @Test
    fun testTick(){
        val connection = KrakenRestConnection()
        val response = connection.getTick(pair = TradablePair("BATUSD", "BAT/USD"), null)
        Assert.assertNotNull(response)

        response.result.forEach { detail -> println(detail.key + " " + detail.value) }
    }


    @Test
    fun testBuy(){
        val connection = KrakenRestConnection()
        val response = connection.buy(TradablePair("XXBTZUSD", "XXBTZ/USD"), BigDecimal(1), BigDecimal(2), BigDecimal(1))

        Assert.assertNotNull(response)
    }

    @Test
    fun testSell(){
        val connection = KrakenRestConnection()
        val response = connection.sell(TradablePair("XXBTZUSD", "XXBTZ/USD"), BigDecimal(1), BigDecimal(2))

        Assert.assertNotNull(response)
    }

    @Test
    fun testSocketToken(){
        val connection = KrakenRestConnection()
        val response = connection.getSocketAuth()

        Assert.assertNotNull(response)
    }
}