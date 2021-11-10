package model.response

import java.util.*

class OrderResponse: KrakenResponse() {

    lateinit var descr:DetailedOrderData
    lateinit var txid: String
}

class DetailedOrderData{

    lateinit var order:String
    lateinit var close:String
}