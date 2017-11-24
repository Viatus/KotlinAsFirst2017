@file:Suppress("UNUSED_PARAMETER")

package lesson5.task1

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
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    if (parts.size != 3) return ""
    val monthNumbers = mapOf("января" to 1, "февраля" to 2, "марта" to 3, "апреля" to 4, "мая" to 5, "июня" to 6,
            "июля" to 7, "августа" to 8, "сентября" to 9, "октября" to 10, "ноября" to 11, "декабря" to 12)
    try {
        if (parts[0].toInt() !in 1..31 || monthNumbers[parts[1]] == null || parts[2].toInt() < 0) {
            return ""
        }
        return String.format("%02d.%02d.%s", parts[0].toInt(), monthNumbers[parts[1]], parts[2])
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    val months = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября",
            "октября", "ноября", "декабря")
    try {
        if (parts[0].toInt() !in 1..31 || parts[1].toInt() !in 1..12 || parts[2].toInt() < 0 || parts.size != 3) {
            return ""
        }
        return String.format("%d %s %s", parts[0].toInt(), months[parts[1].toInt() - 1], parts[2])
    } catch (e: NumberFormatException) {
        return ""
    } catch (e: IndexOutOfBoundsException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    if (phone.isEmpty()) return ""
    if ("[^0-9()\\-\\s+]".toRegex().find(phone) != null || ("/+".toRegex().find(phone.substring(1))) != null || phone == "+")
        return ""
    return phone.filter { it !in listOf(' ', '-', ')', '(', '\n') }
}

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
fun bestLongJump(jumps: String): Int {
    if (jumps.isEmpty()) {
        return -1
    }
    val parts = jumps.split(" ")
    var maxJump = -1
    for (part in parts) {
        try {
            if (part.toInt() > maxJump) maxJump = part.toInt()
        } catch (e: NumberFormatException) {
            if (part != "-" && part != "%" && part != "")
                return -1
        }
    }
    if (maxJump >= 0) {
        return maxJump
    }
    return -1
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (jumps.isEmpty()) {
        return -1
    }
    val parts = jumps.split(" ")
    var maxJump = -1
    for (i in 0..parts.size - 2) {
        try {
            for (element in parts[i + 1]) {
                if (element == '+' && parts[i].toInt() > maxJump)
                    maxJump = parts[i].toInt()
            }
        } catch (e: NumberFormatException) {
            for (element in parts[i]) {
                if (element != '+' && element != '%' && element != '-')
                    return -1
            }
        }
    }
    if (maxJump >= 0) {
        return maxJump
    }
    return -1
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
    val parts = expression.split(" ")
    var value = 0
    try {
        value = parts[0].toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
    var i = 1
    var expressionSize = expression.length - parts[0].length - 1
    while (expressionSize > 0) {
        try {
            when (parts[i]) {
                "+" -> {
                    value += parts[i + 1].toInt()
                    expressionSize -= parts[i + 1].length + 3
                }
                "-" -> {
                    value -= parts[i + 1].toInt()
                    expressionSize -= parts[i + 1].length + 3
                }
                else -> throw IllegalArgumentException()
            }
            i += 2
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException()
        }
    }
    return value
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
fun firstDuplicateIndex(str: String): Int {
    var index = 0
    val words = str.toLowerCase().split(" ")
    var prevWord = words[0]
    for (i in 1 until words.size) {
        if (words[i] == prevWord)
            return index
        index += words[i - 1].length + 1
        prevWord = words[i]
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    val newDescription = description.filter { it != ';' }
    val parts = newDescription.split(" ")
    var maxPrice = 0.0
    var maxName = ""
    try {
        for (i in 1 until parts.size step 2) {
            if (parts[i].toDouble() >= maxPrice) {
                maxPrice = parts[i].toDouble()
                maxName = parts[i - 1]
            }
        }
    } catch (e: NumberFormatException) {
        return ""
    }
    return maxName
}

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
fun fromRoman(roman: String): Int {
    if (roman.isEmpty()) return -1
    var number = 0
    var romanReplica = roman
    while (romanReplica != "") {
        when (romanReplica[0]) {
            'M' -> {
                number += 1000
                romanReplica = romanReplica.substring(1)
            }
            'D' -> {
                number += 500
                romanReplica = romanReplica.substring(1)
            }
            'C' -> {
                try {
                    if (romanReplica[1] == 'M') {
                        number += 900
                        romanReplica = romanReplica.substring(2)
                    } else {
                        if (romanReplica[1] == 'D') {
                            number += 400
                            romanReplica = romanReplica.substring(2)
                        } else {
                            number += 100
                            romanReplica = romanReplica.substring(1)
                        }
                    }
                } catch (e: StringIndexOutOfBoundsException) {
                    number += 100
                    romanReplica = ""
                }
            }
            'L' -> {
                number += 50
                romanReplica = romanReplica.substring(1)
            }
            'X' -> {
                try {
                    if (romanReplica[1] == 'C') {
                        number += 90
                        romanReplica = romanReplica.substring(2)
                    } else {
                        if (romanReplica[1] == 'L') {
                            number += 40
                            romanReplica = romanReplica.substring(2)
                        } else {
                            number += 10
                            romanReplica = romanReplica.substring(1)
                        }
                    }
                } catch (e: StringIndexOutOfBoundsException) {
                    number += 10
                    romanReplica = ""
                }
            }
            'V' -> {
                number += 5
                romanReplica = romanReplica.substring(1)
            }
            'I' -> {
                try {
                    if (romanReplica[1] == 'X') {
                        number += 9
                        romanReplica = romanReplica.substring(2)
                    } else {
                        if (romanReplica[1] == 'V') {
                            number += 4
                            romanReplica = romanReplica.substring(2)
                        } else {
                            number += 1
                            romanReplica = romanReplica.substring(1)
                        }
                    }
                } catch (e: StringIndexOutOfBoundsException) {
                    number += 1
                    romanReplica = ""
                }
            }
            else -> return -1
        }
    }
    return number
}

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
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val listOfCells = mutableListOf<Int>()
    for (i in 0 until cells) {
        listOfCells.add(0)
    }
    var currentCellIndex = cells / 2
    var commandIndex = 0
    if (commands.isEmpty()) return listOfCells
    for (i in 0 until limit) {
        val command = commands[commandIndex]
        when (command) {
            '+' -> {
                listOfCells[currentCellIndex]++
                commandIndex++
            }
            '-' -> {
                listOfCells[currentCellIndex]--
                commandIndex++
            }
            '>' -> {
                currentCellIndex++
                commandIndex++
                if (currentCellIndex !in 0 until 957) throw IllegalStateException()
            }
            '<' -> {
                currentCellIndex--
                commandIndex++
                if (currentCellIndex !in 0 until 957) throw IllegalStateException()
            }
            '[' -> {
                if (listOfCells[currentCellIndex] == 0) {
                    var count = 0
                    try {
                        for (newCommandIndex in commandIndex + 1..commands.length) {
                            if (commands[newCommandIndex] == '[')
                                count++
                            if (commands[newCommandIndex] == ']' && count == 0) {
                                commandIndex = newCommandIndex + 1
                                break
                            }
                            if (commands[newCommandIndex] == ']')
                                count--
                        }
                    } catch (e: StringIndexOutOfBoundsException) {
                        throw IllegalArgumentException()
                    }

                } else {
                    commandIndex++
                }
            }
            ']' -> {
                if (listOfCells[currentCellIndex] != 0) {
                    var count = 0
                    try {
                        for (newCommandIndex in commandIndex - 1 downTo -1) {
                            if (commands[newCommandIndex] == ']')
                                count++
                            if (commands[newCommandIndex] == '[' && count == 0) {
                                commandIndex = newCommandIndex + 1
                                break
                            }
                            if (commands[newCommandIndex] == '[')
                                count--
                        }
                    } catch (e: StringIndexOutOfBoundsException) {
                        throw IllegalArgumentException()
                    }
                } else {
                    commandIndex++
                }

            }
            ' ' -> commandIndex++
            else -> throw IllegalArgumentException()
        }
        if (commandIndex == commands.length)
            break
    }
    return listOfCells
}
