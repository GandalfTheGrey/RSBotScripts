package com.rsbot.roguescooker.enums;

public class FoodTypes {

    public static enum Foods {
        Shrimp(317), Anchovies(321), Sardine(327), Herring(345), Mackerel(353),
        Trout(335), Cod(341), Pike(349), Salmon(331), Tuna(359), Lobster(337),
        Bass(363), Swordfish(371), Monkfish(7944), Shark(383), Turtle(395),
        MantaRay(389), Rocktail(15270);

        final int id;

        Foods(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }
    }
}
