package com.rsbot.roguescooker.Nodes;

import com.rsbot.roguescooker.Utils.Methods;
import com.rsbot.roguescooker.Variables.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Romi Grace
 * Date: 01/06/13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class Cooking extends Node {
    final SceneObject fire = SceneEntities.getNearest(100);

    public boolean activate() {
        return Variables.guiDisposed
                && Methods.hasItems(Variables.foodID)
                && !Methods.isCooking();
    }

    public void execute() {
        if(fire != null) {
            if(fire.isOnScreen()) {
                if(Methods.hasSelectedFood()) {
                    if(fire.click(true)) {
                        Variables.Status = ("Using Food on Fire");
                    }
                }
            }
        }
    }
}