package com.alfakynz.nomorepopups;

import net.fabricmc.api.ModInitializer;

public class NoMorePopupsFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        NoMorePopups.init();
    }
}
