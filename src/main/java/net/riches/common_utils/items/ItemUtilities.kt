package net.riches.common_utils.items

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.OfflinePlayer
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.DelegateDeserialization
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import net.riches.common_utils.json.JSONList
import net.riches.common_utils.json.JSONMap
import net.riches.common_utils.json.JSONParser
import java.lang.reflect.TypeVariable
import java.util.*
import java.util.function.BiConsumer
import java.util.function.BiPredicate
import java.util.function.Consumer
import java.util.function.Function

object ItemUtilities {
    /**
     * Creates a player skull
     * @param owner The owning player
     * @return The skull for the player
     */
    fun skull(owner: OfflinePlayer): ItemStack {
        val base = baseSkull
        val meta = base.itemMeta as SkullMeta?
        meta!!.owningPlayer = owner
        base.itemMeta = meta
        return base
    }

    private val skullType = Material.valueOf("PLAYER_HEAD")
    private val baseSkull: ItemStack get() = ItemStack(skullType)

    /**
     * Renames an ItemStack, functionally identical to [ItemUtils.setName] but kept for legacy reasons
     * @param item The ItemStack to be renamed
     * @param name The name to give the ItemStack
     * @return The renamed ItemStack
     */
    fun rename(item: ItemStack, name: String?): ItemStack {
        val meta = item.itemMeta
        meta!!.setDisplayName(name)
        item.itemMeta = meta
        return item
    }

    /**
     * Renames an ItemStack
     * @param item The ItemStack to be renamed
     * @param name The name to give the ItemStack
     * @return The renamed ItemStack
     */
    fun setName(item: ItemStack, name: String?): ItemStack {
        return rename(item, name)
    }

    /**
     * Set a single line of lore for an ItemStack
     * @param item The ItemStack to be given lore
     * @param line The line of lore to be given
     * @return The modified ItemStack
     */
    fun setLore(item: ItemStack, line: String): ItemStack {
        val meta = item.itemMeta
        val lore: MutableList<String> = ArrayList()
        lore.add(line)
        meta!!.lore = lore
        item.itemMeta = meta
        return item
    }

    /**
     * Set multiple lines of lore for an ItemStack
     * @param item The ItemStack to be given lore
     * @param lore The lines of lore to be given
     * @return The modified ItemStack
     */
    fun setLore(item: ItemStack, lore: List<String?>?): ItemStack {
        val meta = item.itemMeta
        meta!!.lore = lore
        item.itemMeta = meta
        return item
    }

    /**
     * Add a line of lore to an ItemStack
     * @param item The ItemStack to be given lore
     * @param line The line of lore to add
     * @return The modified ItemStack
     */
    fun addLore(item: ItemStack, line: String?): ItemStack {
        val meta = item.itemMeta
        var lore = meta!!.lore
        lore = lore ?: ArrayList()
        lore.add(line)
        meta.lore = lore
        item.itemMeta = meta
        return item
    }

    /**
     * Add multiple lines of lore for an ItemStack
     * @param item The ItemStack to be given lore
     * @param lore The lines of lore to be given
     * @return The modified ItemStack
     */
    fun addLore(item: ItemStack, vararg lore: String?): ItemStack {
        return addLore(item, Arrays.asList(*lore))
    }

    /**
     * Adds multiple lines of lore to an ItemStack
     * @param item The ItemStack to be given lore
     * @param lines The lines or lore to add
     * @return The modified ItemStack
     */
    fun addLore(item: ItemStack, lines: List<String?>?): ItemStack {
        val meta = item.itemMeta
        var lore = meta!!.lore
        lore = lore ?: ArrayList()
        lore.addAll(lines!!)
        meta.lore = lore
        item.itemMeta = meta
        return item
    }

    /**
     * Sets the color of dye-able armor.
     * @param item The ItemStack to be given the color
     * @param color The color to give
     * @return The modified ItemStack
     */
    fun setDyeColor(item: ItemStack, color: Color?): ItemStack {
        val meta2 = item.itemMeta as LeatherArmorMeta?
        meta2!!.setColor(Color.GREEN)
        item.itemMeta = meta2
        return item
    }

    /**
     * Sets the owner of a skull
     * @param item The ItemStack to be set the owner
     * @param player The Player to set as owner
     * @return The modified ItemStack
     */
    fun setSkullOwningPlayer(item: ItemStack, player: OfflinePlayer): ItemStack {
        val profileMeta = item.itemMeta as SkullMeta?
        profileMeta!!.owningPlayer = player.player
        item.itemMeta = profileMeta
        return item
    }

