package org.zaksen.fancychat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.zaksen.fancychat.chat.Category;

public class Chat implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        if(Category.hasCategory(event.getPlayer())) {
            Category.getCategoryBy(event.getPlayer()).sendMassage(event.getPlayer(),event.getMessage());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(Category.hasCategory(event.getPlayer())) {
            Player player = event.getPlayer();
            Category.getCategoryBy(player).leave(player);
        }
    }
}