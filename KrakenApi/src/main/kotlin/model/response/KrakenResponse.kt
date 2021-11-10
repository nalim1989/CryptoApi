package model.response

abstract class KrakenResponse{
    lateinit var error:List<String>
}

class KrakenServerStatusResponse: KrakenResponse() {
    lateinit var result: KrakenServerStatus
}

class KrakenSocketAuthResponse: KrakenResponse() {
    lateinit var result: SocketTokenResponse
}

class KrakenGenericResponse: KrakenResponse() {
    var result: MutableMap<String, Any> = LinkedHashMap()
}
