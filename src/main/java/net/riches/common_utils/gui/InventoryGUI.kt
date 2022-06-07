package net.riches.common_utils.gui

import net.riches.common_utils.CommonUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.stream.Collectors
import net.riches.common_utils.gui.ItemButton;
import net.riches.common_utils.items.ItemConstructor

class InventoryGUI(val inventory: Inventory) : Listener {
	private val openSlots: MutableSet<Int> = LinkedHashSet()
	private var onDestroy: Runnable? = null
	private var onClickOpenSlot = BiConsumer { e: InventoryClickEvent?, i: List<Int?>? -> }
	private var onDragOpenSlot = Consumer { e: InventoryDragEvent? -> }
	private val buttons: MutableMap<Int, ItemButton> = HashMap()
	private var returnItems = true
	private var destroyOnClose = true

	/**
	 * Creates a new GUI from an inventory
	 *
	 * @param inventory The inventory to create a GUI from
	 */
	init {
		CommonUtils().getInstance()?.let { Bukkit.getPluginManager().registerEvents(this, it) }
	}

	/**
	 * Creates a new GUI, instantiating a new inventory with the given size and name
	 *
	 * @param size The size of the inventory
	 * @param name The name of the inventory
	 */
	constructor(size: Int, name: String?) : this(Bukkit.createInventory(null, size, name!!)) {}

	/**
	 * Add a button to the GUI in the given slot
	 *
	 * @param button The button to be added
	 * @param slot   The slot to add the button to
	 */
	fun addButton(button: ItemButton, slot: Int) {
		button.setSlot(slot)
		inventory.setItem(slot, button.getItem())
		buttons[slot] = button
	}

	/**
	 * Add a button to the GUI in the given slot
	 *
	 * @param button The button to be added
	 * @param slot   The slot to add the button to
	 */
	fun addButton(slot: Int, button: ItemButton) {
		addButton(button, slot)
	}

	/**
	 * Add a button at the given position in the inventory
	 *
	 * @param button The button to be added
	 * @param x      The X position to add the button at
	 * @param y      The Y position to add the button at
	 */
	fun addButton(button: ItemButton, x: Int, y: Int) {
		val slot = x + y * 9
		addButton(button, slot)
	}

	/**
	 * Fill a section of the inventory with the given item
	 *
	 * @param start The starting index to fill from, inclusive
	 * @param end   The ending index to fill to, exclusive
	 * @param item  The item to set in these slots
	 */
	fun fill(start: Int, end: Int, item: ItemStack?) {
		for (i in start until end) {
			inventory.setItem(i, item?.clone())
		}
	}

	/**
	 * Fill a section of the inventory with the given item
	 *
	 * @param x1   The X position to fill from, inclusive
	 * @param y1   The Y position to fill from, inclusive
	 * @param x2   The X position to fill to, exclusive
	 * @param y2   The Y position to fill to, exclusive
	 * @param item The item to set in these slots
	 */
	fun fill(x1: Int, y1: Int, x2: Int, y2: Int, item: ItemStack?) {
		for (x in x1 until x2) {
			for (y in y1 until y2) {
				inventory.setItem(x + y * 9, item?.clone())
			}
		}
	}

	/**
	 * Remove a button from the inventory
	 *
	 * @param button The button to be removed
	 */
	fun removeButton(button: ItemButton) {
		inventory.setItem(button.getSlot(), ItemStack(Material.AIR))
		buttons.remove(button.getSlot())
	}

	/**
	 * @return All the ItemButtons in this GUI
	 */
	fun getButtons(): Any? {
		return ArrayList<Any?>(buttons.values)
	}

	/**
	 * Gets the ItemButton in a given slot
	 * @param slot The slot the button is in
	 * @return The ItemButton, or null if there is no button in that slot
	 */
	fun getButton(slot: Int): ItemButton? {
		return buttons[slot]
	}

	/**
	 * Clears a single slot, removing a button if it is present
	 * @param slot The slot to clear
	 */
	fun clearSlot(slot: Int) {
		val button: ItemButton? = buttons[slot]
		if (button != null) {
			removeButton(button)
			return
		}
		inventory.setItem(slot, ItemStack(Material.AIR))
	}

	/**
	 * Refresh the inventory.
	 */
	fun update() {
		for (button in buttons.values) {
			inventory.setItem(button.getSlot(), button.getItem())
		}
	}

	/**
	 * Opens a slot so that items can be placed in it
	 *
	 * @param slot The slot to open
	 */
	fun openSlot(slot: Int) {
		openSlots.add(slot)
	}

