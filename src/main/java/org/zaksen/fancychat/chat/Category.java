package org.zaksen.fancychat.chat;

import org.bukkit.entity.Player;
import org.zaksen.fancychat.FancyChat;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private static List<Category> categories = new ArrayList<>();
    private final List<Player> members = new ArrayList<>();
    private final Player owner;
    private final String name;

    public static List<Category> getCategories() {
        return categories;
    }

    public static Category getCategoryBy(Player player) {
        for (Category category : categories) {
            if(category.members.contains(player)) {
                return category;
            }
        }
        return null;
    }

    public static Category getCategoryBy(String name) {
        for (Category category : categories) {
            if(category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public static boolean hasCategory(Player player) {
        return getCategoryBy(player) != null;
    }

    public Category(String name, Player owner) {
        this.name = name;
        this.owner = owner;
        members.add(owner);
        categories.add(this);
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOwner(Player player) {
        return player == owner;
    }

    public void sendMassage(Player by, String message) {
        String format = FancyChat.getInstance().getConfig().getString("message-format");
        members.forEach((player) -> {
            if(format != null) {
                player.sendMessage(String.format(format, name, by.getName(), message));
            }
        });
    }

    public void join(Player player) {
        Category category = getCategoryBy(player);
        if(category != null) {
            category.leave(player);
        }
        members.add(player);
    }

    public void leave(Player player) {
        if(player == owner) {
            breakCategory();
        }
        members.remove(player);
    }

    public void breakCategory() {
        sendMassage(owner, "группа была удалена владельцем");
        categories.remove(this);
    }
}