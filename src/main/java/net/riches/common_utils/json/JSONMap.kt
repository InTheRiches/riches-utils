package net.riches.common_utils.json


/**
 * Represents a map which can be serialized to JSON and deserialized back to this form,
 * assuming all of the values it stores are serializable
 */
class JSONMap : HashMap<String?, Any?>(), JSONStorage {
    override var parent: JSONStorage? = null
    override var tempKey: String? = null

    fun getInt(key: String?): Int? {
        val o = get(key)
        if (o is Long) {
            return o as Long.toInt()
        }
        return o as Int?
    }

    fun getBoolean(key: String?): Boolean? {
        return get(key) as Boolean?
    }

    fun getDouble(key: String?): Double? {
        return get(key) as Double?
    }

    fun getLong(key: String?): Long? {
        return get(key) as Long?
    }

    fun getList(key: String?): JSONList? {
        return get(key) as JSONList?
    }

    fun getMap(key: String?): JSONMap? {
        return get(key) as JSONMap?
    }

    fun getString(key: String?): String? {
        return get(key) as String?
    }

    /**
     * @return A JSON string representing this JSONMap
     */
    override fun toString(): String {
        if (size == 0) {
            return "{}"
        }
        val builder = StringBuilder("{")
        for ((key, o): Map.Entry<String?, Any?> in this) {
            builder.append('"').append(key).append('"').append(':')
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
        return builder.replace(builder.length - 2, builder.length, "}").toString()
    }

    override fun add(key: String?, value: Any?) {
        put(key, value)
    }
}