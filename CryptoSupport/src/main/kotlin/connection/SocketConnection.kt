package connection

import model.WebSocketListener
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class SocketConnection(listener:WebSocketListener) : WebSocketClient(listener.getUri()) {

    private val listener: WebSocketListener

    init {
        this.listener=listener
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        this.send(listener.getInitialMessage())
    }

    override fun onMessage(message: String?) {
        listener.acceptMessage(message)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        this.send(listener.getCloseMessage())
    }

    override fun onError(ex: Exception?) {
        listener.acceptError(ex)
    }
}