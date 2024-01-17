package agh.ics.oop.model

import kotlin.math.max
import kotlin.math.min

data class Vector2d(val x: Int, val y: Int) {
    fun precedes(other: Vector2d): Boolean = x <= other.x && y <= other.y

    fun follows(other: Vector2d): Boolean = x >= other.x && y >= other.y

    fun add(other: Vector2d): Vector2d = Vector2d(x + other.x, y + other.y)

    fun subtract(other: Vector2d): Vector2d = Vector2d(x - other.x, y - other.y)

    fun upperRight(other: Vector2d): Vector2d = Vector2d(max(x, other.x), max(y, other.y))

    fun lowerLeft(other: Vector2d): Vector2d = Vector2d(min(x, other.x), min(y, other.y))

    fun opposite(): Vector2d = Vector2d(-x, -y)

    override fun toString(): String = "($x,$y)"
    operator fun unaryMinus() = opposite()
    operator fun plus(other: Vector2d) = add(other)
    operator fun minus(other: Vector2d) = subtract(other)
}

fun MapDirection.toUnitVector(): Vector2d = when(this) {
    MapDirection.NORTH -> Vector2d(0, 1)
    MapDirection.SOUTH -> Vector2d(0, -1)
    MapDirection.EAST -> Vector2d(1, 0)
    MapDirection.WEST -> Vector2d(-1, 0)
}