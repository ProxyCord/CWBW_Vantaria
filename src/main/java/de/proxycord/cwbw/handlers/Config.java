// This class is created by LookIP
package de.proxycord.cwbw.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private File defaultfile, file;
    private String configname;
    private FileConfiguration config;

    public Config(final File file, final String configname) {
        this.defaultfile = file;
        this.configname = configname;
        this.file = new File(this.defaultfile, this.configname);
        this.config = YamlConfiguration.loadConfiguration(this.file);

        this.config.options().header("\nConfiguration of " + configname + "\nMade by Vantaria.de1\n");
        this.config.options().copyHeader();
        this.config.options().copyHeader(true);

        save();
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public File getFile() {
        return this.file;
    }

    public File getParent() {
        return this.defaultfile;
    }

    public String getConfigName() {
        return this.configname;
    }
}
