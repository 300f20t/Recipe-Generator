package net.mcreator.recipe_generator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

public class CampFireAddRecipeCraftTweakerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String preGeneratedRecipe = "";
		preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_0 + ", " + (new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "XP", "") : "") > 0 ? new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity1 && _entity1.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "XP", "") : "") : 1) + ", " + (Math.round(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity2 && _entity2.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu2) ? _menu2.getMenuState(0, "time", "") : "")) > 0 ? Math.round(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity3 && _entity3.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu3) ? _menu3.getMenuState(0, "time", "") : "")) : Math.round(10)) + ");";
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.CampFireRecipeManager;" + "campfire.addRecipe(\""
				+ (((entity instanceof Player _entity4 && _entity4.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu4) ? _menu4.getMenuState(0, "recipe_name", "") : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(entity)
						: ((entity instanceof Player _entity5 && _entity5.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu5) ? _menu5.getMenuState(0, "recipe_name", "") : ""))
				+ "\", " + preGeneratedRecipe;
	}
}