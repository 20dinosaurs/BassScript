package miro.bassscript.functions;

import baritone.api.pathing.goals.Goal;
import miro.bassscript.BassScript;
import miro.bassscript.functionutils.Function;
import miro.bassscript.functionutils.FunctionStack;

public class GoToGoalFunction extends Function {
    protected final Goal goal;

    public GoToGoalFunction(BassScript bassScript, FunctionStack functionStack, Goal goal) {
        super(bassScript, functionStack);
        this.goal = goal;
    }

    @Override
    protected void onTick() {
        if (goal.isInGoal(bassScript.getPlayer().getBlockPos())) {
            finish();
        }
    }

    @Override
    public void start() {
        bassScript.getBaritone().getPathingBehavior().cancelEverything();
        resume();
    }

    @Override
    public void stop() {
        pause();
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
        bassScript.getBaritone().getCustomGoalProcess().setGoalAndPath(goal);
    }

    @Override
    public Double getEstimatedTicks() {
        return goal.heuristic(player.getBlockPos());
    }

    @Override
    public String name() {
        return "GoToGoal";
    }

    @Override
    public String desc() {
        return "Going to Goal: " + goal.toString();
    }
}
