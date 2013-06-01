package com.rsbot.roguescooker.Nodes;

import com.rsbot.roguescooker.Utils.Methods;
import com.rsbot.roguescooker.Variables.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.methods.Calculations;
import org.powerbot.core.script.methods.Players;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.client.RSNPC;

/**
 * Created with IntelliJ IDEA.
 * User: Romi Grace
 * Date: 01/06/13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class Banking extends Node {
    final NPC theBanker = NPCs.getNearest("Banker");

    public boolean activate() {
        return Variables.guiDisposed
                && !Methods.hasItems(Variables.foodID);
    }

    public void execute() {
        if (theBanker != null) {
            if (!theBanker.isOnScreen()) Camera.turnTo(theBanker);

            if (Calculations.distanceTo(theBanker.getLocation().getX(), theBanker.getLocation().getY()) > 10) {
                Variables.Status = ("Moving to Banker");
                Walking.walk(theBanker.getLocation());

                if (Players.getLocal().isMoving()) {
                    Sleeping.sleep(200, 400);
                }
            }
        }

        if (theBanker != null) {
            if (theBanker.isOnScreen()) {
                if (!Bank.isOpen()) {
                    Variables.Status = ("Interacting with " + theBanker.getName().toString());
                    theBanker.interact("Bank");
                }
            }

            if (Bank.isOpen()) {
                if (Inventory.getCount() > 0) {
                   if(Bank.depositInventory()) {
                       Variables.Status = ("Depositing Inventory");
                   }
                }

                if (Bank.getItemCount(Variables.foodID) > 0 && Inventory.getCount() < 1) {
                    while (Inventory.getCount(Variables.foodID) < 1) {
                        if(Bank.withdraw(Variables.foodID, Bank.Amount.ALL)) {
                            Variables.Status = ("Withdrawing Food!");
                        }
                        sleep(200, 300);
                    }
                }

                if(Methods.hasItems(Variables.foodID)) {
                   if(Bank.close()) {
                       Variables.Status = ("Closing Bank");
                   }
                }
            }
        }
    }
}