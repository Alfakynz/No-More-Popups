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

    public static final Setting[] GENERAL_SETTINGS = new Setting[] {
            new Setting(
                    "advancements.messages",
                    () -> ModConfig.general("advancements.messages"),
                    v -> ModConfig.INSTANCE.general.put("advancements.messages", v),
                    false
            ),
            new Setting(
                    "advancements.toasts",
                    () -> ModConfig.general("advancements.toasts"),
                    v -> ModConfig.INSTANCE.general.put("advancements.toasts", v),
                    true
            ),
            new Setting(
                    "experimental_warning",
                    () -> ModConfig.general("experimental_warning"),
                    v -> ModConfig.INSTANCE.general.put("experimental_warning", v),
                    true
            ),
            new Setting(
                    "multiplayer_warning",
                    () -> ModConfig.general("multiplayer_warning"),
                    v -> ModConfig.INSTANCE.general.put("multiplayer_warning", v),
                    true
            ),
            new Setting(
                    "recipes_toasts",
                    () -> ModConfig.general("recipes_toasts"),
                    v -> ModConfig.INSTANCE.general.put("recipes_toasts", v),
                    true
            ),
            new Setting(
                    "resource_pack_warnings",
                    () -> ModConfig.general("resource_pack_warnings"),
                    v -> ModConfig.INSTANCE.general.put("resource_pack_warnings", v),
                    true
            ),
            new Setting(
                    "system_toasts",
                    () -> ModConfig.general("system_toasts"),
                    v -> ModConfig.INSTANCE.general.put("system_toasts", v),
                    false
            ),
            new Setting(
                    "tutorials",
                    () -> ModConfig.general("tutorials"),
                    v -> ModConfig.INSTANCE.general.put("tutorials", v),
                    true
            )
    };

    public static final Setting[] MODDED_SETTINGS = new Setting[] {
            new Setting(
                    "fastquit",
                    () -> ModConfig.modded("fastquit"),
                    v -> ModConfig.INSTANCE.modded.put("fastquit", v),
                    false
            ),
            new Setting(
                    "nether_weather",
                    () -> ModConfig.modded("nether_weather"),
                    v -> ModConfig.INSTANCE.modded.put("nether_weather", v),
                    false
            )
    };

    private ConfigSettings() {}
}