package com.alfakynz.nomorepopups;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoMorePopups implements ModInitializer {
	public static final String MOD_ID = "no-more-popups";
	public static final String MOD_NAME = "No More Pop-ups";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("Loaded");
	}
}