package com.recipe_generator.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;

@Mixin(CraftingScreen.class)
@SuppressWarnings("unchecked")
public class CraftingScreenMixin {
    
    @Inject(method = "init", at = @At("RETURN"))
    private void addButtons(CallbackInfo ci) {
        try {
            CraftingScreen screen = (CraftingScreen)(Object)this;
            
            int centerX = screen.width / 2;
            int centerY = screen.height / 2;
            
            Button[] buttons = {
                createButton("Generate", centerX + 95, centerY - 70, () -> action1()),
                createButton("Save", centerX  + 95, centerY - 45, () -> action2()),
                createButton("Reload", centerX + 95, centerY - 20, () -> action3()),
                createButton("Close", centerX + 95, centerY + 55, () -> screen.onClose())
            };
            
            Field renderablesField = Screen.class.getDeclaredField("renderables");
            renderablesField.setAccessible(true);
            List<Renderable> renderables = (List<Renderable>) renderablesField.get(screen);
            
            Field childrenField = Screen.class.getDeclaredField("children");
            childrenField.setAccessible(true);
            List<GuiEventListener> children = (List<GuiEventListener>) childrenField.get(screen);
            
            for (Button button : buttons) {
                renderables.add(button);
                children.add(button);
            }
            
        } catch (Exception ignored) {}
    }
    
    private Button createButton(String text, int x, int y, Runnable action) {
        return Button.builder(
            Component.literal(text),
            btn -> action.run()
        )
        .bounds(x, y, 60, 20)
        .build();
    }
    
    private void action1() {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§aAction 1"));
        }
    }
    
    private void action2() {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§aAction 2"));
        }
    }

    private void action3() {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§aAction 3"));
        }
    }
}