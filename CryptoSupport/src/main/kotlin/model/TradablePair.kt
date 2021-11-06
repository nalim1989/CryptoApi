package model

import java.util.*

class TradablePair(name:String, display:String) {

     var name: String
     var display: String

    init{
        this.name=name
        this.display=display
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TradablePair) return false

        if (name != other.name) return false
        if (display != other.display) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + display.hashCode()
        return result
    }


}