package net.riches.common_utils.internal

class Pair<F, S>(first: F, second: S) {
    private var f: F? = first
    private var s: S? = second

    fun getFirst(): F? {
        return f
    }

    fun getSecond(): S? {
        return s
    }

    fun <T, R> pair(first: F, second: S): Pair<F, S> {
        return Pair(first, second)
    }
}