package com.alfakynz.nomorepopups.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("option.no_more_popups.settings"))
                .setSavingRunnable(ModConfig::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.getOrCreateCategory(Text.translatable("option.no_more_popups.settings.general"))
                .addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.no_more_popups.settings.disable_recipe_toasts"), ModConfig.INSTANCE.disableRecipeToasts)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.disableRecipeToasts = newValue)
                        .build());
        
        builder.getOrCreateCategory(Text.translatable("option.no_more_popups.settings.general"))
                .addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.no_more_popups.settings.disable_tutorial_toasts"), ModConfig.INSTANCE.disableTutorialToasts)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.disableTutorialToasts = newValue)
                        .build());

        return builder.build();
    }
}