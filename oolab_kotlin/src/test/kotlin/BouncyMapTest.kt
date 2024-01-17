import agh.ics.oop.model.Animal
import agh.ics.oop.model.BouncyMap
import agh.ics.oop.model.MoveDirection
import agh.ics.oop.model.Vector2d
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

fun generateMap(): BouncyMap {
    val animal1 = Animal(Vector2d(1, 1))
    val animal2 = Animal(Vector2d(0, 0))
    val animal3 = Animal(Vector2d(1, 0))
    val map = BouncyMap(Vector2d(3, 3))
    map.place(animal1)
    map.place(animal2)
    map.place(animal3)

    return map
}

class BouncyMapTest : FunSpec({
    test("Place") {
        val animal1 = Animal(Vector2d(0, 0))
        val animal2 = Animal(Vector2d(0, 0))
        val animal3 = Animal(Vector2d(1, 0))
        val map = BouncyMap(Vector2d(3, 3))

        map.place(animal1)
        map.place(animal2)
        map.place(animal3)

        map.objectAt(Vector2d(0, 0)) shouldBe animal2
        map.objectAt(Vector2d(1, 0)) shouldBe animal3
    }

    test("Move") {
        val animal1 = Animal(Vector2d(1, 1))
        val animal2 = Animal(Vector2d(0, 0))
        val animal3 = Animal(Vector2d(1, 0))
        val map = BouncyMap(Vector2d(3, 3))
        map.place(animal1)
        map.place(animal2)
        map.place(animal3)

        map.move(animal1, MoveDirection.LEFT)
        map.move(animal1, MoveDirection.FORWARD)
        map.move(animal1, MoveDirection.FORWARD)

        map.move(animal2, MoveDirection.BACKWARD)


        map.move(animal3, MoveDirection.RIGHT)
        map.move(animal3, MoveDirection.FORWARD)

        forAll(
            row(Vector2d(0, 1), animal1),
            row(Vector2d(0, 0), animal2),
            row(Vector2d(2, 0), animal3)
        ) { pos, res -> map.objectAt(pos) shouldBe res }
    }

    test("isOccupied") {
        val map = generateMap()

        forAll(
            row(Vector2d(1, 1), true),
            row(Vector2d(0, 0), true),
            row(Vector2d(1, 0), true),
            row(Vector2d(0, 1), false),
            row(Vector2d(2, 1), false)
        ) { pos, result -> map.isOccupied(pos) shouldBe result }
    }

    test("objectAt") {
        val map = generateMap()

        forAll(
            row(Vector2d(1, 1), Animal(Vector2d(1, 1))),
            row(Vector2d(0, 0), Animal(Vector2d(0, 0))),
            row(Vector2d(0, 1), null),
            row(Vector2d(2, 1), null)
        ) { pos, anim -> map.objectAt(pos) shouldBe anim }
    }
})