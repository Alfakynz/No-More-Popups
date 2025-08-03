package com.alfakynz.nomorepopups.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

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

        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable(getToggleText(ModConfig.INSTANCE.disableRecipeToasts,  "disable_recipe_toasts")),
                button -> {
                    ModConfig.INSTANCE.disableRecipeToasts = !ModConfig.INSTANCE.disableRecipeToasts;
                    button.setMessage(Text.translatable(getToggleText(ModConfig.INSTANCE.disableRecipeToasts, "disable_recipe_toasts")));
                }
        ).position(centerX - 100, y).size(200, 20).build());

        y += 32;

        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable(getToggleText(ModConfig.INSTANCE.disableTutorialToasts, "disable_tutorial_toasts")),
                button -> {
                    ModConfig.INSTANCE.disableTutorialToasts = !ModConfig.INSTANCE.disableTutorialToasts;
                    button.setMessage(Text.translatable(getToggleText(ModConfig.INSTANCE.disableTutorialToasts, "disable_tutorial_toasts")));
                }
        ).position(centerX - 100, y).size(200, 20).build());

        y += 32;

        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable(getToggleText(ModConfig.INSTANCE.disableAdvancementToasts, "disable_advancement_toasts")),
                button -> {
                    ModConfig.INSTANCE.disableAdvancementToasts = !ModConfig.INSTANCE.disableAdvancementToasts;
                    button.setMessage(Text.translatable(getToggleText(ModConfig.INSTANCE.disableAdvancementToasts, "disable_advancement_toasts")));
                }
        ).position(centerX - 100, y).size(200, 20).build());

        y += 32;

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

    private String getToggleText(boolean enabled, String text) {
        return "option.no_more_popups.settings." + text + "." + (enabled ? "on" : "off");
    }
}