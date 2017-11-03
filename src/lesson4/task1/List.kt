@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant

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
                val root = Math.sqrt(y)
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
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
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
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (element in v) {
        sum += element * element
    }
    return Math.sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) {
        return 0.0
    }
    return list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) {
        return list
    }
    val average = list.sum() / list.size
    for (i in 0 until list.size) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    if (a.isEmpty() || b.isEmpty()) {
        return 0.0
    }
    var c = 0.0
    for (i in 0 until a.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) {
        return 0.0
    }
    var sum = p.first()
    for (i in 1 until p.size) {
        sum += p[i] * Math.pow(x, i.toDouble())
    }
    return sum
}

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
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    for (i in list.size - 1 downTo 1) {
        for (j in i - 1 downTo 0) {
            list[i] += list[j]
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val digits = mutableListOf<Int>()
    var nReplica = n
    var factor = 2
    while (nReplica != 1) {
        if (nReplica % factor == 0) {
            digits.add(factor)
            nReplica /= factor
        } else {
            factor++
        }
    }
    return digits
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n == 0) {
        return listOf(0)
    }
    val list = mutableListOf<Int>()
    var nReplica = n
    while (nReplica > 0) {
        list.add(nReplica % base)
        nReplica /= base
    }
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun numberToString(n: Int): String {
    if (n in 0 until 10) {
        return n.toString()
    }
    return ('a' + n - 10).toString()
}

fun convertToString(n: Int, base: Int): String {
    if (n == 0) {
        return "0"
    }
    val listOfDigits = convert(n, base)
    val number = StringBuilder()
    for (elements in listOfDigits) {
        number.append(numberToString(elements))
    }
    return number.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0
    for (i in 0 until digits.size) {
        number += digits[i] * Math.pow(base.toDouble(), (digits.size - i - 1).toDouble()).toInt()
    }
    return number
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var basePow = 1
    var number = 0
    for (i in str.length - 1 downTo 0) {
        when {
            str[i] in '0'..'9' -> number += (str[i] - '0') * basePow
            str[i] in 'a'..'z' -> number += (str[i] - 'a' + 10) * basePow
        }
        basePow *= base
    }
    return number
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun romanPart(number: Int, unit: String, five: String, decade: String): String {
    val numberUnit = number % 10
    return when (numberUnit) {
        1 -> unit
        2 -> unit + unit
        3 -> unit + unit + unit
        4 -> five + unit
        6 -> unit + five
        7 -> unit + unit + five
        8 -> unit + unit + unit + five
        5 -> five
        9 -> decade + unit
        else -> ""
    }
}

fun roman(n: Int): String {
    val rom = StringBuilder()
    val alphabet = listOf("I", "V", "X", "L", "C", "D", "M")
    var numberHundred = n % 1000
    val numberThousand = n / 1000
    var unit = 0
    var five = 1
    var decade = 2
    while (numberHundred > 0) {
        rom.append(romanPart(numberHundred, alphabet[unit], alphabet[five], alphabet[decade]))
        numberHundred /= 10
        unit += 2
        five += 2
        decade += 2
    }
    for (i in 1..numberThousand) {
        rom.append("M")
    }
    return rom.reversed().toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun teenNumberName(number: Int): String {
    return when (number) {
        in 4..9 -> digitName(number).substring(0, digitName(number).length - 1) + "надцать "
        1, 3 -> digitName(number) + "надцать "
        2 -> "двенадцать "
        else -> ""
    }
}

fun digitName(n: Int): String =
        listOf("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")[n]


fun hundredName(number: Int, isThousand: Boolean): String {
    val rusHundred = StringBuilder()
    val numberHundred = number / 100
    rusHundred.append(when (numberHundred) {
        in 5..9 -> digitName(numberHundred) + "сот "
        in 3..4 -> digitName(numberHundred) + "ста "
        1 -> "сто "
        2 -> "двести "
        else -> ""
    })
    val numberDecade = number / 10 % 10
    val numberRank = number % 10
    if (numberDecade != 1 || numberRank == 0) {
        rusHundred.append(when (numberDecade) {
            in 5..8 ->digitName(numberDecade) + "десят "
            in 2..3 -> digitName(numberDecade) + "дцать "
            9 -> "девяносто "
            4 -> "сорок "
            1 -> "десять "
            else -> ""
        })
    } else {
        rusHundred.append(teenNumberName(numberRank))
        return rusHundred.toString()
    }
    if (!isThousand) {
        if (numberRank != 0) {
            rusHundred.append(digitName(numberRank))
        }
    } else {
        rusHundred.append(when (numberRank) {
            in 3..9 -> digitName(numberRank) + " "
            2 -> "две "
            1 -> "одна "
            else -> ""
        })
    }
    return rusHundred.toString()
}

fun russian(n: Int): String {
    val rus = StringBuilder()
    var number = n
    if (number > 999) {
        val numberThousand = number / 1000
        rus.append(hundredName(numberThousand, true))
        val rank = numberThousand % 10
        if (numberThousand / 10 % 10 != 1 || numberThousand % 10 == 0)
            rus.append(when (rank) {
                0, in 5..9 -> "тысяч "
                in 2..4 -> "тысячи "
                else -> "тысяча "
            }) else {
            rus.append("тысяч ")
        }
        number %= 1000
    }
    if (number != 0) {
        rus.append(hundredName(number, false))
    }
    return rus.toString().trimEnd()
}