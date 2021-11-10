package model

import java.math.BigDecimal

class Order(
    val orderId:String,
    val pair: TradablePair,
    val volume:BigDecimal)  {
}