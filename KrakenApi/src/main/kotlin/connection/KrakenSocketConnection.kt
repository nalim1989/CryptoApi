package connection

import com.fasterxml.jackson.databind.ObjectMapper
import model.TradablePair
import model.WebSocketListener
import model.request.KrakenSubscriptionWebSocketRequest
import model.request.KrakenUnsubscriptionWebSocketRequest
import model.response.KrakenGenericResponse
import model.response.SocketOlhcResponse
import java.net.URI


class KrakenSocketConnection(val pair:TradablePair, val messageArrived: (input: SocketOlhcResponse) -> Unit, val errorArrived: (input: Exception) -> Unit):WebSocketListener {

    private val publicUri = "wss://ws.kraken.com"
    private val objectMapper = ObjectMapper()

    override fun getUri(): URI{
        return URI(publicUri)
    }

    override fun getInitialMessage(): String {
        val request = KrakenSubscriptionWebSocketRequest(listOf(pair))
        return objectMapper.writeValueAsString(request)
    }

    override fun getCloseMessage(): String {
        val request = KrakenUnsubscriptionWebSocketRequest(listOf(pair))
        return objectMapper.writeValueAsString(request)
    }

    override fun acceptMessage(message: String?) {
        if(message != null){
            val response = objectMapper.readValue(message, SocketOlhcResponse::class.java)
            messageArrived(response)
        }
    }

    override fun acceptError(error: Exception?) {
        if(error != null) {
            errorArrived(error)
        }
    }

}