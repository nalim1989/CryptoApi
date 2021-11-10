package model

import java.net.URI

interface WebSocketListener {

    fun getUri(): URI
    fun getInitialMessage():String
    fun getCloseMessage():String
    fun acceptMessage(message:String?)
    fun acceptError(error:Exception?)
}