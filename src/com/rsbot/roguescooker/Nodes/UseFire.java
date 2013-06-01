package com.rsbot.roguescooker.Nodes;

import com.rsbot.roguescooker.Utils.Methods;
import com.rsbot.roguescooker.Variables.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.methods.Calculations;
import org.powerbot.core.script.methods.Players;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Romi Grace
 * Date: 01/06/13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class UseFire extends Node {
    final SceneObject fire = SceneEntities.getNearest(100);

    public boolean activate() {
        return Variables.guiDisposed
                && Methods.hasSelectedFood()
                && Methods.hasItems(Variables.foodID)
                && !Methods.isCooking();
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
                if (Methods.hasSelectedFood()) {
                    Variables.Status = ("Clicking Fire!");
                    fire.click(true);
                }
            }
        }
    }
}