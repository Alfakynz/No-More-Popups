package com.alfakynz.nomorepopups.mixin.compat;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing modded messages. (Server-side)
 */
@Mixin(ClientPacketListener.class)
public class ModdedMessageServerMixin {

    @Inject(method = "handleSystemChat", at = @At("HEAD"), cancellable = true)
    private void onSystemChat(ClientboundSystemChatPacket packet, CallbackInfo ci) {

        Component message = packet.content();
        String plainText = message.getString();

        // Nether Weather
        if (ModConfig.modded("nether_weather")
                && plainText.contains("Nether Weather")
                && plainText.contains("was successfully loaded")) {
            ci.cancel();
        }

        // Terralith
        if (ModConfig.modded("terralith")
                && plainText.contains("Hello, thank you for downloading ")
                && plainText.contains("Terralith")) {
            ci.cancel();
        }
    }
}