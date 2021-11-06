package model

interface TickListener {

    fun acceptTicks(ticks:List<Tick>)
}