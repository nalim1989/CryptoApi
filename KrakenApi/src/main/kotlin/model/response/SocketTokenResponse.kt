package model.response

import kotlin.properties.Delegates

class SocketTokenResponse: KrakenResponse() {
    lateinit var token:String
    var expires: Int = 0
}