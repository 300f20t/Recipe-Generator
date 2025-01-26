package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateracipesProcedure {
public static void execute(
LevelAccessor world,
HashMap guistate ) {
if (
guistate == null ) return ;
double i = 0;String preGeneratedRecipe = "";String recipeType = "";String recipeTypeFutures = "";String KubeJSRecipeShape = "";
if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {preGeneratedRecipe = +" * "+new java.text.DecimalFormat("##").format()+", ["+"\n"+"["++", "++", "++"], "+"\n"+"["++", "++", "++"], "+"\n"+"["++", "++", "++"]]);";if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {recipeType = "craftingTable.addShapedMirrored(\"";recipeTypeFutures = "MirrorAxis."+RecipeGeneratorModVariables.WorldVariables.get(world).Mirror_axis+", ";RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.MirrorAxis;"+recipeType+((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()?:(guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))+"\", "+recipeTypeFutures+preGeneratedRecipe;}else if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {recipeType = "craftingTable.addShapeless(\"";RecipeGeneratorModVariables.Generated_recipe = recipeType+""+.replace(",","")+"\", "++((" * "+new java.text.DecimalFormat("##").format()+", "+"\n"+"["+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+(.contains("<item:minecraft:air>, ")?"":+", ")+");").replace("<item:minecraft:air>, ","")).replace(">, );",">]);");}else{recipeType = "craftingTable.addShaped(\"";recipeTypeFutures = preGeneratedRecipe;RecipeGeneratorModVariables.Generated_recipe = recipeType+""+((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()?:(guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))+"\", "+recipeTypeFutures;}}else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
preGeneratedRecipe = "\n"+(+""++",")+"\n"+(+""++",")+"\n"+(+""++",")+"\n"+(+""++",")+"\n"+(+""++",")+"\n"+(+""++",")+"\n"+(+""++",")+"\n"+(+""++",")+"\n"+(+""++",")+"\n";i = 0;while(i<=8) {if (preGeneratedRecipe.contains(+"'minecraft:air',")) {preGeneratedRecipe = preGeneratedRecipe.replace(+"'minecraft:air',"," ");KubeJSRecipeShape = "'"+(!().equals("'minecraft:air'")?"A":" ")+(!().equals("'minecraft:air'")?"B":" ")+(!().equals("'minecraft:air'")?"C":" ")+"',"+"\n"+"'"+(!().equals("'minecraft:air'")?"D":" ")+(!().equals("'minecraft:air'")?"E":" ")+(!().equals("'minecraft:air'")?"F":" ")+"',"+"\n"+"'"+(!().equals("'minecraft:air'")?"G":" ")+(!().equals("'minecraft:air'")?"H":" ")+(!().equals("'minecraft:air'")?"I":" ")+"'";}i = i+1;}RecipeGeneratorModVariables.Generated_recipe = "ServerEvents.recipes(event => {"+"event.shaped("+"\n"+" Item.of("++", "+new java.text.DecimalFormat("##").format()+"),"+"\n"+" ["+"\n"+KubeJSRecipeShape+"\n"+" ],"+"\n"+" {"+preGeneratedRecipe+"})})";}if (!world.isClientSide() && world.getServer() != null)
world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
}
}
