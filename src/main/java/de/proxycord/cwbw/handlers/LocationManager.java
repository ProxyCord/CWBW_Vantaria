// This class is created by LookIP
package de.proxycord.cwbw.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocationManager {

    private final File file = new File("/home/minecraft/confíg/CWBW.yml");
    private final YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    private final Map<LocationType, Location> loadedLocations = new HashMap<>();

    public enum LocationType {
        RED,
        BLUE,
        BLACK,
        ORANGE,
        WHITE,
        GREEN,
        CYAN,
        PINK,
        DEATHEIGHT,
        SPAWN;
    }


    public void setLocation(final String name, final Location location) {
        yml.set(name, locationToString(location));

        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation(final String name) {
        try {
            final LocationType type = LocationType.valueOf(name);

            if (loadedLocations.containsKey(type)) {
                return loadedLocations.get(type);
            } else {
                if (yml.get(type.name()) != null) {
                    loadedLocations.put(type, stringToLocation(yml.getString(type.name())));
                    return loadedLocations.get(type);
                } else {
                    return null;
                }
            }
        } catch (final IllegalArgumentException exception) {
            final Location location = stringToLocation(yml.getString(name));
            return location;
        }
    }

    public String locationToString(final Location location) {
        String loc = "";
        loc = location.getWorld().getUID() + "#" + location.getX() + "#" + location.getY() + "#" + location.getZ()
                + "#" + location.getPitch() + "#" + location.getYaw();
        return loc;
    }

    public Location stringToLocation(final String string) {
        final String[] split = string.split("#");
        final World world = Bukkit.getWorld(UUID.fromString(split[0]));

        final double x = Double.parseDouble(split[1]);
        final double y = Double.parseDouble(split[2]);
        final double z = Double.parseDouble(split[3]);

        final float pitch = Float.parseFloat(split[4]);
        final float yaw = Float.parseFloat(split[5]);

        final Location location = new Location(world, x, y, z);
        location.setPitch(pitch);
        location.setYaw(yaw);

        return location;
    }
}
