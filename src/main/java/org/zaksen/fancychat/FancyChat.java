package org.zaksen.fancychat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.zaksen.fancychat.command.FancyChatCMD;
import org.zaksen.fancychat.command.FancyChatCompleter;
import org.zaksen.fancychat.events.Chat;

public final class FancyChat extends JavaPlugin {

    private static FancyChat Instance;

    public static FancyChat getInstance() {
        return Instance;
    }

    @Override
    public void onEnable() {
        // Load config
        saveDefaultConfig();
        // Get instance
        Instance = this;
        // Plugin startup logic
        getCommand("fancy_chat").setExecutor(new FancyChatCMD());
        getCommand("fancy_chat").setTabCompleter(new FancyChatCompleter());
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
