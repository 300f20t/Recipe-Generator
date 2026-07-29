package com.recipe_generator.client;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.recipe_generator.CommonClass;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

public class RecipeGeneratorCommand {
    public static void addCommand(RegisterClientCommandsEvent event) {
        var command = LiteralArgumentBuilder.<CommandSourceStack>literal("rgui")
            .executes(ctx -> {
                if (CommonClass.isUIHidden) {
                    ctx.getSource().sendSystemMessage(Component.literal("Recipe Generator GUI is now shown"));
                    CommonClass.isUIHidden = false;
                } else {
                    ctx.getSource().sendSystemMessage(Component.literal("Recipe Generator GUI is now hidden"));
                    CommonClass.isUIHidden = true;
                }
                return 1;
            });
        event.getDispatcher().register(command);
    }
}
