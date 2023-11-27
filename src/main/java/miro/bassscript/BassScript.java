package miro.bassscript;

import miro.bassscript.baritone.BaritoneHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public class BassScript implements ClientModInitializer {

	private BSLogger logger;
	private FunctionStack functionStack;
	private BaritoneHandler baritoneHandler;


	@Override
	public void onInitializeClient() {
		// initialize
		ClientLifecycleEvents.CLIENT_STARTED.register((client) -> onInitializeReady());
	}

	public void onInitializeReady() {
		logger = new BSLogger();
		baritoneHandler = new BaritoneHandler();
		functionStack = new FunctionStack(false);

		baritoneHandler.initSettings();

		getLogger().logDebug("BassScript ready");
	}

	public BSLogger getLogger() {
		return logger;
	}

	public BaritoneHandler getBaritoneHandler() {
		return baritoneHandler;
	}

	public FunctionStack getFunctionStack() {
		return functionStack;
	}
}