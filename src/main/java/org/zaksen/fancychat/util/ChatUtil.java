package org.zaksen.fancychat.util;

import org.bukkit.entity.Player;
import org.zaksen.fancychat.FancyChat;

public class ChatUtil {
    public static ChatUtil Instance = new ChatUtil();
    private final String prefix;

    private ChatUtil() {
        prefix = FancyChat.getInstance().getConfig().getString("command-prefix");
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(prefix + message);
    }
}