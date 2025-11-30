package Library;

import Hero.*;
import TrainingGround.StatProgress;
import java.util.Random;

public class StatsProgressLibrary extends StatProgress {

    private static Random random = new Random();

    public void randomStatsProgress(Hero hero){
        int whatStat = random.nextInt(4) + 1;

        switch(whatStat){
            case 1:
                /*System.out.println();
                System.out.println("┌────────────────────────────────────────────┐");
                System.out.println("│   + Endurance Related Stats Increased! +   │");
                System.out.println("└────────────────────────────────────────────┘");
                */
                endurance(hero);
                displayXPandLevel(hero, 500);

                break;

            case 2:
                /*System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│   + Durability Related Stats Increased! +   │");
                System.out.println("└─────────────────────────────────────────────┘");
                */
                durability(hero);
                displayXPandLevel(hero, 500);
                break;

            case 3:
                /*System.out.println();
                System.out.println("┌───────────────────────────────────────────┐");
                System.out.println("│   + Strength Related Stats Increased! +   │");
                System.out.println("└───────────────────────────────────────────┘");
                */
                strength(hero);
                displayXPandLevel(hero, 500);
                break;

            case 4:
                /*System.out.println();
                System.out.println("┌──────────────────────────────────────────────────┐");
                System.out.println("│   + Mana Refinement Related Stats Increased! +   │");
                System.out.println("└──────────────────────────────────────────────────┘");
                */
                mana(hero);
                displayXPandLevel(hero, 500);
                break;
        }
    }
}