@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = TODO()

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = TODO()

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun getWordFromHundreds(numVal: Int, numLength: Int): String {
    val digits = mapOf(
        0 to "ноль",
        1 to "один",
        2 to "два",
        3 to "три",
        4 to "четыре",
        5 to "пять",
        6 to "шесть",
        7 to "семь",
        8 to "восемь",
        9 to "девять"
    )
    val decades = mapOf(
        1 to "десять",
        11 to "одиннадцать",
        12 to "двенадцать",
        13 to "тринадцать",
        14 to "четырнадцать",
        15 to "пятнадцать",
        16 to "шестнадцать",
        17 to "семнадцать",
        18 to "восемнадцать",
        19 to "девятнадцать",
        2 to "двадцать",
        3 to "тридцать",
        4 to "сорок",
        5 to "пятьдесят",
        6 to "шестьдесят",
        7 to "семьдесят",
        8 to "восемьдесят",
        9 to "девяносто"
    )
    val hundreds = mapOf(
        1 to "сто",
        2 to "двести",
        3 to "триста",
        4 to "четыреста",
        5 to "пятьсот",
        6 to "шестьсот",
        7 to "семьсот",
        8 to "восемьсот",
        9 to "девятьсот"
    )
    val thousands = mapOf(
        1 to "одна тысяча",
        2 to "две тысячи",
        3 to "три тысячи",
        4 to "четыре тысячи",
        5 to "пять тысяч",
        6 to "шесть тысяч",
        7 to "семь тысяч",
        8 to "восемь тысяч",
        9 to "девять тысяч"
    )

    var resultText = ""
    var numLengthVar = kotlin.math.log10(numVal * 1.0).toInt() + 1
    var currentDigit = kotlin.math.floor(numVal / 10.0.pow(numLengthVar - 1)).toInt()
    var leftovers = (numVal - (currentDigit * 10.0.pow(numLengthVar - 1).toInt()))

    //println("Мы в get_word_from_hundreds")
    //println("Переданное число: $numVal; Переданная длинна числа: $numLength")
    if (numVal == 0) {
        return digits.getValue(numVal)
    }

    // Данное условие нужно для значений < 10, поскольку для них разные слова "тысяча" и "тысячи"
    if (numVal < 10 && numLength > 3) {
        // Присланное число есть в массиве тысяч
        if (thousands.containsKey(numVal)) {
            return thousands.getValue(numVal)
        }
    } else if (numVal < 10) {
        // Присланное число есть в массиве единиц и оно от 1 до 9
        if (digits.containsKey(numVal)) {
            return digits.getValue(numVal)
        }
    }

    //println("currentDigit: $currentDigit; leftovers: $leftovers")
    if (numLengthVar == 3 && hundreds.containsKey(currentDigit) && leftovers > 10 && decades.containsKey(leftovers)) {
        resultText = hundreds.getValue(currentDigit) + " " + decades.getValue(leftovers)
    }
    //println("result text: $resultText")
    // Присланное число есть в массиве десятков и оно от 11 до 19
    if (numLengthVar == 2 && decades.containsKey(numVal)) {
        resultText = when {
            resultText.isEmpty() -> decades.getValue(numVal)
            else -> resultText + " " + decades.getValue(numVal)
        }
    }

    if (resultText.isEmpty()) {
        do {
            if (numLengthVar == 3 && hundreds.containsKey(currentDigit)) {
                resultText = when {
                    resultText.isEmpty() -> hundreds.getValue(currentDigit)
                    else -> resultText + " " + hundreds.getValue(currentDigit)
                }
            } else if (numLengthVar == 2) {
                resultText = when {
                    resultText.isEmpty() -> decades.getValue(currentDigit)
                    else -> resultText + " " + decades.getValue(currentDigit)
                }
            } else if (numLengthVar == 1) {
                if (numLength > 3 && thousands.containsKey(currentDigit)) {
                    resultText = when {
                        resultText.isEmpty() -> thousands.getValue(currentDigit)
                        else -> resultText + " " + thousands.getValue(currentDigit)
                    }
                } else if (digits.containsKey(currentDigit)) {
                    resultText = when {
                        resultText.isEmpty() -> digits.getValue(currentDigit)
                        else -> resultText + " " + digits.getValue(currentDigit)
                    }
                }
            }

            numLengthVar = kotlin.math.log10(leftovers * 1.0).toInt() + 1

            currentDigit = kotlin.math.floor(leftovers / 10.0.pow(numLengthVar - 1)).toInt()
            leftovers = (leftovers - (currentDigit * 10.0.pow(numLengthVar - 1).toInt()))

        } while (currentDigit != 0)
    }

    when {
        numLength > 3 && !resultText.contains(" тысяч") -> resultText += " тысяч"
        else -> resultText
    }
    return resultText
}

fun russian(n: Int): String {
    var result: String = ""
    var thousandsNum = 0
    var hundredsNum = 0

    var numLength = kotlin.math.log10(n * 1.0).toInt() + 1

    //println("Обрабатываемое число: $n")
    //println("Длинна числа: $numLength")

    if (numLength <= 1) {
        return getWordFromHundreds(n, 1)
    }

    if (numLength > 3) {
        thousandsNum = kotlin.math.floor(n / 1000.0).toInt()
        hundredsNum = n % 1000

    } else if (numLength <= 3) {
        hundredsNum = n
    }

    if (thousandsNum > 0) {
        result = getWordFromHundreds(thousandsNum, numLength)
    }
    if (hundredsNum > 0) {
        result = when {
            result.isEmpty() -> getWordFromHundreds(hundredsNum, 3)
            else -> result + " " + getWordFromHundreds(hundredsNum, 3)
        }
    }

    return result
}