package model.request

import model.TradablePair

class KrakenSubscriptionWebSocketRequest(var pair: List<TradablePair>){
    val event: String ="subscribe"
    val subscription:KrakenWebSocketSubscription=KrakenWebSocketSubscription()
}

class KrakenUnsubscriptionWebSocketRequest(var pair: List<TradablePair>){
    val event: String ="unsubscribe"
    val subscription:KrakenWebSocketSubscription=KrakenWebSocketSubscription()
}

class KrakenWebSocketSubscription(){
    val name:String="ohlc"
}