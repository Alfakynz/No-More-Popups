package com.alfakynz.nomorepopups.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.fml.ModLoadingContext;

public class ModConfigScreen {

    public static void registerModsPage() {
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (_, screen) -> ModConfigScreen.create(screen));
    }

    public final static String defaultKey = "option.no_more_popups.config.";

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable(defaultKey + "title"))
                .setSavingRunnable(ModConfig::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // General category
        var generalCategory = builder.getOrCreateCategory(
                Component.translatable(defaultKey + "general")
        );

        for (ConfigSettings.Setting setting : ConfigSettings.GENERAL_SETTINGS) {
            generalCategory.addEntry(entryBuilder
                    .startBooleanToggle(
                            Component.translatable(defaultKey + "general." + setting.key()),
                            setting.getter().getAsBoolean()
                    )
                    .setDefaultValue(setting.defaultValue())
                    .setSaveConsumer(setting.setter())
                    .build());
        }

        // Modded category
        var moddedCategory = builder.getOrCreateCategory(
                Component.translatable(defaultKey + "modded")
        );

        for (ConfigSettings.Setting setting : ConfigSettings.MODDED_SETTINGS) {
            moddedCategory.addEntry(entryBuilder
                    .startBooleanToggle(
                            Component.translatable(defaultKey + "modded." + setting.key()),
                            setting.getter().getAsBoolean()
                    )
                    .setDefaultValue(setting.defaultValue())
                    .setSaveConsumer(setting.setter())
                    .build());
        }

        return builder.build();
    }
}