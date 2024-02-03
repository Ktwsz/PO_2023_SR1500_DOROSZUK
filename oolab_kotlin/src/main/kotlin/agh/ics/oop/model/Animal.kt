package agh.ics.oop.model

data class Animal(var position: Vector2d = Vector2d(2, 2)) {
    var direction: MapDirection = MapDirection.NORTH
        private set

    override fun toString() = when(direction) {
        MapDirection.NORTH -> "^"
        MapDirection.SOUTH -> "v"
        MapDirection.EAST ->">"
        MapDirection.WEST -> "<"
    }

    fun move(direction: MoveDirection, moveValidator: IMoveValidator) {
        if (direction == MoveDirection.LEFT) {
            this.direction = this.direction.previous()
            return
        }
        if (direction == MoveDirection.RIGHT) {
            this.direction = this.direction.next()
            return
        }

        val moveVector = if (direction == MoveDirection.BACKWARD) -this.direction.toUnitVector()
                         else this.direction.toUnitVector()

        if (moveValidator.canMoveTo(position + moveVector))
            position += moveVector
    }
}
