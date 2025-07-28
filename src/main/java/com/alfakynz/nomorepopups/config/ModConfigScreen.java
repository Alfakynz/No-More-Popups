package com.alfakynz.nomorepopups.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("No More Popups Settings"))
                .setSavingRunnable(ModConfig::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.getOrCreateCategory(Text.literal("General"))
                .addEntry(entryBuilder.startBooleanToggle(Text.literal("Disable Recipe Toasts"), ModConfig.INSTANCE.disableRecipeToasts)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> ModConfig.INSTANCE.disableRecipeToasts = newValue)
                        .build());

        return builder.build();
    }
}