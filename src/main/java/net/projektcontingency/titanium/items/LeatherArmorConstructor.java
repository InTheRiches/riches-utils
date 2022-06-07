package net.projektcontingency.titanium.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class LeatherArmorConstructor extends ItemStack {
    /**
     * Constructs a new LeatherArmorConstructor. An LeatherArmorConstructor extends ItemStack, an can be used as such.
     * @param material The type of the item
     */
    public LeatherArmorConstructor(Material material) {
        super(material);
    }

    /**
     * Constructs an LeatherArmorConstructor using a pre-existing item
     * @param item The item to copy
     */
    public LeatherArmorConstructor(ItemStack item) {
        super(item);
    }

    /**
     * Sets the stack size of this LeatherArmorConstructor
     * @param amount The number of items in the stack
     * @return The LeatherArmorConstructor with the new stack size
     */
    public LeatherArmorConstructor setCount(int amount) {
        setAmount(amount);
        return this;
    }

    /**
     * Adds an enchantment to this LeatherArmorConstructor
     * @param enchant The enchantment to add
     * @param level The level of the enchantment
     * @return The enchanted LeatherArmorConstructor
     */
    public LeatherArmorConstructor addEnchant(Enchantment enchant, int level) {
        ItemUtilities.addEnchant(this, enchant, level);
        return this;
    }

    /**
     * Converts this LeatherArmorConstructor to a normal ItemStack. Useful because there are some inconsistencies within Spigot using this class.
     * @return An ItemStack copy of this LeatherArmorConstructor
     */
    public ItemStack toItemStack() {
        return new ItemStack(this);
    }

    /**
     * Set the lore of this LeatherArmorConstructor
     * @param lore The lines of lore
     * @return The LeatherArmorConstructor with lore added
     */
    public LeatherArmorConstructor setLore(String... lore) {
        ItemUtilities.setLore(this, lore);
        return this;
    }

    /**
     * Add a line of lore to this LeatherArmorConstructor
     * @param line The line of lore
     * @return The LeatherArmorConstructor with lore added
     */
    public LeatherArmorConstructor addLore(String line) {
        ItemUtilities.addLore(this, line);
        return this;
    }

    /**
     * Add multiple lines of lore to this LeatherArmorConstructor
     * @param lines The lines of lore
     * @return The LeatherArmorConstructor with lore added
     */
    public LeatherArmorConstructor addLore(String... lines) {
        ItemUtilities.addLore(this, lines);
        return this;
    }

    /**
     * Renames this LeatherArmorConstructor
     * @param name The name to set
     * @return The renamed LeatherArmorConstructor
     */
    public LeatherArmorConstructor setName(String name) {
        ItemUtilities.rename(this, name);
        return this;
    }

    /**
     * Adds ItemFlags to this LeatherArmorConstructor
     * @param flags The ItemFlags to add
     * @return The LeatherArmorConstructor with the flags added
     */
    public LeatherArmorConstructor addItemFlags(ItemFlag... flags) {
        ItemUtilities.addItemFlags(this, flags);
        return this;
    }

    public LeatherArmorConstructor setColor(Color color) {
        ItemUtilities.setDyeColor(this, color);
        return this;
    }
}
