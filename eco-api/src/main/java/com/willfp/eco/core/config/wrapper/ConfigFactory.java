package com.willfp.eco.core.config.wrapper;

import com.willfp.eco.core.Eco;
import com.willfp.eco.core.PluginLike;
import com.willfp.eco.core.config.ConfigType;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.eco.core.config.interfaces.LoadableConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Internal component to create backend config implementations.
 */
@ApiStatus.Internal
@Eco.HandlerComponent
public interface ConfigFactory {
    /**
     * Updatable config.
     *
     * @param configName            The name of the config
     * @param plugin                The plugin.
     * @param subDirectoryPath      The subdirectory path.
     * @param source                The class that owns the resource.
     * @param removeUnused          Whether keys not present in the default config should be removed on update.
     * @param type                  The config type.
     * @param updateBlacklist       Substring of keys to not add/remove keys for.
     * @param requiresChangesToSave If the config must be changed in order to save the config.
     * @return The config implementation.
     */
    LoadableConfig createUpdatableConfig(@NotNull String configName,
                                         @NotNull PluginLike plugin,
                                         @NotNull String subDirectoryPath,
                                         @NotNull Class<?> source,
                                         boolean removeUnused,
                                         @NotNull ConfigType type,
                                         boolean requiresChangesToSave,
                                         @NotNull String... updateBlacklist);

    /**
     * Loadable config.
     *
     * @param configName            The name of the config
     * @param plugin                The plugin.
     * @param subDirectoryPath      The subdirectory path.
     * @param source                The class that owns the resource.
     * @param type                  The config type.
     * @param requiresChangesToSave If the config must be changed in order to save the config.
     * @return The config implementation.
     */
    LoadableConfig createLoadableConfig(@NotNull String configName,
                                        @NotNull PluginLike plugin,
                                        @NotNull String subDirectoryPath,
                                        @NotNull Class<?> source,
                                        @NotNull ConfigType type,
                                        boolean requiresChangesToSave);

    /**
     * Create config.
     *
     * @param config The handle.
     * @return The config implementation.
     */
    Config createConfig(@NotNull ConfigurationSection config);

    /**
     * Create config.
     *
     * @param values The values.
     * @param type   The config type.
     * @return The config implementation.
     */
    Config createConfig(@NotNull Map<String, Object> values,
                        @NotNull ConfigType type);

    /**
     * Create config.
     *
     * @param contents The file contents.
     * @param type     The type.
     * @return The config implementation.
     */
    Config createConfig(@NotNull String contents,
                        @NotNull ConfigType type);
}
