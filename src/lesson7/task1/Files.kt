@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.BufferedWriter
import java.io.File
import java.util.Deque
import java.util.LinkedList

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> = TODO()


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */

fun replaceAllCases(
    textLine: String,
    letter1ForSearch: String,
    letter2ForSearch: String,
    letter1ForReplace: String,
    letter2ForReplace: String
): String {
    var resultText = textLine
    resultText = resultText.replace(
        letter1ForSearch.toLowerCase() + letter2ForSearch.toLowerCase(),
        letter1ForReplace.toLowerCase() + letter2ForReplace.toLowerCase(),
        false
    )
    resultText = resultText.replace(
        letter1ForSearch.toUpperCase() + letter2ForSearch.toUpperCase(),
        letter1ForReplace.toUpperCase() + letter2ForReplace.toUpperCase(),
        false
    )
    resultText = resultText.replace(
        letter1ForSearch.toLowerCase() + letter2ForSearch.toUpperCase(),
        letter1ForReplace.toLowerCase() + letter2ForReplace.toUpperCase(),
        false
    )
    resultText = resultText.replace(
        letter1ForSearch.toUpperCase() + letter2ForSearch.toLowerCase(),
        letter1ForReplace.toUpperCase() + letter2ForReplace.toLowerCase(),
        false
    )

    return resultText
}

