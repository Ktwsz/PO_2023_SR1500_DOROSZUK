package agh.ics.oop.model

interface IMoveValidator {
    fun canMoveTo(position: Vector2d): Boolean
}