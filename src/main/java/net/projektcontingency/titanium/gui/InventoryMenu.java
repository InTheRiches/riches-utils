package net.projektcontingency.titanium.gui;

import net.projektcontingency.titanium.Titanium;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.misc.Task;

import java.util.function.Predicate;

public class InventoryMenu extends InventoryGUI {

    private boolean preventClose;
    private Predicate<? super Player> preventClosePredicate;

    public InventoryMenu(Inventory inventory) {
        super(inventory);
    }

    public InventoryMenu(int size, String name) {
        super(size, name);

        this.setDestroyOnClose(false);
    }

    @EventHandler
    @Override
    public void onClose(InventoryCloseEvent e) {
        if (!e.getInventory().equals(this.getInventory()) || (!this.destroysOnClose() && !this.preventClose) || e.getViewers().size() > 1) return;

        if (this.preventClose && this.preventClosePredicate.test((Player) e.getPlayer())) {
            Task.syncDelayed(Titanium.getInstance(), () -> this.open((Player) e.getPlayer()));
            return;
        }

        if (this.destroysOnClose())
            destroy((Player) e.getPlayer());
    }

    public void setPreventClose(boolean preventClose) {
        this.preventClose = preventClose;
    }

    public void setPreventClosePredicate(Predicate<? super Player> preventClosePredicate) {
        this.preventClosePredicate = preventClosePredicate;
    }

}


// extends InventoryGUI {
//
//    @EventHandler
//    public void onInventoryClose(InventoryCloseEvent e) {
//        if (!e.getInventory().equals(inventory) || !destroyOnClose || e.getViewers().size() > 1) return;
//
//        if (this.preventClosePredicate.test((Player) e.getPlayer())) {
//            e.
//        }
//    }
//}