    /**
     * Set multiple lines of lore for an ItemStack
     * @param item The ItemStack to be given lore
     * @param lore The lines of lore to be given
     * @return The modified ItemStack
     */
    fun setLore(item: ItemStack, vararg lore: String?): ItemStack {
        return setLore(item, Arrays.asList(*lore))
    }

    /**
     * Sets an item to be unbreakable
     * @param item The item to make unbreakable
     * @return The unbreakable item
     */
    fun setUnbreakable(item: ItemStack): ItemStack {
        var item = item
        item = item.clone()
        val meta = item.itemMeta
        meta!!.isUnbreakable = true
        item.itemMeta = meta
        return item
    }

    /**
     * Add an enchantment to an ItemStack
     * @param item The ItemStack to be enchanted
     * @param enchant The Enchantment to add to the ItemStack
     * @param level The level of the Enchantment
     * @return The enchanted ItemStack
     */
    fun addEnchant(item: ItemStack, enchant: Enchantment?, level: Int): ItemStack {
        val meta = item.itemMeta
        meta!!.addEnchant(enchant!!, level, true)
        if (level == 0) {
            meta.removeEnchant(enchant)
        }
        item.itemMeta = meta
        return item
    }

    /**
     * Add an attribute to the item
     * @param item The item to have an attribute added
     * @param attribute The Attribute to be added
     * @param modifier The AttributeModifier to be added
     * @return The modified ItemStack
     */
    fun addAttribute(item: ItemStack, attribute: Attribute?, modifier: AttributeModifier?): ItemStack {
        val meta = item.itemMeta
        meta!!.addAttributeModifier(attribute!!, modifier!!)
        item.itemMeta = meta
        return item
    }

    /**
     * Add an attribute to the item
     * @param item The item to have an attribute added
     * @param attribute The Attribute to be added
     * @param amount The amount to modify it by
     * @param operation The operation by which the value will be modified
     * @return The modified item
     */
    fun addAttribute(
        item: ItemStack,
        attribute: Attribute,
        amount: Double,
        operation: AttributeModifier.Operation?
    ): ItemStack {
        val meta = item.itemMeta
        val modifier = AttributeModifier(attribute.toString(), amount, operation!!)
        meta!!.addAttributeModifier(attribute, modifier)
        item.itemMeta = meta
        return item
    }

    /**
     * Adds ItemFlags to the item
     * @param item The item to add ItemFlags to
     * @param flags The ItemFlags to add
     * @return The modified item
     */
    fun addItemFlags(item: ItemStack, vararg flags: ItemFlag?): ItemStack {
        val meta = item.itemMeta
        meta!!.addItemFlags(*flags)
        item.itemMeta = meta
        return item
    }

    /**
     * Sets the custom model data of the item
     * @param item The item to set the custom model data for
     * @param customModelData The custom model data to set
     * @return The modified item
     */
    fun setCustomModelData(item: ItemStack, customModelData: Int): ItemStack {
        val meta = item.itemMeta
        meta!!.setCustomModelData(customModelData)
        item.itemMeta = meta
        return item
    }

    /**
     * Adds persistent data to the item
     * @param item The item to add persistent data to
     * @param key The key to add the data under
     * @param type The type of the data
     * @param data The data to store
     * @param <T> The primary object type
     * @param <Z> The retrieved object type
     * @return The modified item
    </Z></T> */
//    fun <T, Z> addPersistentTag(
//        item: ItemStack,
//        key: NamespacedKey?,
//        type: PersistentDataType<T, Z>?,
//        data: Z
//    ): ItemStack {
//        val meta = item.itemMeta
//        meta!!.persistentDataContainer.set(key!!, type, data)
//        item.itemMeta = meta
//        return item
//    }

    /**
     * Add an attribute to the item
     * @param item The item to have an attribute added
     * @param attribute The Attribute to be added
     * @param amount The amount to modify it by
     * @param operation The operation by which the value will be modified
     * @param slot The slot this attribute will be effective in
     * @return The modified item
     */
    fun addAttribute(
        item: ItemStack,
        attribute: Attribute,
        amount: Double,
        operation: AttributeModifier.Operation?,
        slot: EquipmentSlot?
    ): ItemStack {
        val meta = item.itemMeta
        val modifier = AttributeModifier(
            UUID.randomUUID(), attribute.toString(), amount,
            operation!!, slot
        )
        meta!!.addAttributeModifier(attribute, modifier)
        item.itemMeta = meta
        return item
    }

