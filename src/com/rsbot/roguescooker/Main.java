package com.rsbot.roguescooker;

import com.rsbot.roguescooker.Nodes.Banking;
import com.rsbot.roguescooker.Nodes.Cooking;
import com.rsbot.roguescooker.Nodes.Sleeping;
import com.rsbot.roguescooker.Utils.GUI;
import com.rsbot.roguescooker.Variables.Constants;
import com.rsbot.roguescooker.Variables.Variables;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Random;

import javax.swing.*;
import java.util.LinkedList;
import java.awt.*;

@Manifest(name = "RoguesDenCooker", description = "Cooks all types of food at Rogues Den",
        version = 0.1, authors = { "Gandalf The Grey" })
public class Main extends ActiveScript implements PaintListener {
    LinkedList<Node> NODES = new LinkedList<Node>();

    public void onStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ui = new GUI();
                ui.setVisible(true);
            }
        });

        Mouse.setSpeed(Variables.USE);
        Variables.startTime = System.currentTimeMillis();
        Variables.startExperience = Variables.getStartExperience();
        Variables.startLevel = Variables.getStartLevel();

        provide(new Banking());
        provide(new Cooking());
        provide(new Sleeping());
    }

    public int loop() {
        if (Game.isLoggedIn()) {
            for (Node node : NODES) {
                if (node != null && node.activate())
                    node.execute();
            }
        }
        return Random.nextInt(250, 500);
    }

    private void provide(Node node) {
        if (node != null)
            NODES.add(node);
    }

    public void onRepaint(Graphics g) {
        g.drawString("Experience Gained: " + Variables.getExperienceGained(), 20, 20);
        g.drawString("Levels Gained: " + Variables.getLevelsGained(), 20, 20);
        g.drawString("Experience Per Hour: " + Variables.getExperiencePerHour(), 20, 20);
        g.drawString("Food Cooked Per Hour: " + Variables.getItemsCookedPerHour(), 20, 20);
    }
}