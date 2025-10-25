package com.alfakynz.nomorepopups;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoMorePopups implements ClientModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("NoMorePopups");
    @Override
    public void onInitializeClient() {
        ModConfig.load();
    }
}
