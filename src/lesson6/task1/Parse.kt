@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите дату в формате ДД МЕСЯЦ ГГГГ")
    val line = "0 - 1"//readLine()
    /*if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }*/

    println("Result: ${plusMinus(line ?: "")}")
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    var day: Int = 0
    var month: String = ""
    var monthMaxDay = 0
    var year: Int = 0
    val pattern = """\d{1,2}\s[А-Яа-я]+\s\d{4}""".toRegex()

    val monthVal = mapOf<String, Pair<String, Int>>(
        "января" to Pair("01", 31),
        "февраля" to Pair("02", 28),
        "марта" to Pair("03", 31),
        "апреля" to Pair("04", 30),
        "мая" to Pair("05", 31),
        "июня" to Pair("06", 30),
        "июля" to Pair("07", 31),
        "августа" to Pair("08", 31),
        "сентября" to Pair("09", 30),
        "октября" to Pair("10", 31),
        "ноября" to Pair("11", 30),
        "декабря" to Pair("12", 31)
    )

    if (pattern.matches(str)) {
        for (wordNum in str.split(" ").indices) {

            when (wordNum) {
                0 -> day = str.split(" ")[wordNum].toInt()
                1 -> {
                    month = (monthVal[str.split(" ")[wordNum]]?.first ?: "")
                    monthMaxDay = (monthVal[str.split(" ")[wordNum]]?.second ?: 0)
                }
                2 -> year = str.split(" ")[wordNum].toInt()
            }
            //println("Day $day; month $month; year $year; str: ${str.split(" ")[wordNum]}")
            if (day > 31 || day < 1 || (wordNum == 1 && month == "")) {
                return ""
            }
            // Високосный год
            if ((year % 4 == 0 && month == "02" && day > monthMaxDay + 1)) {
                return ""
            } else if (year % 4 != 0 && day > monthMaxDay) {
                return ""
            }
        }
    }
    if (day == 0 || month == "" || year == 0) {
        return ""
    }
    return String.format("%02d.%s.%4d", day, month, year)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val monthVal = mapOf<String, Pair<String, Int>>(
        "01" to Pair("января", 31),
        "02" to Pair("февраля", 28),
        "03" to Pair("марта", 31),
        "04" to Pair("апреля", 30),
        "05" to Pair("мая", 31),
        "06" to Pair("июня", 30),
        "07" to Pair("июля", 31),
        "08" to Pair("августа", 31),
        "09" to Pair("сентября", 30),
        "10" to Pair("октября", 31),
        "11" to Pair("ноября", 30),
        "12" to Pair("Декабря", 31)
    )

    var day: Int = 0
    var month: String = ""
    var monthMaxDay = 0
    var year: Int = 0
    var buf: String = ""
    val pattern = """\d{1,2}.\d{1,2}.\d{4}""".toRegex()

    if (pattern.matches(digital)) {
        for (wordNum in digital.split(".").indices) {
            buf = digital.split(".")[wordNum]
            when (wordNum) {
                0 -> day = buf.toInt()
                1 -> {
                    month = (monthVal[digital.split(".")[wordNum]]?.first ?: "")
                    monthMaxDay = (monthVal[digital.split(".")[wordNum]]?.second ?: 0)
                }
                2 -> year = buf.toInt()
            }
            println("Day $day; month $month; year $year; str: ${digital.split(".")[wordNum]}")
            if (day > 31 || day < 1 || (wordNum == 1 && month == "")) {
                return ""
            }
            // Високосный год
            if ((year % 4 == 0 && month == "02" && day > monthMaxDay + 1)) {
                return ""
            } else if (year % 4 != 0 && day > monthMaxDay) {
                return ""
            }
        }
    }

    if (day == 0 || month == "" || year == 0) {
        return ""
    }
    return String.format("%d %s %4d", day, month, year)
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String = TODO()

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = TODO()

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var result = -1
    var attemptHigh = 0
    var attemptType = ""
    var typeAttemptPattern = Regex("""[\\+,-/,%]""")
    var typeExceptionsPattern = Regex("""[^\\+,-/,%]|(\d+[^\s])""")
    //var pattern = """^(\d+\s[+,-/,%])(\s\d+\s[+,-/,%])+$""".toRegex()

    //println("Match: ${pattern.matchEntire(jumps)}; pattern value: ${jumps.split(pattern)}")

    for (jump in jumps.split(" ")) {

        if (jump.toIntOrNull() != null && jump.toInt() > result) {
            attemptHigh = jump.toInt()
        } else if (jump.toIntOrNull() == null && typeAttemptPattern.matches(jump)) {
            attemptType = jump
        } else if (typeExceptionsPattern.containsMatchIn(jump)) {
            result = -1
            break
        }

        if (attemptHigh > result && attemptType == "+") {
            result = attemptHigh
            attemptHigh = 0
            attemptType = ""
        } else if (attemptHigh != 0 && attemptType != "") {
            attemptHigh = 0
            attemptType = ""
        }
    }

    return result
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    var result = 0
    var numValue = 0
    var typeAction = ""
    var truePattern = Regex("""(\d+)|((\d+\s[+,-]\s\d+)*((\s[+,-]\s\d+))*)""")
    var exceptionPattern = Regex("""(\d+[^\s])|([+,-][^\s])|(\d+\s\d)|([+,-]\s[+,-])""")

    if (truePattern.matchEntire(expression) == null) {
        throw IllegalArgumentException("Wrong row")
    }
    for (value in expression.split(" ")) {
        if (value.toIntOrNull() != null) {
            numValue = value.toInt()
        } else {
            typeAction = value
        }
        println("numValue: $numValue typeAction: $typeAction")
        if (result == 0 && numValue > 0 && typeAction == "") {
            result = numValue
            numValue = 0
        } else if (numValue != 0 && typeAction != "") {
            println("result Before Action: $result")
            when (typeAction) {
                "+" -> result += numValue
                "-" -> result -= numValue
            }
            println("result After Action: $result")
            numValue = 0
            typeAction = ""
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int = TODO()

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
