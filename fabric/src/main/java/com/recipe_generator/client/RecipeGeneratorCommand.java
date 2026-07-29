package com.recipe_generator.client;

import com.recipe_generator.CommonClass;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import net.minecraft.network.chat.Component;

public class RecipeGeneratorCommand {
	public static void addCommand() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("rgui").executes(context -> {
				if (CommonClass.isUIHidden) {
					context.getSource().sendFeedback(Component.literal("The Recipe Generator GUI is shown"));
					CommonClass.isUIHidden = false;
				}
				else {
					context.getSource().sendFeedback(Component.literal("The Recipe Generator GUI is hidden"));
					CommonClass.isUIHidden = true;
				}
				return 1;
			}));
		});
	}
}
