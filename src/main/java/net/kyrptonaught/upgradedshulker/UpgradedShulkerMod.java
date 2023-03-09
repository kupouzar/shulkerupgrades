package net.kyrptonaught.upgradedshulker;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.kyrptonaught.upgradedshulker.recipe.AddUpgradeRecipe;
import net.kyrptonaught.upgradedshulker.recipe.CopyUpgradesRecipe;
import net.kyrptonaught.upgradedshulker.recipe.DyeShulkerRecipe;
import net.kyrptonaught.upgradedshulker.recipe.KeepColorSmithing;
import net.kyrptonaught.upgradedshulker.screen.UpgradedShulkerScreenHandler;
import net.kyrptonaught.upgradedshulker.util.ShulkerUpgrades;
import net.kyrptonaught.upgradedshulker.util.ShulkersRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class UpgradedShulkerMod implements ModInitializer {
    public static final String MOD_ID = "upgradedshulkers";
    public static final ItemGroup GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "shulkers"))
            .icon(() -> new ItemStack(ShulkersRegistry.upgradedShulkerBlocks.get(ShulkerUpgrades.MATERIAL.NETHERITE)))
            .build();
    public static final ScreenHandlerType<UpgradedShulkerScreenHandler> US_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerExtended(new Identifier(MOD_ID, "upgradedshulker"), UpgradedShulkerScreenHandler::new);

    public static RecipeSerializer<CopyUpgradesRecipe> copyDyeRecipe;
    public static RecipeSerializer<KeepColorSmithing> colorSmithingRecipe;
    public static RecipeSerializer<AddUpgradeRecipe> addUpgradeRecipe;
    public static SpecialRecipeSerializer<DyeShulkerRecipe> dyeShulkerRecipe;

    @Override
    public void onInitialize() {
        ShulkersRegistry.init();

        copyDyeRecipe = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "copy_upgrade_recipe"), new CopyUpgradesRecipe.Serializer());
        colorSmithingRecipe = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "keep_color_recipe"), new KeepColorSmithing.Serializer());
        addUpgradeRecipe = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "add_upgrade_recipe"), new AddUpgradeRecipe.Serializer());
        dyeShulkerRecipe = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "dye_shulker_recipe"), new SpecialRecipeSerializer<>(DyeShulkerRecipe::new));
    }
}