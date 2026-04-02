package com.ror.models.training;

import com.ror.models.*;
import com.ror.engine.design.*;
import java.util.Random;

public class StatProgress {

    Random random = new Random();

    public void endurance(Hero hero){
        double enduranceMultiplier = random.nextDouble(1.2, 1.5); 
        int newHp = (int) (200 * enduranceMultiplier);
        hero.addBonusHp(newHp);  // add bonus + apply immediately
    }

    public void strength(Hero hero){
        double attackMultiplier = random.nextDouble(1.2, 1.5); 
        int newAttack = (int) (100 * attackMultiplier);
        hero.addBonusAtk(newAttack);
    }

    public void durability(Hero hero){
        double defenseMultiplier = random.nextDouble(1.2, 1.5); 
        int newDefense = (int) (50 * defenseMultiplier);
        hero.addBonusDef(newDefense);
    }

    public void mana(Hero hero){
        double manaMultiplier = random.nextDouble(1.2, 1.5); 
        int newMana = (int) (150 * manaMultiplier);
        hero.addBonusMana(newMana);
    }
    
    public void displayXPandLevel(Hero hero, int xpReward) {

        hero.levelUp(xpReward);
        checkHpValidation(hero);
    }

    public void checkHpValidation(Hero hero){

        if(!hero.canEnterArea1()){
            if(hero.hasFinishedAllTraining()){
                System.out.println();
                System.out.println();
                System.out.println("     ┌──────────────────────────────────────────────────────────────┐");
                System.out.println("     │     You meet the requirements for The Forest of Reverie      │");
                System.out.println("     │   Visit the Principal's Office to get your permission slip   │");
                System.out.println("     └──────────────────────────────────────────────────────────────┘");
                System.out.println();
                System.out.println();

            }
            

        } else if (!hero.canEnterArea2()){
            if(hero.canEnterArea1() && hero.getHaveDefeatedArea1Boss()) {
                System.out.println();
                System.out.println();
                System.out.println("     ┌─────────────────────────────────────────────────────────────┐");
                System.out.println("     │     You meet the requirements for The Reverie\'s Edge       │");
                System.out.println("     │   Visit the Principal's Office to get your permission slip  │");
                System.out.println("     └─────────────────────────────────────────────────────────────┘");
                System.out.println();
                System.out.println();
            }
            

        } else if (!hero.canEnterArea3()){
            if(hero.canEnterArea3() && hero.getHaveDefeatedArea2Boss()) {
                System.out.println();
                System.out.println();
                System.out.println("     ┌──────────────────────────────────────────────────────────────┐");
                System.out.println("     │      You meet the requirements for The Forsaken Lands        │");
                System.out.println("     │   Visit the Principal's Office to get your permission slip   │");
                System.out.println("     └──────────────────────────────────────────────────────────────┘");
                System.out.println();
                System.out.println();
            }
            
        } 
    }

    public void currencyProgress(Hero hero) {
        Stats statsHandler = new Stats();

        int gold = 2500;
        int xp = 500; 

        statsHandler.sackOfGoldAfterTraining(hero, gold, xp);

    }

}
