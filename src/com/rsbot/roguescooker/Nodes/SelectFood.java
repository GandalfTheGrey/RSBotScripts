package com.rsbot.roguescooker.nodes;

import com.rsbot.roguescooker.utils.Methods;
import com.rsbot.roguescooker.vars.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class SelectFood extends Node {

    public boolean activate() {
        return Variables.guiDisposed
                && Methods.hasItems(Variables.foodID)
                && !Players.getLocal().isMoving()
                && !Methods.hasSelectedFood(Variables.foodID)
                && !Methods.isCooking();
    }

    public void execute() {
        Item foodSelect = Inventory.getItem(Variables.foodID);

        if (foodSelect != null) {
            if (foodSelect.getWidgetChild().isOnScreen()) {
                if (!Methods.hasSelectedFood(Variables.foodID)) {
                    foodSelect.getWidgetChild().click(true);
                }
            }
        }
    }
}