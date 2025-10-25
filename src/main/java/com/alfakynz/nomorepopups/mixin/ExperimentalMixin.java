package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldOpenFlows.class)
public class ExperimentalMixin {
    
    @ModifyVariable(method = "confirmWorldCreation", at = @At("HEAD"))
    private static boolean removeExperimentalWarning(boolean original) {
        return ModConfig.INSTANCE.disableExperimentalWarning || original;
    }
}