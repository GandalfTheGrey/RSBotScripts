package com.rsbot.roguescooker.Utils;

import org.powerbot.core.script.methods.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: Romi Grace
 * Date: 01/06/13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class Methods {

    public static boolean isCooking() {
        Timer timer = new Timer(800);
        while (timer.isRunning()) {
            if (Players.getLocal().getAnimation() != -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSelectedFood() {
        return Inventory.isItemSelected();
    }

    public static boolean hasItems(int ID) {
        return Inventory.contains(ID);
    }
}