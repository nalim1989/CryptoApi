package connection

import com.fasterxml.jackson.databind.ObjectMapper
import constants.KrakenConstants
import model.TradablePair
import model.request.KrakenBuyRequest
import model.request.KrakenSellRequest
import model.response.*
import okhttp3.MediaType.Companion.toMediaType
import util.HeadersBuilder
import util.KrakenSignatureCalculator
import util.UriRequestBuilder
import java.math.BigDecimal


class KrakenConnection {

    private val baseUri = "https://api.kraken.com"
    private val privateUriSuffix = "/0/private"
    private val publicUriSuffix = "/0/public"
    private val publicUri = "$baseUri$publicUriSuffix"
    private val privateUri = "$baseUri$privateUriSuffix"
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

    fun buy(pair:TradablePair, volume:BigDecimal, limitPrice:BigDecimal, stopLossPrice:BigDecimal):OrderResponse{
        val params = KrakenBuyRequest.buildParamRequest(pair,volume,limitPrice,stopLossPrice)
        return addOrder(params)
    }

    fun sell(pair:TradablePair, volume:BigDecimal, limitPrice:BigDecimal):OrderResponse{
        val params = KrakenSellRequest.buildParamRequest(pair, volume, limitPrice)
        return addOrder(params)
    }

    private fun addOrder(params:Map<String,String>):OrderResponse{
        val orderUri="/AddOrder"
        val uri = "$privateUri$orderUri"

        val body = UriRequestBuilder.build(params,mediaType)

        val headersMap = HashMap<String,String>(KrakenConstants.REST_HEADERS)
        headersMap["API-Sign"]=KrakenSignatureCalculator.calculateSignature("$privateUriSuffix$orderUri",params)
        val headers = HeadersBuilder.build(headersMap)

        val response = objectMapper.readValue(connection.post(uri,body,headers), OrderResponse::class.java)
        return checkAndReturnResponse(response) as OrderResponse
    }

    private fun checkAndReturnResponse(response: KrakenResponse): KrakenResponse {
        if(response.error.isNotEmpty()){
            throw  Exception("Kraken exchange exception: " + response.error.joinToString(separator = "; "))
        }
        return response
    }
}