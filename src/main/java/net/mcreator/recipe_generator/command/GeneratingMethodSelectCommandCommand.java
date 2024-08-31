
package net.mcreator.recipe_generator.command;

import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.commands.Commands;

@EventBusSubscriber
public class GeneratingMethodSelectCommandCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("generatingmethodselectcommand")

		);
	}
}
