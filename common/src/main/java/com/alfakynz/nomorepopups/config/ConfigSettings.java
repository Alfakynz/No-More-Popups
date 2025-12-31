package com.alfakynz.nomorepopups.config;

import net.minecraft.client.Minecraft;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public final class ConfigSettings {

    public record Setting(
            String key,
            BooleanSupplier getter,
            Consumer<Boolean> setter,
            boolean defaultValue
    ) {}

    public static final Setting[] SETTINGS = new Setting[] {
            new Setting(
                    "disable_advancements_messages",
                    () -> ModConfig.INSTANCE.disableAdvancementsMessages,
                    v -> ModConfig.INSTANCE.disableAdvancementsMessages = v,
                    false
            ),
            new Setting(
                    "disable_advancement_toasts",
                    () -> ModConfig.INSTANCE.disableAdvancementToasts,
                    v -> ModConfig.INSTANCE.disableAdvancementToasts = v,
                    true
            ),
            new Setting(
                    "disable_experimental_warning",
                    () -> ModConfig.INSTANCE.disableExperimentalWarning,
                    v -> ModConfig.INSTANCE.disableExperimentalWarning = v,
                    true
            ),
            new Setting(
                    "disable_multiplayer_warning",
                    () -> ModConfig.INSTANCE.disableMultiplayerWarning,
                    v -> {
                        ModConfig.INSTANCE.disableMultiplayerWarning = v;
                        Minecraft.getInstance().options.skipMultiplayerWarning = v;
                        Minecraft.getInstance().options.save();
                    },
                    true
            ),
            new Setting(
                    "disable_recipe_toasts",
                    () -> ModConfig.INSTANCE.disableRecipeToasts,
                    v -> ModConfig.INSTANCE.disableRecipeToasts = v,
                    true
            ),
            new Setting(
                    "disable_resource_pack_warnings",
                    () -> ModConfig.INSTANCE.disableResourcePackWarnings,
                    v -> ModConfig.INSTANCE.disableResourcePackWarnings = v,
                    true
            ),
            new Setting(
                    "disable_system_toasts",
                    () -> ModConfig.INSTANCE.disableSystemToasts,
                    v -> ModConfig.INSTANCE.disableSystemToasts = v,
                    false
            ),
            new Setting(
                    "disable_tutorial_toasts",
                    () -> ModConfig.INSTANCE.disableTutorialToasts,
                    v -> ModConfig.INSTANCE.disableTutorialToasts = v,
                    true
            )
    };

    private ConfigSettings() {}
}