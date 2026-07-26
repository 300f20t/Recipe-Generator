package com.recipe_generator.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.List;

import com.recipe_generator.client.RecipeGeneratorClient;
import com.recipe_generator.client.RecipeGeneratorClient.GenerationMethod;
import com.recipe_generator.client.generator.crafting_table.Generator;
import com.recipe_generator.client.util.FileSaver;

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
                createButton("Generate", centerX + 95, centerY - 70, () -> generate()),
                createButton("Save", centerX + 95, centerY - 45, () -> save()),
                createButton("Reload", centerX + 95, centerY - 20, () -> reload()),
        
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
    
    private void generate() {
        if (Minecraft.getInstance().player == null) return;

        String name = recipeNameField != null ? recipeNameField.getValue().trim() : "";
        
        if (name.isEmpty()) {
            name = generateName();
            if (recipeNameField != null) {
                recipeNameField.setValue(name);
            }
        }

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();

        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aGenerated recipe: \n§f" + new Generator().generate(menu.slots, name))
        );
    }
    
    private void save() {
        if (Minecraft.getInstance().player == null) return;

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        String name = fileNameField != null ? fileNameField.getValue().trim() : "";

        if (name.isEmpty()) {
            name = generateName();
            if (fileNameField != null) {
                fileNameField.setValue(name);
            }
        }
        
        String script = new Generator().generate(menu.slots, name);

        GenerationMethod method = RecipeGeneratorClient.genMethod;
        String fileName = name + method.getExtension();
        String folder = method.getFolder();

        FileSaver.save(script, fileName, folder);
        
        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aSaved as §f" + fileName + "§a to §f" + folder)
        );
    }

    private void reload() {
        if (Minecraft.getInstance().player == null) return;

        Minecraft.getInstance().player.connection.sendCommand("reload");

        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aReload command sent")
        );
    }

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (recipeNameField != null && recipeNameField.isFocused()) {
            if (recipeNameField.keyPressed(keyCode, scanCode, modifiers)) {
                cir.setReturnValue(true);
                return;
            }
            cir.setReturnValue(false);
            return;
        }
        if (fileNameField != null && fileNameField.isFocused()) {
            if (fileNameField.keyPressed(keyCode, scanCode, modifiers)) {
                cir.setReturnValue(true);
                return;
            }
            cir.setReturnValue(false);
            return;
        }
    }
    
    @Inject(at = @At("HEAD"), method = "charTyped", cancellable = true)
    private void onCharTyped(char codePoint, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (recipeNameField != null && recipeNameField.isFocused()) {
            if (recipeNameField.charTyped(codePoint, modifiers)) {
                cir.setReturnValue(true);
                return;
            }
            cir.setReturnValue(false);
            return;
        }
        if (fileNameField != null && fileNameField.isFocused()) {
            if (fileNameField.charTyped(codePoint, modifiers)) {
                cir.setReturnValue(true);
                return;
            }
            cir.setReturnValue(false);
            return;
        }
    }

    private String generateName() {
        String baseName = "recipe_" + System.currentTimeMillis();   

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        ItemStack result = menu.slots.get(0).getItem();
        if (!result.isEmpty()) {
            String itemName = result.getItem().getDescriptionId().replace("block.minecraft.", "").replace("item.minecraft.", "");
            baseName = itemName + "_" + System.currentTimeMillis();
        }
    
        return baseName;
    }
}
