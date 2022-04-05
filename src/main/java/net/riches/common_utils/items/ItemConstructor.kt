package net.riches.common_utils.items

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType


class ItemConstructor : ItemStack {
    constructor(material: Material?, amount: Int) : super(material!!, amount) {}

    /**
     * Constructs a new ItemConstructor. An ItemBuilder extends ItemStack, an can be used as such.
     * @param material The type of the item
     */
    constructor(material: Material?) : super(material!!) {}

    /**
     * Constructs an ItemConstructor using a pre-existing item
     * @param item The item to copy
     */
    constructor(item: ItemStack?) : super(item!!) {}

    /**
     * Sets the stack size of this ItemConstructor
     * @param amount The number of items in the stack
     * @return The ItemConstructor with the new stack size
     */
    fun setCount(amount: Int): ItemConstructor {
        setAmount(amount)
        return this
    }

    /**
     * Adds an enchantment to this ItemConstructor
     * @param enchant The enchantment to add
     * @param level The level of the enchantment
     * @return The enchanted ItemConstructor
     */
    fun addEnchant(enchant: Enchantment?, level: Int): ItemConstructor {
        ItemUtilities.addEnchant(this, enchant, level)
        return this
    }

    /**
     * Converts this ItemConstructor to a normal ItemStack. Useful because there are some inconsistencies within Spigot using this class.
     * @return An ItemStack copy of this ItemConstructor
     */
    fun toItemStack(): ItemStack {
        return ItemStack(this)
    }

    /**
     * Set the lore of this ItemConstructor
     * @param lore The lines of lore
     * @return The ItemConstructor with lore added
     */
    fun setLore(lore: String?): ItemConstructor {
        ItemUtilities.setLore(this, lore)
        return this
    }

    /**
     * Add a line of lore to this ItemConstructor
     * @param line The line of lore
     * @return The ItemConstructor with lore added
     */
    fun addLore(line: String?): ItemConstructor {
        ItemUtilities.addLore(this, line)
        return this
    }

    /**
     * Add multiple lines of lore to this ItemConstructor
     * @param lines The lines of lore
     * @return The ItemConstructor with lore added
     */
    fun addLore(lines: List<String?>): ItemConstructor {
        ItemUtilities.addLore(this, lines)
        return this
    }

    /**
     * Renames this ItemConstructor
     * @param name The name to set
     * @return The renamed ItemConstructor
     */
    fun setName(name: String?): ItemConstructor {
        ItemUtilities.rename(this, name)
        return this
    }

    /**
     * Set the durability (damage) of the ItemConstructor
     * @param durability The durability to set
     * @return The ItemConstructor with its durability changed
     */
    fun setDurability(durability: Int): ItemConstructor {
        this.durability = durability.toShort()
        return this
    }

    /**
     * Adds an attribute to this ItemConstructor
     * @param attribute The Attribute to be added
     * @param modifier The AttributeModifier to be added
     * @return The ItemConstructor with the attribute added
     */
    fun addAttribute(attribute: Attribute?, modifier: AttributeModifier?): ItemConstructor {
        ItemUtilities.addAttribute(this, attribute, modifier)
        return this
    }

    /**
     * Adds an attribute to this ItemConstructor
     * @param attribute The attribute to be added
     * @param amount The amount of the modifier
     * @param operation The operation of the modifier
     * @return The ItemConstructor with the attribute added
     */
    fun addAttribute(attribute: Attribute, amount: Double, operation: AttributeModifier.Operation?): ItemConstructor {
        ItemUtilities.addAttribute(this, attribute, amount, operation)
        return this
    }

    /**
     * Adds an attribute to this ItemConstructor
     * @param attribute The attribute to be added
     * @param amount The amount of the modifier
     * @param operation The operation of the modifier
     * @param slot The slot the modifier affects
     * @return The ItemConstructor with the attribute added
     */
    fun addAttribute(
        attribute: Attribute,
        amount: Double,
        operation: AttributeModifier.Operation?,
        slot: EquipmentSlot?
    ): ItemConstructor {
        ItemUtilities.addAttribute(this, attribute, amount, operation, slot)
        return this
    }

    /**
     * Adds ItemFlags to this ItemConstructor
     * @param flags The ItemFlags to add
     * @return The ItemConstructor with the flags added
     */
    fun addItemFlags(vararg flags: ItemFlag?): ItemConstructor {
        ItemUtilities.addItemFlags(this, *flags)
        return this
    }

    /**
     * Adds damage to this ItemConstructor
     * @param damage The amount of damage to apply
     * @return The ItemConstructor with the damage applied
     */
    fun addDamage(damage: Int): ItemConstructor {
        ItemUtilities.damage(this, damage)
        return this
    }

    /**
     * Sets the custom model data of this ItemConstructor
     * @param customModelData The custom model data to set
     * @return The ItemConstructor with the custom model data set
     */
    fun setCustomModelData(customModelData: Int): ItemConstructor {
        ItemUtilities.setCustomModelData(this, customModelData)
        return this
    }

    /**
     * Add persistent tags to this ItemConstructor
     * @param key The key to add the data under
     * @param type The type of the data
     * @param data The data to store
     * @param <T> The primary object type
     * @param <Z> The retrieved object type
     * @return The ItemConstructor with the persistent data added
    </Z></T> */
//    fun <T, Z> addPersistentTag(key: NamespacedKey?, type: PersistentDataType<T, Z>?, data: Z): ItemConstructor {
//        ItemUtilities.addPersistentTag(this, key, type, data)
//        return this
//    }

    /**
     * Sets this ItemConstructor to be unbreakable
     * @return The ItemConstructor with the unbreakable tag added
     */
    fun unbreakable(): ItemConstructor {
        ItemUtilities.setUnbreakable(this)
        return this
    }
}