package com.alfakynz.nomorepopups;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;

public class NoMorePopupsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig.load();
    }
}
