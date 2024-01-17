package agh.ics.oop.model

enum class MapDirection {
    NORTH, SOUTH, WEST, EAST;

    override fun toString() = when(this) {
        NORTH -> "Północ"
        EAST -> "Wschód"
        SOUTH -> "Południe"
        WEST -> "Zachód"
    }

    fun next(): MapDirection = when(this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun previous(): MapDirection = when(this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }
}