package com.rsbot.roguescooker.nodes;

import com.rsbot.roguescooker.utils.Methods;
import com.rsbot.roguescooker.vars.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class UseFire extends Node {
    final SceneObject fire = SceneEntities.getNearest(2732);

    public boolean activate() {
        return Variables.guiDisposed
                && Methods.hasSelectedFood(Variables.foodID)
                && Methods.hasItems(Variables.foodID)
                && !Methods.isCooking()
                && !Bank.isOpen();
    }

    public void execute() {
        if (fire != null) {
            if (!fire.isOnScreen()) Camera.turnTo(fire.getLocation());
            if (Calculations.distanceTo(fire.getLocation().getX(), fire.getLocation().getY()) > 5)
                Walking.walk(fire.getLocation());

            if (Players.getLocal().isMoving()) {
                Sleeping.sleep(200, 400);
            }
        }

        if (fire != null) {
            if (fire.isOnScreen()) {
                if (Methods.hasSelectedFood(Variables.foodID)) {
                    if (!Methods.cookFood()) {
                        fire.click(true);
                    }
                }
            }
        }

        if (Methods.cookFood()) {
            Methods.COOK_BUTTON.click(true);
        }
    }
}