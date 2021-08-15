@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.abs
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var digitsCount = 0
    var num = n
    while (n != 0) {
        num = (num - (num % 10)) / 10
        digitsCount += 1
    }
    return digitsCount
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var iterator = 1
    var fibNumber = 0
    var prevNumber = 1

    while (iterator <= n) {
        prevNumber = abs(fibNumber - prevNumber)
        fibNumber += prevNumber
        //println("iterator: $iterator; prevNumber: $prevNumber; fibNumber: $fibNumber")
        iterator += 1
    }
    return fibNumber
}

/**
 * Функция по рассчёту НОД(Наибольший общий делитель), чтобы с её помощью рассчитать НОК
 * Она не заложена в задачах. На неё написан автотест в MyTests
 */
fun gcd(x: Int, y: Int): Int {
    /** Алгоритм рассчёта по остатку*/
    var resultVal = kotlin.math.abs(x)
    var secondVal = kotlin.math.abs(y)
    /*var buf: Int

    while (secondVal != 0) {
        buf = secondVal
        println("buf: $buf; secondVal: $secondVal; resultVal: $resultVal")
        secondVal = resultVal % secondVal
        resultVal = buf
    }
     */

    /** Алгоритм Евклида */
    if (resultVal == 0 && secondVal != 0) {
        return secondVal
    } else if (secondVal == 0 && resultVal != 0) {
        return resultVal
    }
    if (secondVal == 0 && resultVal == 0) {
        throw java.lang.ArithmeticException("Divided by zero")
    }

    while (resultVal != secondVal) {
        //println("secondVal: $secondVal; resultVal: $resultVal")
        if (resultVal > secondVal) {
            resultVal -= secondVal
        } else if (resultVal < secondVal) {
            secondVal -= resultVal
        } else return resultVal
    }
    return resultVal
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = (m * n) / gcd(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int = TODO()

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = TODO()

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var commonDivider = gcd(m, n)
    return commonDivider == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = TODO()

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var resultVal = x
    var iterator = 0
    while (resultVal != 1) {
        if (resultVal % 2 == 0) {
            resultVal /= 2
        } else {
            resultVal = 3 * resultVal + 1
        }
        iterator += 1
    }
    return iterator
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var seriesMember = x
    var result = 0.0
    var n = 1
    while (abs(seriesMember) > eps) {
        n += 1
        result += seriesMember
        seriesMember *= -x * x / ((2.0 * n - 1.0) * (2.0 * n - 2.0))
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var revNum = 0
    var num = n
    while (num != 0) {
        revNum = (revNum * 10) + (num % 10)
        num = (num - (num % 10)) / 10
    }
    return revNum
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var palNum = 0
    var num = n
    while (num != 0) {
        palNum = (palNum * 10) + (num % 10)
        num = (num - (num % 10)) / 10
    }
    return palNum == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var resultVal = n
    var currentVal = 0
    var previousVal = 0
    while (resultVal != 0) {
        previousVal = currentVal
        currentVal = resultVal % 10
        resultVal = (resultVal - (resultVal % 10)) / 10
        if ((previousVal != 0 && currentVal != 0) && previousVal != currentVal) {
            return true
        }
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 0
    var valLength = 0
    var resultVal = 0
    while (valLength < n){
        i += 1
        resultVal = sqr(i)
        valLength += (Math.log10(resultVal.toDouble()) + 1).toInt()

        // Уменьшим число до нужной позиции n
        if (valLength > n) {
            while (valLength > n) {
                resultVal = (resultVal - (resultVal % 10)) / 10
                valLength -= 1
            }
        }

        //Так как после уменьшения число может иметь более одной цифры, то возьмём последнюю через остаток от деления
        resultVal %= 10
    }

    return resultVal
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var i = 0
    var valLength = 0
    var resultVal = 0
    while (valLength < n){
        i += 1
        resultVal = fib(i)
        valLength += (Math.log10(resultVal.toDouble()) + 1).toInt()

        // Уменьшим число до нужной позиции n
        if (valLength > n) {
            while (valLength > n) {
                resultVal = (resultVal - (resultVal % 10)) / 10
                valLength -= 1
            }
        }

        //Так как после уменьшения число может иметь более одной цифры, то возьмём последнюю через остаток от деления
        resultVal %= 10
    }

    return resultVal
}
