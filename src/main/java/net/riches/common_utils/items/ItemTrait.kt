package net.riches.common_utils.items

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*
import java.util.function.BiPredicate


/**
 * A set of comparable traits items have and can be compared with [ItemUtils.compare]
 * @author Redempt
 */
enum class ItemTrait(private val compare: BiPredicate<ItemStack, ItemStack>) {
    /**
     * For comparing the durability of two items
     */
    DURABILITY(BiPredicate { a: ItemStack, b: ItemStack -> a.durability == b.durability }),

    /**
     * For comparing the amount of two items
     */
    AMOUNT(BiPredicate { a: ItemStack, b: ItemStack -> a.amount == b.amount }),

    /**
     * For comparing the display name of two items
     */
    NAME(BiPredicate { a: ItemStack, b: ItemStack ->
        Optional.ofNullable<ItemMeta>(a.itemMeta)
            .map<String> { obj: ItemMeta -> obj.displayName } == Optional.ofNullable<ItemMeta>(
            b.itemMeta
        ).map<String> { obj: ItemMeta -> obj.displayName }
    }),

    /**
     * For comparing the lore of two items
     */
    LORE(BiPredicate { a: ItemStack, b: ItemStack ->
        Optional.ofNullable<ItemMeta>(a.itemMeta)
            .map<List<String>?> { obj: ItemMeta -> obj.lore } == Optional.ofNullable<ItemMeta>(
            b.itemMeta
        ).map<List<String>?> { obj: ItemMeta -> obj.lore }
    }),

    /**
     * For comparing the enchantments of two items
     */
    ENCHANTMENTS(BiPredicate { a: ItemStack, b: ItemStack -> a.enchantments == b.enchantments }),

    /**
     * For comparing the types of two items
     */
    TYPE(BiPredicate { a: ItemStack, b: ItemStack -> a.type == b.type });

    /**
     * Compares this trait on the two items
     * @param a The first item
     * @param b The second item
     * @return True if the trait is the same on both items, false otherwise
     */
    fun compare(a: ItemStack, b: ItemStack): Boolean {
        return compare.test(a, b)
    }
}