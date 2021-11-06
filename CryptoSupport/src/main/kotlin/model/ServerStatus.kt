package model

import kotlin.properties.Delegates

class ServerStatus(isOnline:Boolean) {

    var isOnline:Boolean

    init {
        this.isOnline=isOnline
    }
}