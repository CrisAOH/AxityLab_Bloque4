package com.axity.dinosaurpark.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ParkConfig {
    private static ParkConfig instance;
    private final Properties props = new Properties();

    private ParkConfig() throws IOException {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("park.properties")) {
            if (is == null) {
                throw new IOException("Configuration file 'park.properties' was not found in the application classpath.");
            }

            props.load(is);
        }
    }

    public static ParkConfig getInstance() throws IOException {
        if (instance == null) {
            instance = new ParkConfig();
        }

        return instance;
    }

    public int getInt(String key, int defaultValue) {
        String property = props.getProperty(key);

        if (property == null) {
            System.out.println("Property '" + key + "' not found. Using default value: " + defaultValue);
            return defaultValue;
        }

        try {
            return Integer.parseInt(property);
        } catch (NumberFormatException e) {
            System.err.println("Invalid numeric value for property '" + key + "': '" + property + "'. Using default value: " + defaultValue);
            return defaultValue;
        }
    }

    public double getDouble(String key, double defaultValue) {
        String property = props.getProperty(key);

        if (property == null) {
            System.out.println("Property '" + key + "' not found. Using default value: " + defaultValue);
            return defaultValue;
        }

        try {
            return Double.parseDouble(property);
        } catch (NumberFormatException e) {
            System.err.println("Invalid numeric value for property '" + key + "': '" + property + "'. Using default value: " + defaultValue);
            return defaultValue;
        }
    }

    public long getLong(String key, long defaultValue) {
        String property = props.getProperty(key);

        if (property == null) {
            System.out.println("Property '" + key + "' not found. Using default value: " + defaultValue);
            return defaultValue;
        }

        try {
            return Long.parseLong(property);
        } catch (NumberFormatException e) {
            System.err.println("Invalid numeric value for property '" + key + "': '" + property + "'. Using default value: " + defaultValue);
            return defaultValue;
        }
    }

    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public long getSeed() {
        return getLong("simulation.seed", 42L);
    }

    public int getTotalSteps() {
        return getInt("simulation.totalSteps", 100);
    }

    static void resetForTesting() {
        instance = null;
    }
}