package com.recipe_generator.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
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

import com.recipe_generator.client.RecipeGeneratorClient;

@Mixin(CraftingScreen.class)
@SuppressWarnings("unchecked")
public class CraftingScreenMixin {
    
    private EditBox recipeNameField;
	private EditBox fileNameField;
    
    @Inject(at = @At("RETURN"), method = "init")
    private void addUI(CallbackInfo ci) {
        try {
            CraftingScreen screen = (CraftingScreen)(Object)this;
        
            int centerX = screen.width / 2;
            int centerY = screen.height / 2;
        
            recipeNameField = new EditBox(
                Minecraft.getInstance().font,
                centerX - 200,
                centerY - 70,
                100,
                20,
                Component.literal("")
            );
            recipeNameField.setMaxLength(100);
            recipeNameField.setHint(Component.literal("Recipe name"));
			fileNameField = new EditBox(
                Minecraft.getInstance().font,
                centerX - 200,
                centerY - 45,
                100,
                20,
                Component.literal("")
            );
            fileNameField.setMaxLength(100);
            fileNameField.setHint(Component.literal("File name"));
            
            Button[] buttons = {
                createButton("Generate", centerX + 95, centerY - 70, () -> action1()),
                createButton("Save", centerX + 95, centerY - 45, () -> action2()),
                createButton("Reload", centerX + 95, centerY - 20, () -> action3()),
        
                createButton("Close", centerX + 95, centerY + 55, () -> screen.onClose())
            };

            if (!RecipeGeneratorClient.isUIHidden) {
                Field renderablesField = Screen.class.getDeclaredField("renderables");
                renderablesField.setAccessible(true);
                List<Renderable> renderables = (List<Renderable>) renderablesField.get(screen);
                
                Field childrenField = Screen.class.getDeclaredField("children");
                childrenField.setAccessible(true);
                List<GuiEventListener> children = (List<GuiEventListener>) childrenField.get(screen);
                renderables.add(recipeNameField);
                children.add(recipeNameField);
			    renderables.add(fileNameField);
                children.add(fileNameField);
                
                for (Button button : buttons) {
                    renderables.add(button);
                    children.add(button);
                }
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
        String name = recipeNameField != null ? recipeNameField.getValue() : "";
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§aGenerate: " + name));
        }
    }
    
    private void action2() {
        String name = recipeNameField != null ? recipeNameField.getValue() : "";
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§aSave: " + name));
        }
    }

    private void action3() {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§aReload"));
        }
    }
}
