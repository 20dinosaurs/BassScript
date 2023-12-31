package miro.bassscript.functions;

import miro.bassscript.BassScript;
import miro.bassscript.functionutils.Function;
import miro.bassscript.functionutils.FunctionStack;
import miro.bassscript.item.IItemCollection;
import miro.bassscript.item.ItemUtils;
import net.minecraft.block.Block;

import java.util.Set;

public class MineBlocksFunction extends Function {
    private final IItemCollection items;
    private final boolean blocksGiven;
    private Set<Block> blocks;
    private IItemCollection prevUnmetItems;

    public MineBlocksFunction(BassScript bassScript, FunctionStack functionStack, IItemCollection items, Set<Block> blocks) {
        super(bassScript, functionStack);
        this.items = items;
        this.blocks = blocks;
        blocksGiven = true;
    }

    public MineBlocksFunction(BassScript bassScript, FunctionStack functionStack, IItemCollection items) {
        super(bassScript, functionStack);
        this.items = items;
        this.blocks = ItemUtils.blocksFromItems(items.getItems());
        blocksGiven = false;
    }

    @Override
    protected void onTick() {
        if (!blocksGiven) {
            IItemCollection unmetItems = items.getItemsUnmetBy(player.getInventory());

            if (!unmetItems.equals(prevUnmetItems)) {
                prevUnmetItems = unmetItems;
                blocks = ItemUtils.blocksFromItems(unmetItems.getItems());
                tryPause();
                resume();
            }
        }
    }

    @Override
    public void start() {
        bassScript.getBaritone().getPathingBehavior().cancelEverything();
        resume();
    }

    @Override
    public void stop() {
        tryPause();
    }

    @Override
    public boolean tryPause() {
        return bassScript.getBaritone().getPathingBehavior().cancelEverything();
    }

    @Override
    public void forcePause() {
        bassScript.getBaritone().getPathingBehavior().forceCancel();
    }

    @Override
    protected void pause() {}

    @Override
    public void resume() {
        bassScript.getBaritone().getMineProcess().mine(blocks.toArray(new Block[0]));
    }

    @Override
    public boolean isFinished() {
        return items.isMetBy(player.getInventory());
    }

    @Override
    public Double getEstimatedTicks() {
        return super.getEstimatedTicks();
    }

    @Override
    public String name() {
        return "MineBlocks";
    }

    @Override
    public String desc() {
        return "Mining: " + blocks.toString();
    }
}