    /**
     * Damages an item
     * @param item The item to damage
     * @param amount How much damage to apply
     * @return The damaged item
     * @throws IllegalArgumentException if the item is not damageable
     */
    fun damage(item: ItemStack, amount: Int): ItemStack {
        val meta = item.itemMeta
        require(meta is Damageable) { "Item must be damageable" }
        val d = meta
        d.damage = d.damage + amount
        item.itemMeta = meta
        return item
    }
    /**
     * Counts the number of the given item in the given inventory
     * @param inv The inventory to count the items in
     * @param item The item to count
     * @param comparison A filter to compare items for counting
     * @return The number of items found
     */
    /**
     * Counts the number of the given item in the given inventory
     * @param inv The inventory to count the items in
     * @param item The item to count
     * @return The number of items found
     */
    @JvmOverloads
    fun count(
        inv: Inventory,
        item: ItemStack,
        comparison: BiPredicate<ItemStack, ItemStack?> = BiPredicate { obj: ItemStack, stack: ItemStack? ->
            obj.isSimilar(
                stack
            )
        }
    ): Int {
        var count = 0
        for (i in inv) {
            if (comparison.test(item, i)) {
                count += i.amount
            }
        }
        return count
    }

    /**
     * Counts the number of items of the given type in the given inventory
     * @param inv The inventory to count the items in
     * @param type The type of item to count
     * @return The number of items found
     */
    fun count(inv: Inventory, type: Material?): Int {
        return count(
            inv, ItemStack(type!!)
        ) { a: ItemStack?, b: ItemStack? -> compare(a, b, ItemTrait.TYPE) }
    }
    /**
     * Removes the specified amount of the given item from the given inventory
     * @param inv The inventory to remove the items from
     * @param item The item to be removed
     * @param amount The amount of items to remove
     * @param comparison A filter to compare items for removal
     * @return Whether the amount specified could be removed. False if it removed less than specified.
     */
    /**
     * Removes the specified amount of the given item from the given inventory
     * @param inv The inventory to remove the items from
     * @param item The item to be removed
     * @param amount The amount of items to remove
     * @return Whether the amount specified could be removed. False if it removed less than specified.
     */
    @JvmOverloads
    fun remove(
        inv: Inventory,
        item: ItemStack,
        amount: Int,
        comparison: BiPredicate<ItemStack, ItemStack?> = BiPredicate { obj: ItemStack, stack: ItemStack? ->
            obj.isSimilar(
                stack
            )
        }
    ): Boolean {
        var amount = amount
        val contents = inv.contents
        var i = 0
        while (i < contents.size && amount > 0) {
            if (!comparison.test(item, contents[i])) {
                i++
                continue
            }
            if (amount >= contents[i]!!.amount) {
                amount -= contents[i]!!.amount
                contents[i] = null
                if (amount == 0) {
                    inv.contents = contents
                    return true
                }
                i++
                continue
            }
            contents[i]!!.amount = contents[i]!!.amount - amount
            inv.contents = contents
            return true
            i++
        }
        inv.contents = contents
        return false
    }

    /**
     * Removes the specified amount of the given item type from the given inventory
     * @param inv The inventory to remove the items from
     * @param type The item type to be removed
     * @param amount The amount of items to remove
     * @return Whether the amount specified could be removed. False if it removed less than specified.
     */
    fun remove(inv: Inventory, type: Material?, amount: Int): Boolean {
        return remove(
            inv, ItemStack(type!!), amount
        ) { a: ItemStack?, b: ItemStack? -> compare(a, b, ItemTrait.TYPE) }
    }
    /**
     * Remove all matching items up to a maximum, returning the number that were removed
     * @param inv The inventory to count and remove items from
     * @param item The item to count and remove
     * @param max The maximum number of items to remove
     * @param comparison A filter to compare items for counting and removal
     * @return How many items were removed
     */
    /**
     * Remove all matching items, returning the number that were removed
     * @param inv The inventory to count and remove items from
     * @param item The item to count and remove
     * @return How many items were removed
     */
    /**
     * Remove all matching items up to a maximum, returning the number that were removed
     * @param inv The inventory to count and remove items from
     * @param item The item to count and remove
     * @param max The maximum number of items to remove
     * @return How many items were removed
     */
    @JvmOverloads
    fun countAndRemove(
        inv: Inventory,
        item: ItemStack,
        max: Int = Int.MAX_VALUE,
        comparison: BiPredicate<ItemStack, ItemStack?> = BiPredicate { obj: ItemStack, stack: ItemStack? ->
            obj.isSimilar(
                stack
            )
        }
    ): Int {
        var count = count(inv, item, comparison)
        count = Math.min(max, count)
        remove(inv, item, count, comparison)
        return count
    }

