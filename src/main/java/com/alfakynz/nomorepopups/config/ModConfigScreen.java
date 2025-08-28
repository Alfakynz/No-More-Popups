package com.alfakynz.nomorepopups.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class ModConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("option.no_more_popups.settings"))
                .setSavingRunnable(ModConfig::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var generalCategory = builder.getOrCreateCategory(Text.translatable("option.no_more_popups.settings.general"));

        record Setting(String key, BooleanSupplier getter, Consumer<Boolean> setter) {}

        Setting[] settings = new Setting[] {
            new Setting("disable_recipe_toasts", () -> ModConfig.INSTANCE.disableRecipeToasts, newValue -> ModConfig.INSTANCE.disableRecipeToasts = newValue),
            new Setting("disable_tutorial_toasts", () -> ModConfig.INSTANCE.disableTutorialToasts, newValue -> ModConfig.INSTANCE.disableTutorialToasts = newValue),
            new Setting("disable_advancement_toasts", () -> ModConfig.INSTANCE.disableAdvancementToasts, newValue -> ModConfig.INSTANCE.disableAdvancementToasts = newValue),
            new Setting("disable_resource_pack_warnings", () -> ModConfig.INSTANCE.disableResourcePackWarnings, newValue -> ModConfig.INSTANCE.disableResourcePackWarnings = newValue),
            new Setting("disable_multiplayer_warning", () -> ModConfig.INSTANCE.disableMultiplayerWarning, newValue -> {
                ModConfig.INSTANCE.disableMultiplayerWarning = newValue;
                if (newValue) {
                    MinecraftClient.getInstance().options.skipMultiplayerWarning = true;
                } else {
                    MinecraftClient.getInstance().options.skipMultiplayerWarning = false;
                }
                MinecraftClient.getInstance().options.write();
            })
        };

        for (Setting setting : settings) {
            String key = setting.key();
            BooleanSupplier getter = setting.getter();
            Consumer<Boolean> setter = setting.setter();

            generalCategory.addEntry(entryBuilder
                .startBooleanToggle(Text.translatable("option.no_more_popups.settings." + key), getter.getAsBoolean())
                .setDefaultValue(true)
                .setSaveConsumer(setter)
                .build());
        }

        return builder.build();
    }
}