package model.response

import java.util.*

class BuyResponse: KrakenResponse() {

    lateinit var descr:DetailedBuyData
    lateinit var txid: Date
}

class DetailedBuyData{

    lateinit var order:String
    lateinit var close:String
}