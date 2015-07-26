package com.caved_in.commons.debug.actions;

import com.caved_in.commons.Commons;
import com.caved_in.commons.Messages;
import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.debug.DebugAction;
import com.caved_in.commons.player.Players;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DebugHandItemJsonSerialize implements DebugAction {
    @Override
    public void doAction(Player player, String... args) {
        if (!Players.hasItemInHand(player)) {
            Chat.message(player, Messages.DEBUG_ACTION_REQUIRES_HAND_ITEM);
            return;
        }

        ItemStack hand = player.getItemInHand();
        Gson gson = Commons.getInstance().getGson();
        File itemFile = new File(Commons.DEBUG_DATA_FOLDER + UUID.randomUUID().toString() + ".json");
        try {
            FileUtils.writeStringToFile(itemFile, gson.toJson(hand.serialize()));
        } catch (IOException e) {
            Chat.message(player, "IOException saving file to Json");
            e.printStackTrace();
        }

        Chat.message(player, "Item in hand stored @ " + itemFile.getAbsolutePath());
    }

    @Override
    public String getActionName() {
        return "hand_item_json";
    }
}
