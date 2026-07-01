package pl.fanth.exampleplugin.config;

import eu.okaeri.configs.OkaeriConfig;
import pl.fanth.exampleplugin.ExamplePlugin;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.validator.okaeri.OkaeriValidator;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;

import java.io.File;

public class ConfigurationFactory {

    private ConfigurationFactory(){
    }

    public static <T extends OkaeriConfig> T createConfiguration(Class<T> clazz, File configurationFile) {
        return ConfigManager.create(clazz, it -> {
            it.configure(opt -> {
                opt.configurer(new YamlBukkitConfigurer(), new SerdesBukkit(), new SerdesCommons());
                opt.validator(new OkaeriValidator());
                opt.bindFile(configurationFile); // specify Path, File or pathname
                opt.removeOrphans(true); // automatic removal of undeclared keys
                opt.resolvePlaceholders(); // resolve ${VAR} and ${VAR:default} from environment
                opt.logger(ExamplePlugin.instance().getLogger());
            });
            it.saveDefaults();
            it.load(true);
        });
    }
}
