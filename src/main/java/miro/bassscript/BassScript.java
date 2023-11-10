package miro.bassscript;

import miro.bassscript.baritone.BaritoneHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public class BassScript implements ClientModInitializer {

	private BSLogger logger;
	private BaritoneHandler baritoneHandler;


	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register((client) -> onInitializeReady());
	}

	public void onInitializeReady() {
		logger = new BSLogger();
		baritoneHandler = new BaritoneHandler();

		baritoneHandler.initSettings();

		getLogger().logDebug("BassScript ready");
	}

	public BSLogger getLogger() {
		return logger;
	}

	public BaritoneHandler getBaritoneHandler() {
		return baritoneHandler;
	}
}