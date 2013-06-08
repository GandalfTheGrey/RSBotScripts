package com.rsbot.roguescooker.utils;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Methods {
    public static final WidgetChild COOK_BUTTON = Widgets.get(275, 40);

    public static boolean cookFood() {
       return (COOK_BUTTON != null && COOK_BUTTON.visible());
    }

    public static boolean isCooking() {
        Timer timer = new Timer(1000);
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