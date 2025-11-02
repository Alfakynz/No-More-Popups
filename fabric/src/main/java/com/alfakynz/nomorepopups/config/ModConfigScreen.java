package com.alfakynz.nomorepopups.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class ModConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("option.no_more_popups.settings"))
                .setSavingRunnable(ModConfig::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var generalCategory = builder.getOrCreateCategory(Component.translatable("option.no_more_popups.settings.general"));

        record Setting(String key, BooleanSupplier getter, Consumer<Boolean> setter, Boolean default_value) {}

        Setting[] settings = new Setting[] {
                new Setting("disable_advancements_messages", () -> ModConfig.INSTANCE.disableAdvancementsMessages, newValue -> ModConfig.INSTANCE.disableAdvancementsMessages = newValue, false),
                new Setting("disable_advancement_toasts", () -> ModConfig.INSTANCE.disableAdvancementToasts, newValue -> ModConfig.INSTANCE.disableAdvancementToasts = newValue, true),
                new Setting("disable_experimental_warning", () -> ModConfig.INSTANCE.disableExperimentalWarning, newValue -> ModConfig.INSTANCE.disableExperimentalWarning = newValue, true),
                new Setting("disable_multiplayer_warning", () -> ModConfig.INSTANCE.disableMultiplayerWarning, newValue -> {
                    ModConfig.INSTANCE.disableMultiplayerWarning = newValue;
                    Minecraft.getInstance().options.skipMultiplayerWarning = newValue;
                    Minecraft.getInstance().options.save();
                }, true),
                new Setting("disable_recipe_toasts", () -> ModConfig.INSTANCE.disableRecipeToasts, newValue -> ModConfig.INSTANCE.disableRecipeToasts = newValue, true),
                new Setting("disable_resource_pack_warnings", () -> ModConfig.INSTANCE.disableResourcePackWarnings, newValue -> ModConfig.INSTANCE.disableResourcePackWarnings = newValue, true),
                new Setting("disable_tutorial_toasts", () -> ModConfig.INSTANCE.disableTutorialToasts, newValue -> ModConfig.INSTANCE.disableTutorialToasts = newValue, true)
        };

        for (Setting setting : settings) {
            String key = setting.key();
            BooleanSupplier getter = setting.getter();
            Consumer<Boolean> setter = setting.setter();
            Boolean default_value = setting.default_value();

            generalCategory.addEntry(entryBuilder
                    .startBooleanToggle(Component.translatable("option.no_more_popups.settings." + key), getter.getAsBoolean())
                    .setDefaultValue(default_value)
                    .setSaveConsumer(setter)
                    .build());
        }

        return builder.build();
    }
}