    /**
     * Remove all matching items up to a maximum, returning the number that were removed
     * @param inv The inventory to count and remove items from
     * @param type The item type to count and remove
     * @param max The maximum number of items to remove
     * @return How many items were removed
     */
    fun countAndRemove(inv: Inventory, type: Material?, max: Int): Int {
        return countAndRemove(
            inv, ItemStack(type!!), max
        ) { a: ItemStack?, b: ItemStack? -> compare(a, b, ItemTrait.TYPE) }
    }

    /**
     * Remove all items of a specified type, returning the number that were removed
     * @param inv The inventory to count and remove items from
     * @param type The item type to count and remove
     * @return How many items were removed
     */
    fun countAndRemove(inv: Inventory, type: Material?): Int {
        return countAndRemove(
            inv, ItemStack(type!!), Int.MAX_VALUE
        ) { a: ItemStack?, b: ItemStack? ->
            compare(
                a,
                b,
                ItemTrait.TYPE
            )
        }
    }

    /**
     * Give the player the specified items, dropping them on the ground if there is not enough room
     * @param player The player to give the items to
     * @param items The items to be given
     */
    fun give(player: Player, vararg items: ItemStack?) {
        player.inventory.addItem(*items).values.forEach(Consumer { i: ItemStack? ->
            player.world.dropItem(
                player.location,
                i!!
            )
        })
    }

    /**
     * Gives the player the specified amount of the specified item, dropping them on the ground if there is not enough room
     * @param player The player to give the items to
     * @param item The item to be given to the player
     * @param amount The amount the player should be given
     */
    fun give(player: Player, item: ItemStack, amount: Int) {
        var amount = amount
        require(amount >= 1) { "Amount must be greater than 0" }
        val stackSize = item.type.maxStackSize
        while (amount > stackSize) {
            val clone = item.clone()
            clone.amount = stackSize
            give(player, clone)
            amount -= stackSize
        }
        val clone = item.clone()
        clone.amount = amount
        give(player, clone)
    }

    /**
     * Gives the player the specified amount of the specified item type, dropping them on the ground if there is not enough room
     * @param player The player to give the items to
     * @param type The item type to be given to the player
     * @param amount The amount the player should be given
     */
    fun give(player: Player, type: Material?, amount: Int) {
        give(player, ItemStack(type!!), amount)
    }

    /**
     * Compares the traits of two items
     * @param first The first ItemStack
     * @param second The second ItemStack
     * @param traits The ItemTraits to compare
     * @return Whether the two items are identical in terms of the traits provided. Returns true if both items are null, and false if only one is null.
     */
    fun compare(first: ItemStack?, second: ItemStack?, vararg traits: ItemTrait?): Boolean {
        if (first === second) {
            return true
        }
        if (first == null || second == null) {
            return false
        }
        for (trait in traits) {
            if (!trait?.compare(first, second)!!) {
                return false
            }
        }
        return true
    }

    /**
     * Compares the type, name, and lore of two items
     * @param first The first ItemStack
     * @param second The second ItemStack
     * @return Whether the two items are identical in terms of type, name, and lore. Returns true if both items are null, and false if only one is null.
     */
    fun compare(first: ItemStack?, second: ItemStack?): Boolean {
        return compare(first, second, ItemTrait.TYPE, ItemTrait.NAME, ItemTrait.LORE)
    }

    /**
     * Calculates the minimum chest size (next highest multiple of 9) required to fit the given number of item stacks
     * @param items The number of item stacks
     * @return The minimum chest size to accommodate the items
     */
    fun minimumChestSize(items: Int): Int {
        return Math.max(9.0, Math.ceil(items / 9.0) * 9).toInt()
    }
}
