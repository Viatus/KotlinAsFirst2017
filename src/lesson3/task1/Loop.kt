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
    var number = n
    if (n < 0) number *= -1
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
    if (n < 1) return 0
    return if (n == 1) 1 else fib(n - 1) + fib(n - 2)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var max: Int
    if (m > n)
        max = m
    else {
        max = m
    }
    for (i in max..m * n) {
        if ((i % m == 0) && (i % n == 0))
            return i
    }
    return -1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n - 1) {
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
    var max: Int = 1
    for (i in 1..n - 1) {
        if (n % i == 0)
            max = i
    }
    return max
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var b: Boolean = true
    var min: Int
    if (m > n)
        min = n
    else
        min = m
    for (i in 2..min) {
        if ((m % i == 0) && (n % i == 0))
            b = false
    }
    return b
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n) {
        if (Math.sqrt(i.toDouble()).toInt().toDouble() == Math.sqrt(i.toDouble())) {
            return true
        }
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
    var exprPart: Double = eps
    var result: Double = 0.0
    var k: Int = 1
    var xx: Double
    var factorial: Double
    var z: Int = 0
    var xn = x
    while (xn > 2 * Math.PI) {
        xn -= 2 * Math.PI
    }
    while (xn < -2 * Math.PI) {
        xn += 2 * Math.PI
    }
    while (Math.abs(exprPart) >= eps) {
        factorial = 1.0
        for (i in 2..k) {
            factorial *= i
        }
        xx = sc(xn, k)
        exprPart = xx / factorial
        k += 2
        if (Math.abs(exprPart) >= eps)
            if (z % 2 == 0)
                result += exprPart
            else
                result -= exprPart
        z++
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
    var exprPart: Double = eps
    var result: Double = 0.0
    var k: Int = 0
    var xx: Double
    var factorial: Double
    var z: Int = 0
    var xn = x
    while (xn > 2 * Math.PI) {
        xn -= 2 * Math.PI
    }
    while (xn < -2 * Math.PI) {
        xn += 2 * Math.PI
    }
    while (exprPart >= eps) {
        factorial = 1.0
        for (i in 2..k) {
            factorial *= i
        }
        xx = sc(xn, k)
        exprPart = xx / factorial
        k += 2
        if (z % 2 == 0)
            result += exprPart
        else
            result -= exprPart
        z++
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
    var a: Int = n
    var c: Int = 0
    var d: Int
    var nn: Int = 0
    while (a > 0) {
        c++
        a /= 10
    }
    a = n
    for (i in 1..c) {
        d = a % 10
        a /= 10
        nn += d * scint(10, (c - i))
    }
    return nn
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var a: Int = n
    var c: Int = 0
    while (a > 0) {
        c++
        a /= 10
    }
    a = n
    while (a >= 10) {
        if (a / scint(10, c - 1) != a % 10)
            return false
        a %= scint(10, c - 1)
        a /= 10
        c -= 2
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
    var a: Int = n
    var t: Int
    var c = 0
    while (a > 0) {
        c++
        a /= 10
    }
    for (i in 1..c - 1) {
        a = n
        t = a / scint(10, c - 1) % 10
        while (a > 0) {
            if (t != a % 10)
                return true
            a /= 10
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
 */
fun squareSequenceDigit(n: Int): Int {
    var flag: Boolean = true
    var b = 0
    var a = 0
    var i = 1
    var i2: Int
    var z: Int = 0
    var c: Int
    while (flag) {
        i2 = i * i
        c = 0
        a = i2
        while (i2 > 0) {
            c++
            i2 /= 10
        }
        for (k in 1..c) {
            z += 1
            if (n == z) {
                if (a >= 10) {
                    b = a / scint(10, c - 1)
                    flag = false
                } else {
                    b = a
                    flag = false
                }
            }
            a %= scint(10, c - k)
        }
        i++
    }
    return b
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var flag = true
    var b = 0
    var a = 0
    var i = 1
    var i2: Int
    var z = 0
    var c: Int
    while (flag) {
        i2 = fib(i)
        c = 0
        a = i2
        while (i2 > 0) {
            c++
            i2 /= 10
        }
        for (k in 1..c) {
            z += 1
            if (n == z) {
                if (a >= 10) {
                    b = a / scint(10, c - 1)
                    flag = false
                } else {
                    b = a
                    flag = false
                }
            }
            a %= scint(10, c - k)
        }
        i++
    }
    return b
}

