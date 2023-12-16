package miro.bassscript.functions;

import baritone.api.pathing.goals.GoalBlock;
import miro.bassscript.BassScript;
import miro.bassscript.functionutils.FunctionStack;
import net.minecraft.util.math.BlockPos;

public class GoToXYZFunction extends GoToGoalFunction {
    BlockPos pos;

    public GoToXYZFunction(BassScript bassScript, FunctionStack functionStack, BlockPos pos) {
        super(bassScript, functionStack, new GoalBlock(pos));
        this.pos = pos;
    }

    public GoToXYZFunction(BassScript bassScript, FunctionStack functionStack, int x, int y, int z) {
        this(bassScript, functionStack, new BlockPos(x, y, z));
    }

    @Override
    public String name() {
        return "GoToXYZ";
    }

    @Override
    public String desc() {
        return String.format("Going to %s %s %s", pos.getX(), pos.getY(), pos.getZ());
    }
}
