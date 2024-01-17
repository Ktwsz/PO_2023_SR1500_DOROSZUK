package agh.ics.oop.model

class BouncyMap(val bounds: Vector2d) : IWorldMap {
    private val animalMap: MutableMap <Vector2d, Animal> = HashMap()

    override fun place(animal: Animal) {
        if (!isOccupied(animal.position)) {
            animalMap[animal.position] = animal
            return
        }

        val newPosition: Vector2d? = animalMap.randomFreePosition(bounds)
        if (newPosition == null) {
            animalMap.randomPosition()?.let {animalMap[it] = animal}
        } else {
            animalMap[newPosition] = animal
        }
    }

    override fun move(animal: Animal, moveDirection: MoveDirection) {
        animalMap.remove(animal.position)
        animal.move(moveDirection, this)
        place(animal)
    }

    override fun isOccupied(position: Vector2d): Boolean = objectAt(position) != null

    override fun objectAt(position: Vector2d): Animal? = animalMap[position]

    override fun canMoveTo(position: Vector2d): Boolean = position.follows(Vector2d(0, 0)) && position.precedes(bounds - Vector2d(1, 1))
}