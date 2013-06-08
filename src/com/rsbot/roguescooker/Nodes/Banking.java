package com.rsbot.roguescooker.nodes;

import com.rsbot.roguescooker.utils.Methods;
import com.rsbot.roguescooker.vars.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Banking extends Node {
    final NPC theBanker = NPCs.getNearest(14707);

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
                    Variables.Status = ("Interacting with " + theBanker.getName());
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