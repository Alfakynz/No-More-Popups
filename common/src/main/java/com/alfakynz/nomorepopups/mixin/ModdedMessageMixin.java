package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing modded messages.
 */
@Mixin(ClientPacketListener.class)
public class ModdedMessageMixin {

    @Inject(method = "handleSystemChat", at = @At("HEAD"), cancellable = true)
    private void onSystemChat(ClientboundSystemChatPacket packet, CallbackInfo ci) {

        if (ModConfig.modded("messages")) return;

        Component message = packet.content();
        String plainText = message.getString();

        // Nether Weather
        if (plainText.contains("Nether Weather") && !ModConfig.general("nether_weather")) {
            ci.cancel();
        }
    }
}