package net.projektcontingency.titanium.items;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class SkullConstructor extends ItemConstructor {
    /**
     * Constructs a new SkullConstructor. An SkullConstructor extends ItemStack, and can be used as such.
     * @param material The type of the item
     */
    public SkullConstructor(Material material) {
        super(material);
    }

    /**
     * Constructs an LeatherArmorConstructor using a pre-existing item
     * @param item The item to copy
     */
    public SkullConstructor(ItemStack item) {
        super(item);
    }

    /**
     * Sets the stack size of this SkullConstructor
     * @param amount The number of items in the stack
     * @return The SkullConstructor with the new stack size
     */
    public SkullConstructor setCount(int amount) {
        setAmount(amount);
        return this;
    }



    /**
     * Adds an enchantment to this SkullConstructor
     * @param enchant The enchantment to add
     * @param level The level of the enchantment
     * @return The enchanted SkullConstructor
     */
    public SkullConstructor addEnchant(Enchantment enchant, int level) {
        ItemUtilities.addEnchant(this, enchant, level);
        return this;
    }

    /**
     * Converts this SkullConstructor to a normal ItemStack. Useful because there are some inconsistencies within Spigot using this class.
     * @return An ItemStack copy of this SkullConstructor
     */
    public ItemStack toItemStack() {
        return new ItemStack(this);
    }

    /**
     * Set the lore of this SkullConstructor
     * @param lore The lines of lore
     * @return The SkullConstructor with lore added
     */
    public SkullConstructor setLore(String... lore) {
        ItemUtilities.setLore(this, lore);
        return this;
    }

    /**
     * Add a line of lore to this SkullConstructor
     * @param line The line of lore
     * @return The SkullConstructor with lore added
     */
    public SkullConstructor addLore(String line) {
        ItemUtilities.addLore(this, line);
        return this;
    }

    /**
     * Add multiple lines of lore to this SkullConstructor
     * @param lines The lines of lore
     * @return The SkullConstructor with lore added
     */
    public SkullConstructor addLore(String... lines) {
        ItemUtilities.addLore(this, lines);
        return this;
    }

    /**
     * Renames this SkullConstructor
     * @param name The name to set
     * @return The renamed SkullConstructor
     */
    public SkullConstructor setName(String name) {
        ItemUtilities.rename(this, name);
        return this;
    }

    /**
     * Adds ItemFlags to this SkullConstructor
     * @param flags The ItemFlags to add
     * @return The SkullConstructor with the flags added
     */
    public SkullConstructor addItemFlags(ItemFlag... flags) {
        ItemUtilities.addItemFlags(this, flags);
        return this;
    }

    /**
     * Sets the owning player of this SkullConstructor
     * @param player The Player that is the owner
     * @return The SkullConstructor with the owner added
     */
    public SkullConstructor setOwningPlayer(OfflinePlayer player) {
        ItemUtilities.setSkullOwningPlayer(this, player);
        return this;
    }
}
