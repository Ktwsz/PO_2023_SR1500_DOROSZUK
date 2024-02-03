package agh.ics.oop.model

interface IWorldMap : IMoveValidator {
    fun place(animal: Animal)
    fun move(animal: Animal, moveDirection: MoveDirection)
    fun isOccupied(position: Vector2d): Boolean
    fun objectAt(position: Vector2d): Animal?
}