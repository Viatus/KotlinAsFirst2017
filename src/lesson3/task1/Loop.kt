@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

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
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
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
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = Math.abs(n)
    if (n == 0) return 1
    while (number > 0) {
        count++
        number /= 10
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 1
    var fib3 = 0
    if (n == 1 || n == 2) {
        return 1
    }
    for (i in 3..n) {
        fib3 = fib1 + fib2
        fib1 = fib2
        fib2 = fib3
    }
    return fib3
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */

fun gcd(a: Int, b: Int): Int {
    if (b == 0) {
        return a
    }
    return gcd(b, a % b)
}

fun lcm(m: Int, n: Int): Int = n * m / gcd(n, m)


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2 until n) {
        if (n % i == 0)
            return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n - 1 downTo 2) {
        if (n % i == 0) {
            return i
        }
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = Math.min(m, n)
    for (i in 2..min) {
        if ((m % i == 0) && (n % i == 0))
            return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var number = Math.round(Math.sqrt(m.toDouble()))
    while (number * number <= n) {
        if (number * number >= m) {
            return true
        }
        number++
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sc(a: Double, n: Int): Double {
    var b = 1.0
    for (i in 1..n)
        b *= a
    return b
}

fun sin(x: Double, eps: Double): Double {
    var result: Double = 0.0
    var k: Int = 1
    var factorial = 1.0
    val xn = x % (2 * Math.PI)
    var xx = xn
    var exprPart: Double = xn
    while (Math.abs(exprPart) >= eps) {
        if ((k - 1) / 2 % 2 == 0)
            result += exprPart
        else
            result -= exprPart
        k += 2
        factorial *= (k - 1) * k
        xx *= xn * xn
        exprPart = xx / factorial
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var exprPart: Double = 1.0
    var result: Double = 0.0
    var k: Int = 0
    var factorial = 1.0
    val xn = x % (2 * Math.PI)
    var xx = 1.0
    while (Math.abs(exprPart) >= eps) {
        if (k / 2 % 2 == 0)
            result += exprPart
        else
            result -= exprPart
        k += 2
        factorial *= (k - 1) * k
        xx *= xn * xn
        exprPart = xx / factorial
    }
    return result
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun scint(a: Int, n: Int): Int {
    var b = 1
    for (i in 1..n)
        b *= a
    return b
}

fun revert(n: Int): Int {
    var number: Int = n
    val amountOfDigits = digitNumber(number)
    var newNumber = 0
    var tenPow = scint(10, amountOfDigits - 1)
    for (i in 1..amountOfDigits) {
        val digit = number % 10
        number /= 10
        newNumber += digit * tenPow
        tenPow /= 10
    }
    return newNumber
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var number: Int = n
    val amountOfDigits = digitNumber(number)
    var tenPow = scint(10, amountOfDigits - 1)
    while (number >= 10) {
        if (number / tenPow != number % 10) {
            return false
        }
        number %= tenPow
        number /= 10
        tenPow /= 100
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val digit = n % 10
    var number = n / 10
    while (number > 0) {
        if (digit != number % 10) {
            return true
        }
        number /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */

fun squareSequenceDigit(n: Int): Int {
    var flag: Boolean = true
    var i = 1
    var digit = 0
    var digitCounter = 0
    while (flag) {
        val number = i * i
        val amountOfDigits = digitNumber(number)
        if (digitCounter + amountOfDigits >= n) {
            digit = number / scint(10, amountOfDigits - n + digitCounter) % 10
            flag = false
        } else {
            digitCounter += amountOfDigits
        }
        i++
    }
    return digit
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var flag: Boolean = true
    var i = 1
    var digit = 0
    var digitCounter = 0
    while (flag) {
        val number = fib(i)
        val amountOfDigits = digitNumber(number)
        if (digitCounter + amountOfDigits >= n) {
            digit = number / scint(10, amountOfDigits - n + digitCounter) % 10
            flag = false
        } else {
            digitCounter += amountOfDigits
        }
        i++
    }
    return digit
}

