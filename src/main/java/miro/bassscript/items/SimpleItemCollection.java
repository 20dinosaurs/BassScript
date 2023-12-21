package miro.bassscript.items;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SimpleItemCollection implements IItemCollection {
    private Map<Item, Integer> items = new LinkedHashMap<>();

    public SimpleItemCollection() {}

    public SimpleItemCollection(Item item, int count) {
        items.put(item, count);
    }

    public SimpleItemCollection(Item item) {
        items.put(item, 1);
    }

    @Override
    public boolean isMetBy(Inventory inv) {
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            // If item count in inventory is not >= requested count
            if (!(ItemUtils.countItems(inv, itemEntry.getKey()) >= itemEntry.getValue())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public IItemCollection getItemsUnmetBy(Inventory inv) {
        SimpleItemCollection unmetItems = new SimpleItemCollection();
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            int count = ItemUtils.countItems(inv, itemEntry.getKey());
            if (!(count >= itemEntry.getValue())) {
                unmetItems.addItem(itemEntry.getKey(), itemEntry.getValue() - count);
            }
        }

        return unmetItems;
    }

    public void addItem(Item item, int count) {
        items.put(item, count);
    }

    public void addItem(Item item) {
        items.put(item, 1);
    }

    @Override
    public Set<Item> getItems() {
        return items.keySet();
    }

    public Map<Item, Integer> getItemMap() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SimpleItemCollection) {
            return items.equals(((SimpleItemCollection) o).getItemMap());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
