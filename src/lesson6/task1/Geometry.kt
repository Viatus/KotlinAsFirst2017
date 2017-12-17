@file:Suppress("UNUSED_PARAMETER")

package lesson6.task1

import lesson1.task1.sqr

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = Math.sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return Math.sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        val newDistance = this.center.distance(other.center) - this.radius - other.radius
        return if (newDistance > 0) newDistance
        else 0.0
    }


    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = Math.pow(p.x - this.center.x, 2.0) + Math.pow(p.y - this.center.y, 2.0) <= Math.pow(this.radius, 2.0)
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
            other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    fun length() = this.begin.distance(this.end)
    override fun hashCode() =
            begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    if (points.size < 2) throw IllegalArgumentException()
    var max = Segment(points[0], points[1])
    var maxLen = max.length()
    for ((i, firstPoint) in points.withIndex()) {
        for (secondPoint in points.asSequence().drop(i + 1)) {
            val newDistance = firstPoint.distance(secondPoint)
            if (maxLen < newDistance) {
                max = Segment(firstPoint, secondPoint)
                maxLen = newDistance
            }
        }
    }
    return max
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val rad = diameter.length() / 2
    val center = Point((diameter.begin.x + diameter.end.x) / 2, (diameter.begin.y + diameter.end.y) / 2)
    return Circle(center, rad)
}

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
    init {
        assert(angle >= 0 && angle < Math.PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * Math.cos(angle) - point.x * Math.sin(angle), angle)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point {
        val dSin = Math.sin(this.angle - other.angle)
        val x = (other.b * Math.cos(this.angle) - this.b * Math.cos(other.angle)) / dSin
        val y = (other.b * Math.sin(this.angle) - this.b * Math.sin(other.angle)) / dSin
        return Point(x, y)
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${Math.cos(angle)} * y = ${Math.sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    var newAngle = Math.atan((s.end.y - s.begin.y) / (s.end.x - s.begin.x)) % Math.PI
    if (newAngle < 0) newAngle += Math.PI
    return Line(s.begin, newAngle)
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = lineBySegment(Segment(a, b))


/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val biPoint = Point((a.x + b.x) / 2, (a.y + b.y) / 2)
    val biAngle = (lineByPoints(a, b).angle + Math.PI / 2) % Math.PI
    return Line(biPoint, biAngle)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()
    var min = circles[0].distance(circles[1])
    var minCircles = Pair(circles[0], circles[1])
    for ((i, firstCircle) in circles.withIndex()) {
        for (secondCircle in circles.asSequence().drop(i + 1)) {
            val newDistance = firstCircle.distance(secondCircle)
            if (min > newDistance) {
                min = newDistance
                minCircles = Pair(firstCircle, secondCircle)
            }
        }
    }
    return minCircles

}

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val firstBiSec = bisectorByPoints(a, b)
    val secondBiSec = bisectorByPoints(c, a)
    val center = firstBiSec.crossPoint(secondBiSec)
    val radius = center.distance(a)
    return Circle(center, radius)
}

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle {
    if (points.isEmpty()) throw IllegalArgumentException()
    if (points.size == 1) return Circle(points[0], 0.0)
    if (points.size == 2) return circleByDiameter(Segment(points[0], points[1]))
    var circle = Circle(Point(0.0, 0.0), 0.0)
    var minRad = Double.MAX_VALUE
    var newCircle = circleByDiameter(diameter(*points))
    if (isAllPointsContains(points, newCircle, listOf(diameter(*points).begin, diameter(*points).end))
            && newCircle.radius <= minRad) {
        circle = newCircle
        minRad = newCircle.radius
    }
    for (i in 0 until points.size) {
        for (j in 0 until points.size) {
            for (k in 0 until points.size) {
                if (i != j && k != j && k != i) {
                    newCircle = circleByThreePoints(points[i], points[j], points[k])
                    if (!isAllPointsContains(points, newCircle, listOf(points[i], points[j], points[k]))
                            || newCircle.radius > minRad) continue
                    circle = newCircle
                    minRad = newCircle.radius
                }
            }
        }
    }
    return circle
}

fun isAllPointsContains(points: Array<out Point>, circle: Circle, creators: List<Point>): Boolean {
    for (point in points) {
        if (!circle.contains(point) && point !in creators) return false
    }
    return true
}