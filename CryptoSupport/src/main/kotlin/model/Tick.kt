package model

import java.math.BigDecimal
import java.math.BigInteger
import java.time.Instant
import java.util.*

class Tick (
    val stockName: String,
    val open: BigDecimal,
    val close: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val timestamp: Long){

}
