package com.alfakynz.nomorepopups.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModSettingsScreen extends Screen {
    private final Screen parent;

    public ModSettingsScreen(Screen parent) {
        super(Text.translatable("option.no_more_popups.settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int y = this.height / 3;

        List<ToggleOption> toggleOptions = List.of(
            new ToggleOption("option.no_more_popups.settings.disable_advancements_messages",
                () -> ModConfig.INSTANCE.disableAdvancementsMessages,
                value -> ModConfig.INSTANCE.disableAdvancementsMessages = value),
            new ToggleOption("option.no_more_popups.settings.disable_advancement_toasts",
                () -> ModConfig.INSTANCE.disableAdvancementToasts,
                value -> ModConfig.INSTANCE.disableAdvancementToasts = value),

            new ToggleOption("option.no_more_popups.settings.disable_experimental_warning",
                () -> ModConfig.INSTANCE.disableExperimentalWarning,
                value -> ModConfig.INSTANCE.disableExperimentalWarning = value),

            new ToggleOption("option.no_more_popups.settings.disable_multiplayer_warning",
                () -> ModConfig.INSTANCE.disableMultiplayerWarning,
                value -> {
                    ModConfig.INSTANCE.disableMultiplayerWarning = value;
                    if (value) {
                        this.client.options.skipMultiplayerWarning = true;
                    } else {
                        this.client.options.skipMultiplayerWarning = false;
                    }
                    this.client.options.write();
            }),

            new ToggleOption("option.no_more_popups.settings.disable_recipe_toasts",
                () -> ModConfig.INSTANCE.disableRecipeToasts,
                value -> ModConfig.INSTANCE.disableRecipeToasts = value),

            new ToggleOption("option.no_more_popups.settings.disable_resource_pack_warnings",
                () -> ModConfig.INSTANCE.disableResourcePackWarnings,
                value -> ModConfig.INSTANCE.disableResourcePackWarnings = value),

            new ToggleOption("option.no_more_popups.settings.disable_tutorial_toasts",
                () -> ModConfig.INSTANCE.disableTutorialToasts,
                value -> ModConfig.INSTANCE.disableTutorialToasts = value)
        );

        for (ToggleOption option : toggleOptions) {
            this.addDrawableChild(ButtonWidget.builder(
                Text.translatable(option.translationKey)
                    .append(": ")
                    .append(Text.translatable(getToggleText(option.getter.get()))),
                button -> {
                    boolean newValue = !option.getter.get();
                    option.setter.accept(newValue);
                    button.setMessage(
                        Text.translatable(option.translationKey)
                            .append(": ")
                            .append(Text.translatable(getToggleText(newValue)))
                    );
                }
            ).position(centerX - 100, y).size(200, 20).build());
            y += 32;
        }

        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("option.no_more_popups.settings.done"),
                button -> {
                    ModConfig.save();
                    assert this.client != null;
                    this.client.setScreen(parent);
                }
        ).position(centerX - 100, y).size(200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    private String getToggleText(boolean enabled) {
        return "option.no_more_popups.settings." + (enabled ? "yes" : "no");
    }

    private static class ToggleOption {
        final String translationKey;
        final Supplier<Boolean> getter;
        final Consumer<Boolean> setter;

        ToggleOption(String translationKey, Supplier<Boolean> getter, Consumer<Boolean> setter) {
            this.translationKey = translationKey;
            this.getter = getter;
            this.setter = setter;
        }
    }
}