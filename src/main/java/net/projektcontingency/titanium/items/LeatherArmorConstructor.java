package net.projektcontingency.titanium.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class LeatherArmorConstructor extends ItemConstructor {
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

    public LeatherArmorConstructor setColor(Color color) {
        ItemUtilities.setDyeColor(this, color);
        return this;
    }
}
