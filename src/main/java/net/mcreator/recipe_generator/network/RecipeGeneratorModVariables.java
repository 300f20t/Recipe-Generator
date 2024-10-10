package net.mcreator.recipe_generator.network;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.mcreator.recipe_generator.RecipeGeneratorMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RecipeGeneratorModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, RecipeGeneratorMod.MODID);
	public static String item_in_slot_0_crafting_table = "";
	public static String item_in_slot_1_crafting_table = "\"\"";
	public static String item_in_slot_2_crafting_table = "\"\"";
	public static String item_in_slot_3_crafting_table = "\"\"";
	public static String item_in_slot_4_crafting_table = "\"\"";
	public static String item_in_slot_5_crafting_table = "\"\"";
	public static String item_in_slot_6_crafting_table = "\"\"";
	public static String item_in_slot_7_crafting_table = "\"\"";
	public static String item_in_slot_8_crafting_table = "\"\"";
	public static String item_in_slot_9_crafting_table = "\"\"";
	public static String Generated_recipe = "\"\"";
	public static String Recipe_name = "\"\"";
	public static String Pre_generated_recipe = "\"\"";
	public static boolean Is_mirrored = false;
	public static boolean Is_shapeless = false;
	public static String Mirror_axis = "\"NONE\"";
	public static boolean methodSelected = false;
	public static double selectedMethod = 0;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}
}