	/**
	 * Opens slots so that items can be placed in them
	 *
	 * @param start The start of the open slot section, inclusive
	 * @param end   The end of the open slot section, exclusive
	 */
	fun openSlots(start: Int, end: Int) {
		for (i in start until end) {
			openSlots.add(i)
		}
	}

	/**
	 * Opens slots so that items can be placed in them
	 *
	 * @param x1 The x position to open from, inclusive
	 * @param y1 The y position to open from, inclusive
	 * @param x2 The x position to open to, exclusive
	 * @param y2 The y position to open to, exclusive
	 */
	fun openSlots(x1: Int, y1: Int, x2: Int, y2: Int) {
		for (y in y1 until y2) {
			for (x in x1 until x2) {
				openSlots.add(y * 9 + x)
			}
		}
	}

	/**
	 * Closes a slot so that items can't be placed in it
	 *
	 * @param slot The slot to open
	 */
	fun closeSlot(slot: Int) {
		openSlots.remove(slot)
	}

	/**
	 * Closes slots so that items can't be placed in them
	 *
	 * @param start The start of the closed slot section, inclusive
	 * @param end   The end of the open closed section, exclusive
	 */
	fun closeSlots(start: Int, end: Int) {
		for (i in start until end) {
			openSlots.remove(i)
		}
	}

	/**
	 * Closes slots so that items can't be placed in them
	 *
	 * @param x1 The x position to close from, inclusive
	 * @param y1 The y position to close from, inclusive
	 * @param x2 The x position to close to, exclusive
	 * @param y2 The y position to close to, exclusive
	 */
	fun closeSlots(x1: Int, y1: Int, x2: Int, y2: Int) {
		for (y in y1 until y2) {
			for (x in x1 until x2) {
				openSlots.remove(y * 9 + x)
			}
		}
	}

	/**
	 * Gets the open slots
	 *
	 * @return The set of open slots
	 */
	fun getOpenSlots(): Set<Int> {
		return openSlots
	}

	/**
	 * Opens this GUI for a player
	 *
	 * @param player The player to open this GUI for
	 */
	fun open(player: Player) {
		player.openInventory(inventory)
	}

	/**
	 * Returns whether or not items in open slots are returned to the player when this inventory is destroyed
	 *
	 * @return Whether or not items in open slots are returned to the player when this inventory is destroyed
	 */
	fun returnsItems(): Boolean {
		return returnItems
	}

	/**
	 * Sets whether items in open slots are returned to the player when this inventory is destroyed
	 *
	 * @param returnItems Whether items in open slots should be returned to the player when this inventory is destroyed
	 */
	fun setReturnsItems(returnItems: Boolean) {
		this.returnItems = returnItems
	}

	/**
	 * Returns whether this GUI is destroyed when it has been closed by all viewers
	 *
	 * @return Whether this GUI is destroyed when it has been closed by all viewers
	 */
	fun destroysOnClose(): Boolean {
		return destroyOnClose
	}

	/**
	 * Sets whether this GUI is destroyed when it has been closed by all viewers
	 *
	 * @param destroyOnClose Whether this GUI is destroyed when it has been closed by all viewers
	 */
	fun setDestroyOnClose(destroyOnClose: Boolean) {
		this.destroyOnClose = destroyOnClose
	}

	/**
	 * Sets a callback to be run when this GUI is destroyed
	 *
	 * @param onDestroy The callback
	 */
	fun setOnDestroy(onDestroy: Runnable?) {
		this.onDestroy = onDestroy
	}

	/**
	 * Sets the handler for when an open slot is clicked
	 *
	 * @param handler The handler for when an open slot is clicked
	 */
	fun setOnClickOpenSlot(handler: Consumer<InventoryClickEvent?>) {
		onClickOpenSlot =
			BiConsumer { e: InventoryClickEvent?, i: List<Int?>? ->
				handler.accept(
					e
				)
			}
	}

