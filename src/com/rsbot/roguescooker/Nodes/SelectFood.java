package com.rsbot.roguescooker.Nodes;

import com.rsbot.roguescooker.Utils.Methods;
import com.rsbot.roguescooker.Variables.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.methods.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Romi Grace
 * Date: 01/06/13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class SelectFood extends Node {

    public boolean activate() {
        return Variables.guiDisposed
                && Methods.hasItems(Variables.foodID)
                && !Players.getLocal().isMoving()
                && !Methods.hasSelectedFood()
                && !Methods.isCooking();
    }

    public void execute() {
        Item foodSelect = Inventory.getItem(Variables.foodID);

        if (foodSelect != null) {
            if (foodSelect.getWidgetChild().isOnScreen()) {
                if (!Methods.hasSelectedFood()) {
                    foodSelect.getWidgetChild().click(true);
                }
            }
        }
    }
}