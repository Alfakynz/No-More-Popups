package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import com.alfakynz.nomorepopups.mixin.accessor.SystemToastAccessor;
import net.minecraft.client.gui.components.toasts.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing toasts (including Recipe, Tutorial, Advancement and System toasts)
 */
@Mixin(ToastComponent.class)
public class ToastManagerMixin {

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockRecipeToast(Toast toast, CallbackInfo ci) {
        if (toast instanceof RecipeToast && ModConfig.general("recipes_toasts")) {
            ci.cancel();
        }
    }

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockTutorialToast(Toast toast, CallbackInfo ci) {
        if (toast instanceof TutorialToast && ModConfig.general("tutorials")) {
            ci.cancel();
        }
    }

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockAdvancementToast(Toast toast, CallbackInfo ci) {
        if (toast instanceof AdvancementToast && ModConfig.general("advancements.toasts")) {
            ci.cancel();
        }
    }

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockSystemToast(Toast toast, CallbackInfo ci) {
        if (!(toast instanceof SystemToast systemToast)) {
            return;
        }

        SystemToastAccessor accessor = (SystemToastAccessor) systemToast;
        boolean isFastQuitToast = accessor.getTitle().getString().contains("FastQuit");
        boolean disableToast;

        if (isFastQuitToast) {
            disableToast = ModConfig.modded("fastquit");
        } else {
            disableToast = ModConfig.general("system_toasts");
        }

        if (disableToast) {
            ci.cancel();
        }
    }
}