package util

import constants.KrakenConstants
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.xml.crypto.dsig.SignatureMethod.HMAC_SHA512

class KrakenSignatureCalculator {
    companion object{
        fun calculateSignature(path:String, data:Map<String,String>):String{

            val nonce=data["nonce"]
            val flatData:String = data.flatMap { (key, value) -> listOf("$key=$value") }.joinToString("&")

            val sha256Data= SecurityUtils.calculateSha256(nonce+flatData)

            val decodedKeyBytes = Base64.getDecoder().decode(KrakenConstants.KRAKEN_PRIVATE_KEY)

            val sha512Hmac = Mac.getInstance("HmacSHA512").also { it.init(SecretKeySpec(decodedKeyBytes, HMAC_SHA512)) }
                .doFinal(path.toByteArray(StandardCharsets.UTF_8) + sha256Data)

            return Base64.getEncoder().encodeToString(sha512Hmac)
        }

    }


}