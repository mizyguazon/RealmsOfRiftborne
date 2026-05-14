package com.ror.utils;

import com.ror.models.*;
import com.ror.models.training.StatProgress;
import java.util.Random;

public class StatsProgressLibrary extends StatProgress {

    private static Random random = new Random();

    public void randomStatsProgress(Hero hero){
        int whatStat = random.nextInt(4) + 1;

        switch(whatStat){
            case 1:
                
                endurance(hero);
                displayXPandLevel(hero, 100);

                break;

            case 2:
                
                durability(hero);
                displayXPandLevel(hero, 100);
                break;

            case 3:
                
                strength(hero);
                displayXPandLevel(hero, 100);
                break;

            case 4:

                mana(hero);
                displayXPandLevel(hero, 100);
                break;
        }
    }
}
