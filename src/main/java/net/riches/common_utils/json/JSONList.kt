package net.riches.common_utils.json

import java.util.function.Function
import java.util.stream.Collectors


/**
 * Represents a list which can be serialized to JSON and deserialized back to this form,
 * assuming all of the values it stores are serializable
 */
class JSONList : ArrayList<Any?>(), JSONStorage {
    override var parent: JSONStorage? = null
    override var tempKey: String? = null

    fun getInt(key: Int): Int? {
        val o = get(key)
        if (o is Long) {
            return o.toInt()
        }
        return o as Int?
    }

    fun getBoolean(key: Int): Boolean? {
        return get(key) as Boolean?
    }

    fun getLong(key: Int): Long? {
        return get(key) as Long?
    }

    fun getDouble(key: Int): Double? {
        return get(key) as Double?
    }

    fun getList(key: Int): JSONList? {
        return get(key) as JSONList?
    }

    fun getMap(key: Int): JSONMap? {
        return get(key) as JSONMap?
    }

    fun getString(key: Int): String? {
        return get(key) as String?
    }

    fun <T> cast(clazz: Class<T>): List<T> {
        return stream().map<T>(Function { obj: Any? -> clazz.cast(obj) }).collect(Collectors.toList())
    }

    /**
     * @return A JSON string representing this JSONList
     */
    override fun toString(): String {
        if (size == 0) {
            return "[]"
        }
        val builder = StringBuilder("[")
        for (o in this) {
            if (o is CharSequence) {
                builder.append('"').append(o.toString().replace("\\", "\\\\").replace("\"", "\\\"")).append("\", ")
                continue
            }
            if (o is Long) {
                builder.append(o.toString()).append("L, ")
                continue
            }
            builder.append(o).append(", ")
        }
        return builder.replace(builder.length - 2, builder.length, "]").toString()
    }

    override fun add(key: String?, value: Any?) {
        add(value)
    }
}