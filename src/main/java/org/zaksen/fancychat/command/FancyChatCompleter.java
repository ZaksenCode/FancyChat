package org.zaksen.fancychat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.zaksen.fancychat.chat.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FancyChatCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        switch (args.length) {
            case 1: {
                return Arrays.asList("join", "leave", "invite", "accept", "decline", "create");
            }
            case 2: {
                switch (args[0]) {
                    case "create": {
                        return Collections.singletonList("name");
                    }
                    case "invite": {
                        return null;
                    }
                    case "join": {
                        List<String> categories = new ArrayList<>();
                        Category.getCategories().forEach((category) -> {
                            categories.add(category.getName());
                        });
                        return categories;
                    }
                }
            }
        }
        return null;
    }
}