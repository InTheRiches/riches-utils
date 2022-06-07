package net.projektcontingency.titanium.internal;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.projektcontingency.titanium.items.ItemConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.function.Consumer;

public abstract class BookInput {
    private ItemStack book;

    public BookInput(TextComponent text) {
        ItemStack book = new ItemConstructor(Material.WRITABLE_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.addPage(ComponentSerializer.toString(text));
        book.setItemMeta(meta);
        this.book = book;
    }

    public ItemStack getBook() {
        return this.book;
    }

    public void setText(TextComponent text) {
        BookMeta meta = (BookMeta) this.book.getItemMeta();
        meta.setPage(0, ComponentSerializer.toString(text));
        this.book.setItemMeta(meta);
    }

    public static BookInput create(TextComponent text, Consumer<PlayerEditBookEvent> listener) {
        return new BookInput(text) {
            @Override
            public void onClick(PlayerEditBookEvent e) {
                listener.accept(e);
            }
        };
    }
    public abstract void onClick(PlayerEditBookEvent e);

    public void open(Player player) {
        player.openBook(this.book);
    }
}
