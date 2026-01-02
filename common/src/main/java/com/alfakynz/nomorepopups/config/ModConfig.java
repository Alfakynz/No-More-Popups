package com.alfakynz.nomorepopups.config;

import com.alfakynz.nomorepopups.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ModConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final File NEW_CONFIG_FILE = new File("config/no-more-popups.json");
    private static final File OLD_CONFIG_FILE = new File("config/nomorepopups.json");

    public Map<String, Boolean> general = new TreeMap<>();
    public Map<String, Boolean> modded = new TreeMap<>();

    public static ModConfig INSTANCE = createDefault();

    private static ModConfig createDefault() {
        ModConfig config = new ModConfig();

        config.general.put("advancements.messages", false);
        config.general.put("advancements.toasts", true);
        config.general.put("recipes_toasts", true);
        config.general.put("tutorials", true);
        config.general.put("system_toasts", false);
        config.general.put("experimental_warning", true);
        config.general.put("multiplayer_warning", true);
        config.general.put("resource_pack_warnings", true);

        config.modded.put("chunks_fade_in", false);
        config.modded.put("fastquit", false);
        config.modded.put("nether_weather", false);

        return config;
    }

    public static void load() {
        try {
            // New file exists
            if (NEW_CONFIG_FILE.exists()) {
                try (Reader reader = new FileReader(NEW_CONFIG_FILE)) {
                    JsonElement root = GSON.fromJson(reader, JsonElement.class);

                    // New file but old format inside
                    if (root.isJsonObject()
                            && root.getAsJsonObject().has("disableAdvancementsMessages")) {

                        INSTANCE = migrateOldFormat(root.getAsJsonObject());
                        save();
                    } else {
                        INSTANCE = GSON.fromJson(root, ModConfig.class);
                    }
                }
                return;
            }

            // Only old file exists
            if (OLD_CONFIG_FILE.exists()) {
                try (Reader reader = new FileReader(OLD_CONFIG_FILE)) {
                    JsonObject oldRoot = GSON.fromJson(reader, JsonObject.class);
                    INSTANCE = migrateOldFormat(oldRoot);
                }

                save();
                if (OLD_CONFIG_FILE.delete()) {
                    Constants.LOG.info("Migrated No More Popups config to new format.");
                }
                return;
            }

            // No config at all
            INSTANCE = createDefault();
            save();

        } catch (Exception e) {
            Constants.LOG.error("Failed to load or migrate No More Popups config, using defaults.", e);
            INSTANCE = createDefault();
        }
    }

    public static void save() {
        try {
            File parent = NEW_CONFIG_FILE.getParentFile();
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    Constants.LOG.warn("Failed to create config directory: {}", parent.getAbsolutePath());
                }
            }

            INSTANCE.general = new TreeMap<>(INSTANCE.general);
            INSTANCE.modded = new TreeMap<>(INSTANCE.modded);

            try (Writer writer = new FileWriter(NEW_CONFIG_FILE)) {
                GSON.toJson(INSTANCE, writer);
            }
        } catch (IOException e) {
            Constants.LOG.error("Failed to save No More Popups config.", e);
        }
    }

    private static ModConfig migrateOldFormat(JsonObject old) {
        ModConfig config = createDefault();

        map(old, "disableAdvancementsMessages", config.general, "advancements.messages");
        map(old, "disableAdvancementToasts", config.general, "advancements.toasts");
        map(old, "disableRecipeToasts", config.general, "recipes_toasts");
        map(old, "disableTutorialToasts", config.general, "tutorials");
        map(old, "disableSystemToasts", config.general, "system_toasts");
        map(old, "disableExperimentalWarning", config.general, "experimental_warning");
        map(old, "disableMultiplayerWarning", config.general, "multiplayer_warning");
        map(old, "disableResourcePackWarnings", config.general, "resource_pack_warnings");

        return config;
    }

    private static void map(
            JsonObject old,
            String oldKey,
            Map<String, Boolean> target,
            String newKey
    ) {
        if (old.has(oldKey)) {
            target.put(newKey, old.get(oldKey).getAsBoolean());
        }
    }

    public static boolean general(String key) {
        return INSTANCE.general.getOrDefault(key, true);
    }

    public static boolean modded(String key) {
        return INSTANCE.modded.getOrDefault(key, true);
    }
}