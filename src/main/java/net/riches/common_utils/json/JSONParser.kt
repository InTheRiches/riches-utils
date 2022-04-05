package net.riches.common_utils.json

object JSONParser {
    /**
     * Parse a JSONList from a JSON string
     * @param json The JSON string
     * @return The JSONList parsed out of it
     */
    fun parseList(json: String): JSONList? {
        return parse(json) as JSONList?
    }

    /**
     * Parse a JSONMap from a JSON string
     * @param json The JSON string
     * @return TThe JSONList parsed out of it
     */
    fun parseMap(json: String): JSONMap? {
        return parse(json) as JSONMap?
    }

    private fun parse(json: String): JSONStorage? {
        var parentType: Type
        parentType = when (json[0]) {
            '[' -> Type.LIST
            '{' -> Type.MAP
            else -> throw IllegalArgumentException("Invalid JSON input")
        }
        var quoted = false
        var currentParent: JSONStorage? = if (parentType == Type.LIST) JSONList() else JSONMap()
        val root = currentParent
        var cursor = 1
        var lastChar = -1
        var end = false
        var key: String? = null
        var type = Type.INT
        val chars = json.toCharArray()
        var i = 1
        while (i < json.length) {
            when (chars[i]) {
                ' ', '\t', '\n', '\r' -> if (!quoted && lastChar == -1) {
                    cursor = i + 1
                }
                '\\' -> {
                    i++
                    lastChar = i
                }
                'n' -> {
                    if (!quoted) {
                        type = Type.NULL
                    }
                    lastChar = i
                }
                '"' -> {
                    quoted = !quoted
                    lastChar = i
                    type = Type.STRING
                }
                '.' -> {
                    if (!quoted) {
                        type = Type.DOUBLE
                    }
                    lastChar = i
                }
                't', 'f' -> if (!quoted) {
                    type = Type.BOOLEAN
                }
                ':' -> {
                    if (quoted) {
                        break
                    }
                    key = json.substring(cursor + 1, lastChar)
                    type = Type.INT
                    cursor = i + 1
                    lastChar = -1
                }
                ']', '}' -> {
                    if (quoted) {
                        break
                    }
                    end = true
                    if (quoted) {
                        break
                    }
                    if (lastChar != -1) {
                        var value: Any? = null
                        when (type) {
                            Type.STRING -> value = substring(chars, cursor + 1, lastChar)
                            Type.INT -> value = parseInteger(json.substring(cursor, lastChar + 1))
                            Type.DOUBLE -> value = parseDouble(json.substring(cursor, lastChar + 1))
                            Type.BOOLEAN -> value = chars[cursor] == 't'
                            Type.NULL -> value = null
                        }
                        currentParent!!.add(key, value)
                        key = null
                    } else {
                        end = when (chars[i]) {
                            ']', '}' -> true
                            else -> false
                        }
                    }
                    type = Type.INT
                    if (end) {
                        val prev = currentParent
                        currentParent = currentParent!!.parent
                        parentType = if (currentParent is JSONList) Type.LIST else Type.MAP
                        if (currentParent != null) {
                            if (currentParent.tempKey != null) {
                                currentParent.add(currentParent.tempKey, prev)
                                currentParent.tempKey = null
                            }
                        }
                    }
                    lastChar = -1
                    cursor = i + 1
                    end = false
                }
                ',' -> {
                    if (quoted) {
                        break
                    }
                    if (lastChar != -1) {
                        var value: Any? = null
                        when (type) {
                            Type.STRING -> value = substring(chars, cursor + 1, lastChar)
                            Type.INT -> value = parseInteger(json.substring(cursor, lastChar + 1))
                            Type.DOUBLE -> value = parseDouble(json.substring(cursor, lastChar + 1))
                            Type.BOOLEAN -> value = chars[cursor] == 't'
                            Type.NULL -> value = null
                        }
                        currentParent!!.add(key, value)
                        key = null
                    } else {
                        end = when (chars[i]) {
                            ']', '}' -> true
                            else -> false
                        }
                    }
                    type = Type.INT
                    if (end) {
                        val prev = currentParent
                        currentParent = currentParent!!.parent
                        parentType = if (currentParent is JSONList) Type.LIST else Type.MAP
                        if (currentParent != null) {
                            if (currentParent.tempKey != null) {
                                currentParent.add(currentParent.tempKey, prev)
                                currentParent.tempKey = null
                            }
                        }
                    }
                    lastChar = -1
                    cursor = i + 1
                    end = false
                }
                '{', '[' -> {
                    if (quoted) {
                        break
                    }
                    currentParent!!.tempKey = key ?: ""
                    key = null
                    var next: JSONStorage
                    next = if (chars[i] == '[') JSONList() else JSONMap()
                    next.parent = currentParent
                    currentParent = next
                    cursor = i + 1
                    lastChar = -1
                }
                else -> lastChar = i
            }
            i++
        }
        return root
    }

    private fun parseDouble(input: String): Double {
        var i = 0
        var negative = false
        if (input[0] == '-') {
            negative = true
            i++
        }
        var output = 0.0
        var after = 0.0
        var decimal = -1
        while (i < input.length) {
            val c = input[i]
            if (c == '.') {
                if (decimal != -1) {
                    throw NumberFormatException("Second period in double")
                }
                decimal = i
                i++
                continue
            }
            if (c > '9' || c < '0') {
                throw NumberFormatException("Non-numeric character")
            }
            if (decimal != -1) {
                after *= 10.0
                after += (c.code - '0'.code).toDouble()
            } else {
                output *= 10.0
                output += (c.code - '0'.code).toDouble()
            }
            i++
        }
        after /= Math.pow(10.0, (input.length - decimal - 1).toDouble())
        return if (negative) -output - after else output + after
    }

    private fun parseInteger(input: String): Any {
        var i = 0
        var negative = false
        if (input[0] == '-') {
            negative = true
            i++
        }
        var output: Long = 0
        while (i < input.length) {
            val c = input[i]
            if (c == 'L') {
                i++
                continue
            }
            if (c > '9' || c < '0') {
                throw NumberFormatException("Non-numeric character")
            }
            output *= 10
            output += (c.code - '0'.code).toLong()
            i++
        }
        output = if (negative) -output else output
        return if (output > Int.MAX_VALUE || output < Int.MIN_VALUE) {
            output
        } else output.toInt()
    }

    private fun substring(chars: CharArray, start: Int, end: Int): String {
        val builder = StringBuilder()
        var i = start
        while (i < end) {
            val c = chars[i]
            if (c == '\\') {
                builder.append(chars[i + 1])
                i++
                i++
                continue
            }
            builder.append(c)
            i++
        }
        return builder.toString()
    }

    private enum class Type {
        LIST, MAP, STRING, BOOLEAN, DOUBLE, INT, NULL
    }
}