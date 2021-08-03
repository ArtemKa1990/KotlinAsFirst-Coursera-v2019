@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import java.lang.Math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val lastNumInDecades = age % 10
    val lastNumInHundreds = age % 100

    return if (age < 100) {
        if (age in 5..20 || (age > 20 && (lastNumInDecades in 5..9 || lastNumInDecades == 0))) {
            "$age лет"
        } else if (lastNumInDecades == 1) {
            "$age год"
        } else "$age года"
    } else
        if (lastNumInHundreds in 5..20 || (lastNumInHundreds > 20 && (lastNumInDecades in 5..9 || lastNumInDecades == 0)) || lastNumInHundreds == 0) { //if (age > 20) {
            "$age лет"
        } else if (lastNumInDecades == 1) {
            "$age год"
        } else "$age года"
    //} else "$age года"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val halfWay = ((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2
    var leftDistance = 0.0
    var resultTime = 0.0

    leftDistance += halfWay - (t1 * v1)
    println("HalfWay: $halfWay; leftDistance: $leftDistance")
    when {
        leftDistance > 0.0 -> {
            resultTime += t1
        }
        leftDistance == 0.0 -> {
            return t1
        }
        else -> return resultTime + (((t1 * v1) + leftDistance) / v1)
    }
    println("resultTime1: $resultTime")
    leftDistance -= (t2 * v2)
    when {
        leftDistance >= 0.0 -> {
            resultTime += t2
        }
        else -> return resultTime + (((t2 * v2) + leftDistance) / v2)
    }
    println("resultTime2: $resultTime")
    leftDistance -= (t3 * v3)
    when {
        leftDistance >= 0.0 -> {
            resultTime += t3
        }
        else -> return resultTime + (((t3 * v3) + leftDistance) / v3)
    }
    println("resultTime3: $resultTime")
    return resultTime
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return when {
        (kingX == rookX1 || kingY == rookY1) && (kingX != rookX2 && kingY != rookY2) -> 1
        (kingX == rookX2 || kingY == rookY2) && (kingX != rookX1 && kingY != rookY1) -> 2
        (kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2) -> 3
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val deltaKingBishopX = abs(kingX - bishopX)
    val deltaKingBishopY = abs(kingY - bishopY)

    return when {
        (kingX == rookX || kingY == rookY) && (deltaKingBishopX != deltaKingBishopY) -> 1
        (deltaKingBishopX == deltaKingBishopY) && (kingX != rookX && kingY != rookY) -> 2
        (deltaKingBishopX == deltaKingBishopY) && (kingX == rookX || kingY == rookY) -> 3
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    return if (a >= b && a >= c && a < b + c) {
        when {
            sqr(a) == sqr(c) + sqr(b) -> 1
            sqr(a) < sqr(c) + sqr(b) -> 0
            sqr(a) > sqr(c) + sqr(b) -> 2
            else -> -1
        }
    } else if (b >= a && b >= c && b < a + c) {
        when {
            sqr(b) == sqr(a) + sqr(c) -> 1
            sqr(b) < sqr(a) + sqr(c) -> 0
            sqr(b) > sqr(a) + sqr(c) -> 2
            else -> -1
        }
    } else if (c >= a && c >= b && c < a + b) {
        when {
            sqr(c) == sqr(b) + sqr(a) -> 1
            sqr(c) < sqr(b) + sqr(a) -> 0
            sqr(c) > sqr(b) + sqr(a) -> 2
            else -> -1
        }
    }
else -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (b < a || c > d) {
        return -1
    }
    return when {
        a in c..d && b in c..d -> b - a // AB входит в CD
        a in c..d && b !in c..d -> d - a // A входит в CD, B выходит за пределы
        a !in c..d && b in c..d -> b - c // A не входит в CD, только B входит в CD
        c in a..b && d in a..b -> d - c // CD входит в AB
        else -> -1
    }
}
