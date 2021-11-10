package model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.math.BigDecimal

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder("channelID", "data", "channelName", "pair")
class SocketOlhcResponse{
     var channelID:Int=0
     lateinit var data:OlhcData
     lateinit var channelName:String
     lateinit var pair:String
}

@JsonPropertyOrder("time", "etime", "open", "high", "low", "close", "vwap", "volume", "count")
class OlhcData{
     lateinit var time:BigDecimal
     lateinit var etime:BigDecimal
     lateinit var open:BigDecimal
     lateinit var high:BigDecimal
     lateinit var low:BigDecimal
     lateinit var close:BigDecimal
     lateinit var vwap:BigDecimal
     lateinit var volume:BigDecimal
     lateinit var count:BigDecimal
}