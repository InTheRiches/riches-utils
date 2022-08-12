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
     * Sets the owning player of this SkullConstructor
     * @param player The Player that is the owner
     * @return The SkullConstructor with the owner added
     */
    public SkullConstructor setOwningPlayer(OfflinePlayer player) {
        ItemUtilities.setSkullOwningPlayer(this, player);
        return this;
    }
}
