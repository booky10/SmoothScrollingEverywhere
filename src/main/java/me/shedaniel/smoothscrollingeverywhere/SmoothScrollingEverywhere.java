package me.shedaniel.smoothscrollingeverywhere;

import me.shedaniel.clothconfig2.impl.EasingMethod;
import me.shedaniel.clothconfig2.impl.EasingMethods;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class SmoothScrollingEverywhere implements ClientModInitializer {

    private static EasingMethod easingMethod = EasingMethod.EasingMethodImpl.LINEAR;
    private static double bounceBackMultiplier = 0.24;
    private static long scrollDuration = 600;
    private static double scrollStep = 19;

    public static EasingMethod getEasingMethod() {
        return easingMethod;
    }

    public static long getScrollDuration() {
        return scrollDuration;
    }

    public static double getScrollStep() {
        return scrollStep;
    }

    public static double getBounceBackMultiplier() {
        return bounceBackMultiplier;
    }

    @Override
    public void onInitializeClient() {
        loadConfig();
    }

    private static void loadConfig() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("smoothscrollingeverywhere.properties");

        try {
            easingMethod = EasingMethod.EasingMethodImpl.LINEAR;
            bounceBackMultiplier = 0.24;
            scrollDuration = 600;
            scrollStep = 19;

            if (!Files.exists(path)) saveConfig();
            Properties properties = new Properties();
            properties.load(Files.newInputStream(path));

            String easing = properties.getProperty("easingMethod", easingMethod.toString());
            for (EasingMethod value : EasingMethods.getMethods()) {
                if (value.toString().equalsIgnoreCase(easing)) {
                    easingMethod = value;
                    break;
                }
            }

            scrollDuration = Long.parseLong(properties.getProperty("scrollDuration", String.valueOf(600L)));
            scrollStep = Double.parseDouble(properties.getProperty("scrollStep", String.valueOf(19d)));
            bounceBackMultiplier = Double.parseDouble(properties.getProperty("bounceBackMultiplier", String.valueOf(0.24d)));
        } catch (Exception exception) {
            exception.printStackTrace();

            easingMethod = EasingMethod.EasingMethodImpl.LINEAR;
            bounceBackMultiplier = 0.24;
            scrollDuration = 600;
            scrollStep = 19;

            try {
                Files.deleteIfExists(path);
            } catch (Exception ignored) {
            }
        }

        saveConfig();
    }

    private static void saveConfig() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve("smoothscrollingeverywhere.properties");

        try {
            Properties properties = new Properties();
            properties.setProperty("bounceBackMultiplier", String.valueOf(bounceBackMultiplier));
            properties.setProperty("scrollDuration", String.valueOf(scrollDuration));
            properties.setProperty("scrollStep", String.valueOf(scrollStep));
            properties.setProperty("easingMethod", easingMethod.toString());
            properties.store(Files.newOutputStream(file), null);
        } catch (Exception exception) {
            exception.printStackTrace();

            easingMethod = EasingMethod.EasingMethodImpl.LINEAR;
            bounceBackMultiplier = 0.24;
            scrollDuration = 600;
            scrollStep = 19;
        }
    }
}
