package miro.bassscript.functionutils;

import miro.bassscript.BSLogger;
import miro.bassscript.BassScript;
import miro.bassscript.ITimeable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;


/**
 * All Functions extend this class.
 * Provides access to the Function's {@link BassScript} instance.
 * <p>
 * Make sure to call {@link Function#finish}.
 */
public abstract class Function implements ITimeable {
    /**
     * The current instance of BassScript.
     */
    protected BassScript bassScript;
    /**
     * The logger.
     */
    protected BSLogger logger;
    /**
     * Minecraft.
     */
    protected MinecraftClient minecraft;
    /**
     * The player.
     */
    protected ClientPlayerEntity player;
    /**
     * The world.
     */
    protected ClientWorld world;
    /**
     * The function stack to use when running other functions.
     */
    protected FunctionStack functionStack;
    protected Runnable finishCallback;

    private boolean isFirstTick = true;

    public Function(BassScript bassScript, FunctionStack functionStack) {
        this.bassScript = bassScript;
        this.logger = bassScript.getLogger();
        this.minecraft = bassScript.getMinecraft();
        this.player = bassScript.getPlayer();
        this.world = bassScript.getWorld();
        this.functionStack = functionStack;
    }

    /**
     * Called when the Function is created and about to be added the stack.
     * Use this to initialize values and calculate estimated time.
     * Every other method is guaranteed to be called after this one.
     */
    public void init(Runnable finishCallback) {
        this.finishCallback = finishCallback;
    }

    /**
     * Call this every tick, but not when paused.
     */
    public void tick() {
        if (isFirstTick) {
            start();
            isFirstTick = false;
        } else {
            onTick();
        }
    }

    /**
     * Function to call when you have finished.
     */
    protected void finish() {
        stop();
        finishCallback.run();
    }

    @Override
    public String toString() {
        return "[" + name() + "]" + " " + desc();
    }

    // TODO: pass interrupt to pause methods

    /**
     * Tries to interrupt the function.
     * Will keep running if the interrupt was unsuccessful (return false).
     * Override for things like making sure e.g. autoeat doesn't interrupt while shifting over lava or whatever.
     *
     * @return whether the interrupt was successful
     */
    public boolean tryPause() {
        pause();
        return true;
    }

    /**
     * Called when the function MUST be paused.
     * Override to change behavior, only when overriding {@link #tryPause} as well.
     * Probably never run this lol.
     */
    public void forcePause() {
        pause();
    }

    /**
     * The estimated amount of ticks until completion of function.
     * Override this if your function will be used with runFastest or for getting items (in items.json).
     *
     * @return estimated ticks until completion, returns 3 minutes by default.
     */
    public Double getEstimatedTicks() {
        return 20D * 180;
    }

    // ABSTRACT METHODS

    /**
     * Called every tick.
     */
    protected abstract void onTick();

    /**
     * Called the first tick this function is added to the stack.
     */
    public abstract void start();

    /**
     * Called once before the function is removed.
     * Use this to clean up.
     */
    public abstract void stop();

    /**
     * Called when the function is interrupted or paused.
     */
    protected abstract void pause();

    /**
     * Called when the function is resumed from being paused.
     */
    public abstract void resume();

    /**
     * The name of the function. Should be short (a few words).
     *
     * @return The name of the function.
     */
    public abstract String name();

    /**
     * A short description of the function. Should explain the parameters.
     *
     * @return A short description of the function
     */
    public abstract String desc();
}
