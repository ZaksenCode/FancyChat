package org.zaksen.fancychat.chat.request;

import org.bukkit.entity.Player;
import org.zaksen.fancychat.chat.Category;
import org.zaksen.fancychat.util.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryRequest {
    private static List<CategoryRequest> requests = new ArrayList<>();
    public static CategoryRequest getRequestBy(Player player) {
        for(CategoryRequest request : requests) {
            if(request.to == player) {
                return request;
            }
        }
        return null;
    }
    private RequestType type = RequestType.UNSET;
    private final Player from, to;
    private final Category category;

    public CategoryRequest(RequestType type, Category forCategory, Player from, Player to) {
        this.type = type;
        this.category = forCategory;
        this.from = from;
        this.to = to;
        if(setupType()) {
            requests.add(this);
        }
    }

    private boolean setupType() {
        switch (type) {
            case UNSET: {
                System.out.println("создан заспрос неопределенного типа");
                return false;
            }
            case JOIN: {
                ChatUtil.Instance.sendMessage(to,
                        "вам отправили запрос на вступление в вашу группу, используйте /fc accept или /fc decline");
                return true;
            }
            case INVITE: {
                ChatUtil.Instance.sendMessage(to,
                        "вас приглащают в группу, используйте /fc accept или /fc decline");
                return true;
            }
        }
        return false;
    }

    public void accept() {
        switch (type) {
            case INVITE: {
                ChatUtil.Instance.sendMessage(from,
                        "игрок принял ваш запрос");
                category.join(to);
                break;
            }
            case JOIN: {
                ChatUtil.Instance.sendMessage(from,
                        "ваш запрос был принят");
                category.join(from);
                break;
            }
        }
        breakRequest();
    }

    public void decline() {
        switch (type) {
            case INVITE: {
                ChatUtil.Instance.sendMessage(from,
                        "игрок не принял ваш запрос");
                break;
            }
            case JOIN: {
                ChatUtil.Instance.sendMessage(from,
                        "ваш запрос был отклонен");
                break;
            }
        }
        breakRequest();
    }

    public void breakRequest() {
        requests.remove(this);
    }
}