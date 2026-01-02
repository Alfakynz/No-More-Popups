package com.alfakynz.nomorepopups.config;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public final class ConfigSettings {

    public record Setting(
            String key,
            BooleanSupplier getter,
            Consumer<Boolean> setter,
            boolean defaultValue
    ) {}

    private static Setting generalSetting(String key, boolean defaultValue) {
        return new Setting(
                key,
                () -> ModConfig.general(key),
                v -> ModConfig.INSTANCE.general.put(key, v),
                defaultValue
        );
    }

    private static Setting moddedSetting(String key, boolean defaultValue) {
        return new Setting(
                key,
                () -> ModConfig.modded(key),
                v -> ModConfig.INSTANCE.modded.put(key, v),
                defaultValue
        );
    }

    public static final Setting[] GENERAL_SETTINGS = new Setting[] {
            generalSetting("advancements.messages", false),
            generalSetting("advancements.toasts", true),
            generalSetting("experimental_warning", true),
            generalSetting("multiplayer_warning", true),
            generalSetting("recipes_toasts", true),
            generalSetting("resource_pack_warnings", true),
            generalSetting("system_toasts", false),
            generalSetting("tutorials", true)
    };

    public static final Setting[] MODDED_SETTINGS = new Setting[] {
            moddedSetting("chunks_fade_in", false),
            moddedSetting("fastquit", false),
            moddedSetting("frozenlib", false),
            moddedSetting("nether_weather", false),
            moddedSetting("terralith", false)
    };

    private ConfigSettings() {}
}