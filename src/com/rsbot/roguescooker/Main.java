package com.rsbot.roguescooker;

import com.rsbot.roguescooker.nodes.*;
import com.rsbot.roguescooker.utils.GUI;
import com.rsbot.roguescooker.vars.Variables;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Environment;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Random;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.awt.*;

@Manifest(name = "RoguesDenCooker", description = "Cooks all types of food at Rogues Den",
        version = 0.1, authors = {"Gandalf The Grey"})
public class Main extends ActiveScript implements PaintListener, MessageListener {
    LinkedList<Node> NODES = new LinkedList<Node>();

    @Override
    public void onStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI g = new GUI();
                g.setVisible(true);
            }
        });
    }

    public void onStop() {
        if ((System.currentTimeMillis() - Variables.startTime) > 1) {
            try {
                URL submit = new URL(
                        "http://infisoft.org.uk/cooker/input.php?username=" + Environment.getDisplayName() + "&runtime=" + (System.currentTimeMillis() - Variables.startTime) + "&var1=" + Variables.foodCooked + "&var2=" + Variables.getLevelsGained() + "&var3=" + Variables.getExperienceGained() + "&var4=" + Variables.foodburned);
                URLConnection con = submit.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                final BufferedReader rd = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                rd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public int loop() {
        if (Variables.guiDisposed && Game.isLoggedIn()) {
            if (NODES.size() > 0) {
                for (Node node : NODES) {
                    if (node != null && node.activate())
                        node.execute();
                }
            } else {
                Mouse.setSpeed(Variables.USE);
                Variables.startTime = System.currentTimeMillis();
                Variables.startExperience = Variables.getStartExperience();
                Variables.startLevel = Variables.getStartLevel();
                provide(new Banking());
                provide(new Sleeping());
                provide(new SelectFood());
                provide(new UseFire());
            }
        }
        return Random.nextInt(250, 500);
    }

    private void provide(Node node) {
        if (node != null)
            NODES.add(node);
    }

    public void messageReceived(MessageEvent msg) {
        if (msg.getMessage().contains("burned")) {
            Variables.foodburned++;
        }

        if (msg.getMessage().contains("cooked")) {
            Variables.foodCooked++;
        }
    }

    private final Color color1 = new Color(75, 74, 74, 112);
    private final Color color2 = new Color(8, 7, 7);
    private final Color color3 = new Color(12, 11, 11);
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
        // g.drawString("Experience Gained /H: " + Variables.getExperiencePerHour(), 10, 90);
        g.drawString("Levels Gained: " + Variables.getLevelsGained(), 10, 105);
        // g.drawString("Levels Gained /H: " + Variables.getLevelsGainedPerHour(), 10, 120);
        g.drawString("Fish Cooked: " + Variables.foodCooked, 10, 135);
        // g.drawString("Fish Cooked /H: " + Variables.getItemsCookedPerHour(), 10, 150);
        g.drawString("Raw Fish ID:", 55, 240);
    }
}