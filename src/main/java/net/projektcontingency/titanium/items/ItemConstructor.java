package net.projektcontingency.titanium.items;

import net.projektcontingency.titanium.internal.Pair;
import net.projektcontingency.titanium.internal.Translations;
import net.projektcontingency.titanium.internal.StringAlignUtils;
import net.projektcontingency.titanium.internal.Unicode;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ItemConstructor extends ItemStack {
    private ChatColor titleColor = ChatColor.WHITE;
    private String title = "";

    public ItemConstructor(Material material, int amount) {
        super(material, amount);
    }

    /**
     * Constructs a new ItemConstructor. An ItemBuilder extends ItemStack, an can be used as such.
     * @param material The type of the item
     */
    public ItemConstructor(Material material) {
        super(material);
    }

    /**
     * Constructs an ItemConstructor using a pre-existing item
     * @param item The item to copy
     */
    public ItemConstructor(ItemStack item) {
        super(item);
    }

    /**
     * Sets the stack size of this ItemConstructor
     * @param amount The number of items in the stack
     * @return The ItemConstructor with the new stack size
     */
    public ItemConstructor setCount(int amount) {
        setAmount(amount);
        return this;
    }

    public ItemConstructor setTitle(ChatColor color, String title) {
        this.setName(color + StringAlignUtils.alignCenter(32, title));
        this.title = StringAlignUtils.alignCenter(32, title);
        this.titleColor = color;

        return this;
    }

    public Pair<ChatColor, String> getTitle() {
        return new Pair<>(this.titleColor, this.title);
    }

    /**
     * Adds an enchantment to this ItemConstructor
     * @param enchant The enchantment to add
     * @param level The level of the enchantment
     * @return The enchanted ItemConstructor
     */
    public ItemConstructor addEnchant(Enchantment enchant, int level) {
        ItemUtilities.addEnchant(this, enchant, level);
        return this;
    }

    public ItemConstructor addInfo(String title, String... info) {
        this.addLore("");
        this.addLore(Unicode.INFO_MARKER.getText() + ChatColor.AQUA + "  " + title);
        for (String str : info) {
            this.addLore(ChatColor.YELLOW + Unicode.POINT.getText() + " " + str);
        }
        return this;
    }

    /**
     * Converts this ItemConstructor to a normal ItemStack. Useful because there are some inconsistencies within Spigot using this class.
     * @return An ItemStack copy of this ItemConstructor
     */
    public ItemStack toItemStack() {
        return new ItemStack(this);
    }

    /**
     * Set the lore of this ItemConstructor
     * @param lore The lines of lore
     * @return The ItemConstructor with lore added
     */
    public ItemConstructor setLore(String... lore) {
        ItemUtilities.setLore(this, lore);
        return this;
    }
    
    public ItemConstructor setDesc(String... desc) {
        return applyInfo(this, desc);
    }

    public static ItemConstructor applyInfo(ItemConstructor item, String... info) {
        item.addLore(StringAlignUtils.alignCenter(Translations.INFO_DIVIDER.getText()));

        for (String str : info)
            item.addLore(ChatColor.GRAY + StringAlignUtils.alignCenter(str));

        return item.addLore(StringAlignUtils.alignCenter(Translations.INFO_DIVIDER.getText()));
    }

    public ItemConstructor setLore(ChatColor color, String... lore) {
        String[] newLore = new String[lore.length];
        for (int i = 0; i < lore.length; i++) {
            newLore[i] = color + lore[i];
        }
        ItemUtilities.setLore(this, newLore);
        return this;
    }

    /**
     * Add a line of lore to this ItemConstructor
     * @param line The line of lore
     * @return The ItemConstructor with lore added
     */
    public ItemConstructor addLore(String line) {
        ItemUtilities.addLore(this, line);
        return this;
    }

    /**
     * Add multiple lines of lore to this ItemConstructor
     * @param lines The lines of lore
     * @return The ItemConstructor with lore added
     */
    public ItemConstructor addLore(String... lines) {
        ItemUtilities.addLore(this, lines);
        return this;
    }

    /**
     * Renames this ItemConstructor
     * @param name The name to set
     * @return The renamed ItemConstructor
     */
    public ItemConstructor setName(String name) {
        ItemUtilities.rename(this, name);
        return this;
    }

    /**
     * Set the durability (damage) of the ItemConstructor
     * @param durability The durability to set
     * @return The ItemConstructor with its durability changed
     */
    @SuppressWarnings("deprecation")
    public ItemConstructor setDurability(int durability) {
        this.setDurability((short) durability);
        return this;
    }

    /**
     * Adds an attribute to this ItemConstructor
     * @param attribute The Attribute to be added
     * @param modifier The AttributeModifier to be added
     * @return The ItemConstructor with the attribute added
     */
    public ItemConstructor addAttribute(Attribute attribute, AttributeModifier modifier) {
        ItemUtilities.addAttribute(this, attribute, modifier);
        return this;
    }

    /**
     * Adds an attribute to this ItemConstructor
     * @param attribute The attribute to be added
     * @param amount The amount of the modifier
     * @param operation The operation of the modifier
     * @return The ItemConstructor with the attribute added
     */
    public ItemConstructor addAttribute(Attribute attribute, double amount, AttributeModifier.Operation operation) {
        ItemUtilities.addAttribute(this, attribute, amount, operation);
        return this;
    }

    /**
     * Adds an attribute to this ItemConstructor
     * @param attribute The attribute to be added
     * @param amount The amount of the modifier
     * @param operation The operation of the modifier
     * @param slot The slot the modifier affects
     * @return The ItemConstructor with the attribute added
     */
    public ItemConstructor addAttribute(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlot slot) {
        ItemUtilities.addAttribute(this, attribute, amount, operation, slot);
        return this;
    }

    /**
     * Adds ItemFlags to this ItemConstructor
     * @param flags The ItemFlags to add
     * @return The ItemConstructor with the flags added
     */
    public ItemConstructor addItemFlags(ItemFlag... flags) {
        ItemUtilities.addItemFlags(this, flags);
        return this;
    }

    /**
     * Adds damage to this ItemConstructor
     * @param damage The amount of damage to apply
     * @return The ItemConstructor with the damage applied
     */
    public ItemConstructor addDamage(int damage) {
        ItemUtilities.damage(this, damage);
        return this;
    }

    /**
     * Sets the custom model data of this ItemConstructor
     * @param customModelData The custom model data to set
     * @return The ItemConstructor with the custom model data set
     */
    public ItemConstructor setCustomModelData(int customModelData) {
        ItemUtilities.setCustomModelData(this, customModelData);
        return this;
    }

    /**
     * Add persistent tags to this ItemConstructor
     * @param key The key to add the data under
     * @param type The type of the data
     * @param data The data to store
     * @param <T> The primary object type
     * @param <Z> The retrieved object type
     * @return The ItemConstructor with the persistent data added
     */
    public <T, Z> ItemConstructor addPersistentTag(NamespacedKey key, PersistentDataType<T, Z> type, Z data) {
        ItemUtilities.addPersistentTag(this, key, type, data);
        return this;
    }

    /**
     * Sets this ItemConstructor to be unbreakable
     * @return The ItemConstructor with the unbreakable tag added
     */
    public ItemConstructor unbreakable() {
        ItemUtilities.setUnbreakable(this);
        return this;
    }

    /**
     * Sets the owning player of this ItemConstructor
     * @param player The Player that is the owner
     * @return The ItemConstructor with the owner added
     */
    public ItemConstructor setOwningPlayer(OfflinePlayer player) {
        ItemUtilities.setSkullOwningPlayer(this, player);
        return this;
    }
}
