package com.alfakynz.nomorepopups.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class ModConfigScreen {

    public static void registerModsPage() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> create(parent)));
    }

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("option.no_more_popups.settings"))
                .setSavingRunnable(ModConfig::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var generalCategory = builder.getOrCreateCategory(Component.translatable("option.no_more_popups.settings.general"));

        for (ConfigSettings.Setting setting : ConfigSettings.SETTINGS) {
            String key = setting.key();
            BooleanSupplier getter = setting.getter();
            Consumer<Boolean> setter = setting.setter();
            Boolean default_value = setting.defaultValue();

            generalCategory.addEntry(entryBuilder
                    .startBooleanToggle(Component.translatable("option.no_more_popups.settings." + key), getter.getAsBoolean())
                    .setDefaultValue(default_value)
                    .setSaveConsumer(setter)
                    .build());
        }

        return builder.build();
    }
}