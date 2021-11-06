import org.testng.Assert
import org.testng.annotations.Test
import util.KrakenSignatureCalculator

class KrakenSignatureCalculatorTest {

    @Test
    public fun testSignatureCalculation(){
        val path = "/0/private/TradeBalance"
        val data = mapOf("nonce" to "1540973848000", "asset" to "xbt")

        val signature = KrakenSignatureCalculator.calculateSignature(path,data)
        Assert.assertEquals(signature, "RdQzoXRC83TPmbERpFj0XFVArq0Hfadm0eLolmXTuN2R24hzIqtAnF/f7vSfW1tGt7xQOn8bjm+Ht+X0KrMwlA==")
    }
}