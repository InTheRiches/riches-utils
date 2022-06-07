package net.riches.common_utils.json

interface JSONStorage {
    var parent: JSONStorage?

    fun add(key: String?, value: Any?)
    var tempKey: String?
}