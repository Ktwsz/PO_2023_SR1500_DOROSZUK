package agh.ics.oop.model

fun Map<Vector2d, Any> .randomPosition(): Vector2d? = keys.randomOrNull()

fun Map<Vector2d, Any> .randomFreePosition(bounds: Vector2d): Vector2d? {
    val vectorList: MutableList <Vector2d> = ArrayList()
    for (i in 0..bounds.x) {
        for (j in 0..bounds.y) {
            if (!containsKey(Vector2d(i, j)))
                vectorList.add(Vector2d(i, j))
        }
    }

    if (vectorList.isEmpty()) return null
    return vectorList[(0..<vectorList.size).random()]
}