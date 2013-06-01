package com.rsbot.roguescooker.Variables;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;

/**
 * Created with IntelliJ IDEA.
 * User: Romi Grace
 * Date: 01/06/13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class Variables {
    public static boolean guiDisposed;
    public static long startTime = System.currentTimeMillis();
    public static String Status;

    public static Mouse.Speed USE;
    public static Mouse.Speed VERY_FAST = Mouse.Speed.VERY_FAST;
    public static Mouse.Speed FAST = Mouse.Speed.FAST;
    public static Mouse.Speed NORMAL = Mouse.Speed.NORMAL;

    public static int foodID;
    public static int startExperience;
    public static int startLevel;
    public static int itemsCooked;

    public static int getStartExperience() {
        return Skills.getExperience(Skills.COOKING);
    }

    public static int getStartLevel() {
        return Skills.getLevel(Skills.COOKING);
    }

    public static int getCurrentExperience() {
        return Skills.getExperience(Skills.COOKING);
    }

    public static int getCurrentLevel() {
        return Skills.getLevel(Skills.COOKING);
    }

    public static int getExperienceGained() {
        return getCurrentExperience() - startExperience;
    }

    public static int getLevelsGained() {
        return getCurrentLevel() - startLevel;
    }

   public static int getLevelsGainedPerHour() {
       return (int) ((getLevelsGained() * 3600000D) / (System.currentTimeMillis() - startTime));
   }
    public static int getExperiencePerHour() {
        return (int) ((getExperienceGained() * 3600000D) / (System.currentTimeMillis() - startTime));
    }

    public static int getItemsCookedPerHour() {
        return (int) ((itemsCooked * 3600000D) / (System.currentTimeMillis() - startTime));
    }
}