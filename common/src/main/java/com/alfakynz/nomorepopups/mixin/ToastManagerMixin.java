package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.gui.components.toasts.AdvancementToast;
import net.minecraft.client.gui.components.toasts.RecipeToast;
import net.minecraft.client.gui.components.toasts.TutorialToast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing toasts (including Recipe, Tutorial and Advancement)
 */
@Mixin(ToastComponent.class)
public class ToastManagerMixin {

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockRecipeToast(Toast toast, CallbackInfo ci) {
        if (toast instanceof RecipeToast&& ModConfig.INSTANCE.disableRecipeToasts) {
            ci.cancel();
        }
    }

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockTutorialToast(Toast toast, CallbackInfo ci) {
        if (toast instanceof TutorialToast && ModConfig.INSTANCE.disableTutorialToasts) {
            ci.cancel();
        }
    }

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockAdvancementToast(Toast toast, CallbackInfo ci) {
        if (toast instanceof AdvancementToast && ModConfig.INSTANCE.disableAdvancementToasts) {
            ci.cancel();
        }
    }
}