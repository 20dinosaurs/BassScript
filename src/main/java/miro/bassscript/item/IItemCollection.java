package miro.bassscript.item;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;

import java.util.Set;

public interface IItemCollection {
    Set<Item> getItems();

    boolean isMetBy(Inventory inv);

    IItemCollection getItemsUnmetBy(Inventory inv);
}
