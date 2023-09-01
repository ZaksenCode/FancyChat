package org.zaksen.fancychat.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zaksen.fancychat.chat.Category;
import org.zaksen.fancychat.chat.request.CategoryRequest;
import org.zaksen.fancychat.chat.request.RequestType;
import org.zaksen.fancychat.util.ChatUtil;

public class FancyChatCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) { return true; }
        Player player = (Player) sender;
        if(args.length > 0) {
            switch (args[0]) {
                case "join": {
                    Category category = Category.getCategoryBy(args[1]);
                    if(category != null && !category.isOwner(player)) {
                        new CategoryRequest(RequestType.JOIN, category, player, category.getOwner());
                    } else {
                        ChatUtil.Instance.sendMessage(player, "такой группы не существует");
                    }
                    break;
                }
                case "leave": {
                    Category category = Category.getCategoryBy(player);
                    if(category != null) {
                        category.leave(player);
                    } else {
                        ChatUtil.Instance.sendMessage(player, "вы не состоите в группе");
                    }
                    break;
                }
                case "invite": {
                    Category category = Category.getCategoryBy(player);
                    if(category != null && category.isOwner(player)) {
                        new CategoryRequest(RequestType.INVITE, category, category.getOwner(), Bukkit.getPlayer(args[1]));
                    } else {
                        ChatUtil.Instance.sendMessage(player, "вы не владелец группы");
                    }
                    break;
                }
                case "accept": {
                    CategoryRequest request = CategoryRequest.getRequestBy(player);
                    if(request != null) {
                        request.accept();
                    } else {
                        ChatUtil.Instance.sendMessage(player, "у вас нету приглашения");
                    }
                    break;
                }
                case "decline": {
                    CategoryRequest request = CategoryRequest.getRequestBy(player);
                    if(request != null) {
                        request.decline();
                    } else {
                        ChatUtil.Instance.sendMessage(player, "у вас нету приглашения");
                    }
                    break;
                }
                case "create": {
                    Category category = Category.getCategoryBy(player);
                    Category category1 = Category.getCategoryBy(args[1]);
                    if(category == null && category1 == null) {
                        new Category(args[1], player);
                    } else {
                        ChatUtil.Instance.sendMessage(player, "вы уже создали свою группу");
                    }
                    break;
                }
                default: {
                    ChatUtil.Instance.sendMessage(player, "такой команды не существует");
                    break;
                }
            }
        }
        return true;
    }
}