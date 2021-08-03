@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import java.lang.Math.abs

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    var positionNumber: Int
    var rightHalf = 0
    var leftHalf = 0
    var initialNumber = number
    val numLength = number.toString().length
    val halfLength = numLength / 2

    return if (numLength % 2 == 0) {
        while (initialNumber != 0) {
            positionNumber = initialNumber % 10

            if (halfLength < initialNumber.toString().length) {
                rightHalf += positionNumber
            } else leftHalf += positionNumber
            initialNumber = (initialNumber - positionNumber) / 10

        }
        rightHalf - leftHalf == 0
    } else false
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    var deltaQueenX = abs(x1 - x2) //Дельта расстояния по Х
    var deltaQueenY = abs(y1 - y2) //Дельта расстояния по Y

    return when {
        (x1 == x2 || y1 == y2) || (deltaQueenX == deltaQueenY) -> true
        //(threatPosition1y == kingY || threatPosition2y == kingY) && (kingX != rookX && kingY != rookY) -> 2
        //(threatPosition1y == kingY || threatPosition2y == kingY) && (kingX == rookX || kingY == rookY) -> 3
        else -> false
    }
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    return when {
        month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 -> 31
        month == 2 && ((year % 100 == 0 && year % 400 != 0) || year % 4 != 0) -> 28
        month == 2 && year % 400 != 0 && year % 4 == 0 -> 29
        month == 2 && year % 400 == 0 -> 29
        month == 4 || month == 6 || month == 9 || month == 11 -> 30
        else -> 0
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    println("x1: $x1; y1: $y1; r1: $r1; ---- x2: $x2; y2: $y2; r2: $r2")
    return if ((sqr(x1) - 2 * x1 * x2 + sqr(x2)) + (sqr(y1) - 2 * y1 * y2 + sqr(y2)) <= (sqr(r1) - 2 * r1 * r2 + sqr(r2))){
        when {
            r2 - r1 < 0 -> false
            else -> true
        }
    } else false
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    println("a: $a; b: $b; c: $c; r: $r; s: $s")
    return when {
        a <= r && (b <= s || c <= s) -> true
        a <= s && (b <= r || c <= r) -> true
        b <= r && (a <= s || c <= s) -> true
        b <= s && (a <= r || c <= r) -> true
        c <= r && (a <= s || b <= s) -> true
        c <= s && (a <= r || b <= r) -> true
        else -> return false
    }
}
