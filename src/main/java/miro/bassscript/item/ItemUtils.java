package miro.bassscript.item;

import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class ItemUtils {
    public static int countItems(Inventory inv, Item item) {
        int count = 0;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (!stack.isEmpty() && stack.getItem().equals(item)) {
                count += stack.getCount();
            }
        }

        return count;
    }

    public static Optional<Set<Block>> blocksFromItemsAll(Set<Item> items) {
        Set<Block> blocks = new LinkedHashSet<>(items.size());
        for (Item item : items) {
            if (item instanceof BlockItem blockItem) {
                blocks.add(blockItem.getBlock());
            } else {
                return Optional.empty();
            }
        }

        return Optional.of(blocks);
    }

    public static Set<Block> blocksFromItems(Set<Item> items) {
        Set<Block> blocks = new LinkedHashSet<>(items.size());
        for (Item item : items) {
            if (item instanceof BlockItem blockItem) {
                blocks.add(blockItem.getBlock());
            }
        }

        return blocks;
    }
}
