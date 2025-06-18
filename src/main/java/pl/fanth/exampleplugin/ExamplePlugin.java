package pl.fanth.exampleplugin;

import co.aikar.commands.PaperCommandManager;
import pl.fanth.exampleplugin.commands.ExampleCommand;
import pl.fanth.exampleplugin.config.ConfigurationFactory;
import pl.fanth.exampleplugin.config.DataConfiguration;
import pl.fanth.exampleplugin.config.PluginConfiguration;
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
