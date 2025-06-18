package com.epicplayera10.exampleplugin;

import co.aikar.commands.PaperCommandManager;
import com.epicplayera10.exampleplugin.commands.ExampleCommand;
import com.epicplayera10.exampleplugin.config.ConfigurationFactory;
import com.epicplayera10.exampleplugin.config.DataConfiguration;
import com.epicplayera10.exampleplugin.config.PluginConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ExamplePlugin extends JavaPlugin {
    private PluginConfiguration pluginConfiguration;
    private DataConfiguration dataConfiguration;

    private static ExamplePlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        this.pluginConfiguration = ConfigurationFactory.createPluginConfiguration(new File(this.getDataFolder(), "config.yml"));
        this.dataConfiguration = ConfigurationFactory.createDataConfiguration(new File(this.getDataFolder(), "data.yml"));

        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        PaperCommandManager manager = new PaperCommandManager(this);

        manager.enableUnstableAPI("help");

        manager.registerCommand(new ExampleCommand());
    }

    public static ExamplePlugin instance() {
        return instance;
    }

    public PluginConfiguration pluginConfiguration() {
        return pluginConfiguration;
    }

    public DataConfiguration dataConfiguration() {
        return dataConfiguration;
    }

    public void reloadConfiguration() {
        this.pluginConfiguration.load();
        this.dataConfiguration.load();
    }
}
