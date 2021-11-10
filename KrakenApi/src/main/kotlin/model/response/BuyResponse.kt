package model.response

import java.util.*

class BuyResponse: KrakenResponse() {

    lateinit var descr:DetailedBuyData
    lateinit var txid: String
}

class DetailedBuyData{

    lateinit var order:String
    lateinit var close:String
}