	/**
	 * Sets the handler for when an open slot is clicked
	 *
	 * @param handler The handler for when an open slot is clicked, taking the event and list
	 * of affected slots
	 */
	fun setOnClickOpenSlot(handler: BiConsumer<InventoryClickEvent?, List<Int?>?>) {
		onClickOpenSlot = handler
	}
	/**
	 * Remove this inventory as a listener and clean everything up to prevent memory leaks.
	 * Call this when the GUI is no longer being used.
	 *
	 * @param lastViewer The last Player who was viewing this GUI, to have the items returned to them.
	 */
	/**
	 * Remove this inventory as a listener and clean everything up to prevent memory leaks.
	 * Call this when the GUI is no longer being used.
	 */
	@JvmOverloads
	fun destroy(lastViewer: Player? = null) {
		if (onDestroy != null) {
			onDestroy!!.run()
		}
		HandlerList.unregisterAll(this)
		if (returnItems && lastViewer != null) {
			for (slot in openSlots) {
				val item = inventory.getItem(slot) ?: continue
				lastViewer.inventory.addItem(item).values.forEach(Consumer { i: ItemStack? ->
					lastViewer.world.dropItem(
						lastViewer.location,
						i!!
					)
				})
			}
		}
		inventory.clear()
		buttons.clear()
	}

	/**
	 * Clears the inventory and its buttons
	 */
	fun clear() {
		inventory.clear()
		buttons.clear()
	}

	/**
	 * Sets the handler for when items are drag-clicked into open slots
	 *
	 * @param onDrag The handler
	 */
	fun setOnDragOpenSlot(onDrag: Consumer<InventoryDragEvent?>) {
		onDragOpenSlot = onDrag
	}

	@EventHandler
	fun onDrag(e: InventoryDragEvent) {
		val slots = e.rawSlots.stream().filter { s: Int ->
			getInventory(
				e.view,
				s
			) == inventory
		}.collect(Collectors.toList())
		if (slots.size == 0) {
			return
		}
		if (!openSlots.containsAll(slots)) {
			e.isCancelled = true
			return
		}
		onDragOpenSlot.accept(e)
	}

	private fun getInventory(view: InventoryView, rawSlot: Int): Inventory {
		return if (rawSlot < view.topInventory.size) view.topInventory else view.bottomInventory
	}

	@EventHandler
	fun onClick(e: InventoryClickEvent) {
		if (inventory != e.view.topInventory) {
			return
		}
		if (e.action == InventoryAction.COLLECT_TO_CURSOR && e.clickedInventory != inventory) {
			e.isCancelled = true
			return
		}
		if (inventory != e.clickedInventory && e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			if (openSlots.size > 0) {
				val slots: MutableMap<Int?, ItemStack> = HashMap()
				var amount = e.currentItem!!.amount
				for (slot in openSlots) {
					if (amount <= 0) {
						break
					}
					val item = inventory.getItem(slot)
					if (item == null) {
						val diff = Math.min(amount, e.currentItem!!.type.maxStackSize)
						amount -= diff
						val clone = e.currentItem!!.clone()
						clone.amount = diff
						slots[slot] = clone
						continue
					}
					if (e.currentItem!!.isSimilar(item)) {
						val max = item.type.maxStackSize - item.amount
						val diff = Math.min(max, e.currentItem!!.amount)
						amount -= diff
						val clone = item.clone()
						clone.amount = clone.amount + diff
						slots[slot] = clone
					}
				}
				if (slots.size == 0) {
					return
				}
				onClickOpenSlot.accept(e, ArrayList(slots.keys))
				if (e.isCancelled) {
					return
				}
				e.isCancelled = true
				val item = e.currentItem
				item!!.amount = amount
				e.currentItem = item
				slots.forEach { (index: Int?, item: ItemStack?) ->
					inventory.setItem(
						index!!,
						item
					)
				}
				CommonUtils().getInstance().let {
					if (it != null) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(it) { (e.whoClicked as Player).updateInventory() }
					}
				}
				return
			}
			e.isCancelled = true
		}
		if (e.inventory == e.clickedInventory) {
			if (openSlots.contains(e.slot)) {
				val list: MutableList<Int?> = ArrayList()
				list.add(e.slot)
				onClickOpenSlot.accept(e, list)
				return
			}
			e.isCancelled = true
			val button: ItemButton? = buttons[e.slot]
			if (button != null) {
				button.onClick(e)
			}
		}
	}

	@EventHandler
	fun onClose(e: InventoryCloseEvent) {
		if (e.inventory == inventory && destroyOnClose) {
			if (e.viewers.size <= 1) {
				destroy(e.player as Player)
			}
		}
	}

	companion object {
		/**
		 * A gray stained glass pane with no name. Good for filling empty slots in GUIs.
		 */
		var FILLER: ItemStack? = null

		init {
			FILLER = ItemConstructor(Material.GRAY_STAINED_GLASS_PANE).setName(" ")
		}
	}
}