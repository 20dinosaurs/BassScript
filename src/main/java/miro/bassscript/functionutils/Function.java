package miro.bassscript.functionutils;

import miro.bassscript.BassScript;
import miro.bassscript.ITimeable;


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

    private Runnable finishCallback;
    private boolean isFirstTick = true;

    // TODO: pass functionstack

    public Function(BassScript bassScript) {
        this.bassScript = bassScript;
    }

    public void init(Runnable finishCallback) {
        this.finishCallback = finishCallback;
        onInit();
    }

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
    public void finish() {
        stop();
        finishCallback.run();
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
     * Called when the Function is created and about to be added the stack.
     * Use this to initialize values and calculate estimated time.
     * Every other method is guaranteed to be called after this one.
     */
    protected abstract void onInit();

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
}
