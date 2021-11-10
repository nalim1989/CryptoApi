package connection

import com.fasterxml.jackson.databind.ObjectMapper
import constants.KrakenConstants
import model.TradablePair
import model.request.KrakenBuyRequest
import model.response.BuyResponse
import model.response.KrakenGenericResponse
import model.response.KrakenResponse
import model.response.KrakenServerStatusResponse
import okhttp3.MediaType.Companion.toMediaType
import util.HeadersBuilder
import util.KrakenSignatureCalculator
import util.UriRequestBuilder
import java.math.BigDecimal


class KrakenConnection {

    private val baseUri = "https://api.kraken.com"
    private val publicUri = "$baseUri/0/public"
    private val privateUri = "$baseUri/0/private"
    private val mediaType = "application/x-www-form-urlencoded".toMediaType()
    private val connection: RestConnection = RestConnection()
    private val objectMapper = ObjectMapper()

    fun getServerStatus(): KrakenServerStatusResponse {
        val uri = "$publicUri/SystemStatus"
        val response = objectMapper.readValue(connection.get(uri), KrakenServerStatusResponse::class.java)
        return checkAndReturnResponse(response) as KrakenServerStatusResponse
    }

    fun getTradablePairs(): KrakenGenericResponse {
        val uri = "$publicUri/AssetPairs"
        val response = objectMapper.readValue(connection.get(uri), KrakenGenericResponse::class.java)
        return checkAndReturnResponse(response) as KrakenGenericResponse
    }

    fun getTick(pair:TradablePair, last:String?): KrakenGenericResponse {
        var uri = "$publicUri/OHLC?pair=" + pair.name

        if(last != null){
            uri += "&since=$last"
        }

        val response = objectMapper.readValue(connection.get(uri), KrakenGenericResponse::class.java)
        return checkAndReturnResponse(response) as KrakenGenericResponse
    }

    fun buy(pair:TradablePair, volume:BigDecimal, limitPrice:BigDecimal, stopLossPrice:BigDecimal):BuyResponse{
        val uri = "$privateUri/AddOrder"

        val params = KrakenBuyRequest.buildParamRequest(pair,volume,limitPrice,stopLossPrice)
        val body = UriRequestBuilder.build(params,mediaType)

        val headersMap = HashMap<String,String>(KrakenConstants.REST_HEADERS)
        headersMap["API-Sign"]=KrakenSignatureCalculator.calculateSignature("/0/private/AddOrder",params)
        val headers = HeadersBuilder.build(headersMap)

        val response = objectMapper.readValue(connection.post(uri,body,headers), BuyResponse::class.java)
        return checkAndReturnResponse(response) as BuyResponse

    }

    private fun checkAndReturnResponse(response: KrakenResponse): KrakenResponse {
        if(response.error.isNotEmpty()){
            throw  Exception("Kraken exchange exception: " + response.error.joinToString(separator = "; "))
        }
        return response
    }
}