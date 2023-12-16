package miro.bassscript;

import baritone.api.IBaritone;
import miro.bassscript.baritone.BaritoneHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;

public class BassScript implements ClientModInitializer {

	private BSLogger logger;
	private FunctionStack functionStack;
	private BaritoneHandler baritoneHandler;


	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register((client) -> onInitializeReady());

		ClientTickEvents.START_CLIENT_TICK.register((client) -> onStartTick());
	}

	private void onInitializeReady() {
		logger = new BSLogger();
		baritoneHandler = new BaritoneHandler(this);
		functionStack = new FunctionStack(this, false);

		baritoneHandler.initSettings();

		getLogger().logDebug("BassScript ready");
	}

	private void onStartTick() {
		functionStack.tick();
	}

	public MinecraftClient getMinecraft() {
		return MinecraftClient.getInstance();
	}

	public ClientPlayerEntity getPlayer() {
		return MinecraftClient.getInstance().player;
	}

	public ClientWorld getWorld() {
		return MinecraftClient.getInstance().world;
	}

	public BSLogger getLogger() {
		return logger;
	}

	public BaritoneHandler getBaritoneHandler() {
		return baritoneHandler;
	}

	public IBaritone getBaritone() {
		return baritoneHandler.getBaritone();
	}

	public FunctionStack getFunctionStack() {
		return functionStack;
	}
}