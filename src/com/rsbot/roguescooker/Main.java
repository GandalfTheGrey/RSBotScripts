package com.rsbot.roguescooker;

import com.rsbot.roguescooker.Nodes.*;
import com.rsbot.roguescooker.Utils.GUI;
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

        while(!Game.isLoggedIn()) {
            Sleeping.sleep(20,30);
        }

        Mouse.setSpeed(Variables.USE);
        Variables.startTime = System.currentTimeMillis();
        Variables.startExperience = Variables.getStartExperience();
        Variables.startLevel = Variables.getStartLevel();

        provide(new Banking());
        provide(new Cooking());
        provide(new Sleeping());
        provide(new SelectFood());
        provide(new UseFire());
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

    private final Color color1 = new Color(75, 74, 74, 112);
    private final Color color2 = new Color(8, 7, 7);
    private final Color color3 = new Color(12, 11, 11);
    private final BasicStroke stroke1 = new BasicStroke(1);
    private final Font font1 = new Font("Segoe UI", 0, 14);
    private final Font font2 = new Font("Segoe UI", 0, 11);

    public void onRepaint(Graphics g) {
        g.setColor(color1);
        g.fillRoundRect(6, 6, 167, 237, 16, 16);
        g.setColor(color2);
        g.drawRoundRect(6, 6, 167, 237, 16, 16);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("RoguesDenCooker", 27, 26);
        g.setFont(font2);
        g.drawString("Time Running: " + (System.currentTimeMillis() - Variables.startTime), 10, 45);
        g.drawString("Status: " + Variables.Status, 10, 60);
        g.drawString("Experience Gained: " + Variables.getExperienceGained(), 10, 75);
        g.drawString("Experience Gained /H: " + Variables.getExperiencePerHour(), 10, 90);
        g.drawString("Levels Gained: " + Variables.getLevelsGained(), 10, 105);
        g.drawString("Levels Gained /H: " + Variables.getLevelsGainedPerHour(), 10, 120);
        g.drawString("Fish Cooked: " + Variables.itemsCooked, 10, 135);
        g.drawString("Fish Cooked /H: " + Variables.getItemsCookedPerHour(), 10, 150);
        g.drawString("Raw Fish ID:", 55, 240);
    }
}