package miro.bassscript.functionutils;

import miro.bassscript.BassScript;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class FunctionStack {
    private BassScript bassScript;
    private Deque<Function> stack = new ArrayDeque<>();
    private Queue<Function> functionQueue = new ArrayDeque<>();
    private boolean isPaused;

    public FunctionStack(BassScript bassScript, boolean isPaused) {
        this.isPaused = isPaused;
        this.bassScript = bassScript;
    }

    public FunctionStack(BassScript bassScript) {
        this(bassScript, true);
    }

    public void addFunction(Function function) {
        function.init(this::removeFunction);
        functionQueue.add(function);
    }

    private void removeFunction() {
        stack.pop();
        if (!isPaused && stack.peek() != null) {
            stack.peek().resume();
        }
    }

    private void pushToStack(Function function) {
        stack.push(function);
        bassScript.getLogger().logInfo("Added function " + function.name());
    }

    public void tick() {
        if (!functionQueue.isEmpty()) {
            if (stack.peek() != null) {
                if (stack.peek().tryPause()) {
                    pushToStack(functionQueue.remove());
                }
            } else {
                pushToStack(functionQueue.remove());
            }
        }

        if (!isPaused) {
            if (stack.peek() != null) {
                stack.peek().tick();
            }
        }
    }

    public boolean tryPause() {
        if (stack.peek() != null) {
            isPaused = stack.peek().tryPause();
        } else {
            isPaused = true;
        }
        return isPaused;
    }

    public void forcePause() {
        isPaused = true;
        if (stack.peek() != null) {
            stack.peek().forcePause();
        }
    }

    public void resume() {
        isPaused = false;
        if (stack.peek() != null) {
            stack.peek().resume();
        }
    }
}