fun sibilants(inputName: String, outputName: String) {
    val someInputText = File(inputName)
    val someOutputText = File(outputName).bufferedWriter()
    var textLine: String

    for (txt in someInputText.readLines()) {
        textLine = txt
        if (textLine.toUpperCase().contains("ЖЫ")) {
            textLine = replaceAllCases(textLine, "ж", "ы", "ж", "и")
            //someOutputText.write(it.replace("жы", "жи", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ШЫ")) {
            textLine = replaceAllCases(textLine, "ш", "ы", "ш", "и")
            //someOutputText.write(it.replace("шы", "ши", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЩЫ")) {
            textLine = replaceAllCases(textLine, "щ", "ы", "щ", "и")
            //someOutputText.write(it.replace("шы", "ши", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЧЫ")) {
            textLine = replaceAllCases(textLine, "ч", "ы", "ч", "и")
            //someOutputText.write(it.replace("шы", "ши", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЧЯ")) {
            textLine = replaceAllCases(textLine, "ч", "я", "ч", "а")
            //someOutputText.write(it.replace("чя", "ча", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЩЯ")) {
            textLine = replaceAllCases(textLine, "щ", "я", "щ", "а")
            //someOutputText.write(it.replace("щя", "ща", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЖЯ")) {
            textLine = replaceAllCases(textLine, "ж", "я", "ж", "а")
            //someOutputText.write(it.replace("жя", "жа", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ШЯ")) {
            textLine = replaceAllCases(textLine, "ш", "я", "ш", "а")
            //someOutputText.write(it.replace("шя", "ша", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЧЮ")) {
            textLine = replaceAllCases(textLine, "ч", "ю", "ч", "у")
            //someOutputText.write(it.replace("чю", "чу", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЩЮ")) {
            textLine = replaceAllCases(textLine, "щ", "ю", "щ", "у")
            //someOutputText.write(it.replace("щю", "щу", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ШЮ")) {
            textLine = replaceAllCases(textLine, "ш", "ю", "ш", "у")
            //someOutputText.write(it.replace("шю", "шу", true) + newLine)
        }
        if (textLine.toUpperCase().contains("ЖЮ")) {
            textLine = replaceAllCases(textLine, "ж", "ю", "ж", "у")
            //someOutputText.write(it.replace("жю", "жу", true) + newLine)
        }

        someOutputText.write(textLine)
        someOutputText.newLine()

    }

    someOutputText.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    val someInputText = File(inputName)
    val someOutputText = File(outputName).bufferedWriter()
    var listWithoutDoubleLetters = mutableListOf<String>()
    var maxLength = 0
    var firstAdd = true
    var containsLetter = false

    //val maxLength = someInputText.useLines { s -> s.maxOf { it.length } }
    for (txt in someInputText.readLines()) {
        //println("Обрабатываемое слово: $txt")
        for (symbol in txt) {
            //println("Обрабатываемая буква: $symbol")
            containsLetter = txt.toUpperCase().substringAfter(symbol.toUpperCase()).contains(symbol.toUpperCase())
            //println("containsLetter: ${txt.toUpperCase().substringAfter(symbol.toUpperCase())}")
            if (containsLetter == true) {
                break
            }
        }
        //println("Обнаружен текст: $containsLetter; firstAdd: $firstAdd")
        if (!containsLetter) {
            listWithoutDoubleLetters.add(txt)
        }
    }

    for (word in listWithoutDoubleLetters) {
        if (maxLength < word.length) {
            maxLength = word.length
        }
    }

    for (txt in listWithoutDoubleLetters) {
        if (txt.length >= maxLength) {
            when {
                firstAdd -> someOutputText.write(txt)
                else -> someOutputText.write(", $txt")
            }
            firstAdd = false
        }
    }
    someOutputText.close()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val someTextFile = File(inputName)
    val someTextOutput = File(outputName).bufferedWriter()

    var resultWord = ""
    val indent = "         "
    var textFilled = false
    val newLine = Regex("\n").toString()
    var stackOfWordsAndTags: Deque<String> = LinkedList();

    stackOfWordsAndTags.addFirst("</html>")
    stackOfWordsAndTags.addFirst(newLine)
    stackOfWordsAndTags.addFirst("    </body>")
    stackOfWordsAndTags.addFirst(newLine)

    for (i in someTextFile.readLines().reversed()) {

        if ((i.isNotEmpty() && !textFilled) || someTextFile.readLines().reversed().first() == i) {
            stackOfWordsAndTags.addFirst("        </p>")
            stackOfWordsAndTags.addFirst(newLine)
            //println("</p>WordsAndTags: ${stackOfWordsAndTags.first()} emptyLine: $textFilled; i: $i")
        }
        if (i.isNotEmpty()) {
            textFilled = true
            for (word in i.split(" ").reversed()) {
                resultWord = when {
                    i.isNotEmpty() && i.split(" ").reversed().first() != word && i.split(" ").reversed()
                        .last() != word -> "$word "
                    i.split(" ").reversed().last() == word -> "${indent + word} "
                    else -> word
                }
                //Здесь должен быть блок для замены символов, которые не обрамляют текст на закрывающие теги

                //Ищем открывающий тег, у которого может быть закрывающий тег далее в другом слове
                if (Regex("\\*\\*.*(\\w|[А-Яа-я])+").containsMatchIn(resultWord)) {
                    resultWord = Regex("\\*\\*").replaceFirst(resultWord, "<b>")
                }

                if (Regex("(\\w|[А-Яа-я])+.*\\*\\*").containsMatchIn(resultWord)) {
                    resultWord = Regex("\\*\\*").replaceFirst(resultWord, "</b>")
                }

                if (Regex("\\*.*(\\w|[А-Яа-я])+").containsMatchIn(resultWord)) {
                    resultWord = Regex("\\*").replaceFirst(resultWord, "<i>")
                }
                if (Regex("(\\w|[А-Яа-я])+.*\\*").containsMatchIn(resultWord)) {
                    resultWord = Regex("\\*").replaceFirst(resultWord, "</i>")
                }

                if (Regex("~~.*(\\w|[А-Яа-я])+").containsMatchIn(resultWord)) {
                    resultWord = Regex("~~").replaceFirst(resultWord, "<s>")
                }
                if (Regex("(\\w|[А-Яа-я])+.*~~").containsMatchIn(resultWord)) {
                    resultWord = Regex("~~").replaceFirst(resultWord, "</s>")
                }

                stackOfWordsAndTags.addFirst(resultWord)
            }
            stackOfWordsAndTags.addFirst(newLine)
        }

        if ((someTextFile.readLines().reversed().last() == i
                    || someTextFile.readLines().reversed().first() == i) || (i.isEmpty() && textFilled)
        ) {
            //println("<p>WordsAndTags: ${stackOfWordsAndTags.first()} emptyLine: $textFilled; i: $i; stackOfWordsAndTags[0]: ${stackOfWordsAndTags[0]}; stackOfWordsAndTags[1]: ${stackOfWordsAndTags[1]};  ")
            stackOfWordsAndTags.addFirst("        <p>")
            stackOfWordsAndTags.addFirst(newLine)
            textFilled = false
        }

    }
    if (stackOfWordsAndTags.size > 4) {
        stackOfWordsAndTags.addFirst("    <body>")
        stackOfWordsAndTags.addFirst(newLine)
        stackOfWordsAndTags.addFirst("<html>")
    }

    for (text in stackOfWordsAndTags) {
        someTextOutput.write(text)
    }

    someTextOutput.close()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>
Или колбаса
</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>
Фрукты
<ol>
<li>Бананы</li>
<li>
Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */

fun addClosingToLine(
    closingStackData: Deque<Pair<Int, String>>,
    line: Pair<Int, Pair<String, String>>
): Deque<Pair<Int, String>> {
    if (closingStackData.isEmpty() || !closingStackData.contains(
            Pair(line.first, line.second.first)
        )
    ) {
        closingStackData.addFirst(Pair(line.first, line.second.first))
    }
    return closingStackData
}

fun tryToCloseList(
    textData: BufferedWriter,
    closingStackData: Deque<Pair<Int, String>>,
    textInStackData: Deque<Pair<Int, Pair<String, String>>>
): Deque<Pair<Int, String>> {
    var closingBuffer: Pair<Int, String>

    while (closingStackData.first().first >= textInStackData.first().first) {
        closingBuffer = closingStackData.peekFirst()
        //println("closingStackТекущий закрывающий тег на уровне: ${closingBuffer.first}; С типом: ${closingBuffer.second} - Следующий тег на уровне: ${textInStackData.first().first}; с типом: ${textInStackData.first().second.first}")
        if (closingBuffer.first == textInStackData.first().first && closingBuffer.second == textInStackData.first().second.first) {
            textData.write("</li>")
            break
        } else {
            textData.write("</li>")
            when (closingBuffer.second) {
                "." -> textData.write("</ol>")
                "*" -> textData.write("</ul>")
            }
            closingStackData.removeFirst()
        }
        when {
            closingStackData.isEmpty() -> break
        }
    }

    return closingStackData
}

fun markdownToHtmlLists(inputName: String, outputName: String) {
    val someTextFile = File(inputName)
    val someTextOutput = File(outputName).bufferedWriter()

    var currentLevel: Int

    // Стек значений - Уровень, Тип списка, Текст
    val levelsAndTypes: Deque<Pair<Int, Pair<String, String>>> = LinkedList()
    var bufferLine: Pair<Int, Pair<String, String>>
    // Стек закрывающих тегов
    var closingStack: Deque<Pair<Int, String>> = LinkedList() // Закрывающий стек - Уровень, Тип списка
    var closingBuffer: Pair<Int, String>

    var text = ""
    val numList = Regex("\\d*\\.\\s")
    val starList = Regex("\\*\\s")

    // Составление карты уровней и типов тегов (Есть ощущение, что можно и без карты обойтись)
    someTextFile.readLines().reversed().forEach { s ->
        if (numList.containsMatchIn(s)) {
            text = s.substringAfter(".").replaceFirst(" ", "")
            currentLevel = (s.substringBefore(".").count { it == ' ' } / 4)

            levelsAndTypes.addFirst(Pair(currentLevel, Pair(".", text)))
        } else if (starList.containsMatchIn(s)) {
            text = s.substringAfter("*").replaceFirst(" ", "")
            currentLevel = (s.substringBefore("*").count { it == ' ' } / 4)

            levelsAndTypes.addFirst(Pair(currentLevel, Pair("*", text)))
        }
    }

    if (levelsAndTypes.isNotEmpty()) {
        someTextOutput.write("<html>")
        someTextOutput.write("<body>")
    } else {
        someTextOutput.write("</html>")
        someTextOutput.write("</body>")
        return
    }

    levelsAndTypes.indices.forEach {
        bufferLine = levelsAndTypes.poll() // Текущая запись

        if (levelsAndTypes.isEmpty()) {
            someTextOutput.write("<li>" + bufferLine.second.second)

            while (closingStack.isNotEmpty()) {
                closingBuffer = closingStack.pollFirst()
                someTextOutput.write("</li>")
                when (closingBuffer.second) {
                    "." -> someTextOutput.write("</ol>")
                    "*" -> someTextOutput.write("</ul>")
                }

            }
        } else {
            //println("1Обработка элемента: ${bufferLine.second.second}; Количество записей в стеке закрытия: ${closingStack.size}; Содержание текущей пары: ${closingStack.contains(Pair(bufferLine.first, bufferLine.second.first))}")
            if (!closingStack.contains(Pair(bufferLine.first, bufferLine.second.first))) {
                when (bufferLine.second.first) {
                    "." -> someTextOutput.write("<ol>")
                    "*" -> someTextOutput.write("<ul>")
                }
            }

            if (bufferLine.first == levelsAndTypes.first().first && bufferLine.second.first == levelsAndTypes.first().second.first) { // На следующем элементе уровень и тип не изменятся - список не вложенный


                someTextOutput.write("<li>" + bufferLine.second.second + "</li>")
                closingStack = addClosingToLine(closingStack, bufferLine)

            } else if (bufferLine.first == levelsAndTypes.first().first && bufferLine.second.first != levelsAndTypes.first().second.first) { // На следующем элементе изменится только тип - список не вложенный
                someTextOutput.write("<li>" + bufferLine.second.second)
                closingStack = addClosingToLine(closingStack, bufferLine)
                //println("1Выполнить закрытие для элемента: ${bufferLine.second.second} Уровень первой записи в закрытии: ${closingStack.first().first} - Уровень следующей записи из текста: ${levelsAndTypes.first().first}")
                closingStack = tryToCloseList(someTextOutput, closingStack, levelsAndTypes)

            } else { // Работа с вложенными списками
                if (bufferLine.first < levelsAndTypes.first().first) { // Следующий элемент - это элемент вложенного списка

                    someTextOutput.write("<li>")
                    someTextOutput.write(bufferLine.second.second)

                    closingStack = addClosingToLine(closingStack, bufferLine)
                } else if (bufferLine.first > levelsAndTypes.first().first) { //Следующий элемент - это элемент на уровне выше
                    closingStack = addClosingToLine(closingStack, bufferLine)

                    someTextOutput.write("<li>" + bufferLine.second.second)

                    //println("2Выполнить закрытие для элемента: ${bufferLine.second.second} Уровень первой записи в закрытии: ${closingStack.first().first} - Уровень следующей записи из текста: ${levelsAndTypes.first().first}")
                    closingStack = tryToCloseList(someTextOutput, closingStack, levelsAndTypes)

                }

            }
        }
    }

    someTextOutput.write("</body>")
    someTextOutput.write("</html>")

    someTextOutput.close()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

