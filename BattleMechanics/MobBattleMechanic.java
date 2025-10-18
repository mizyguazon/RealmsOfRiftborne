package BattleMechanics;

import Hero.*;

import java.util.*;
// This only works for mob
public class MobBattleMechanic {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();
    static private int origHp;
    static private int origMana;

    public boolean fight(Hero player, Entity enemy){
        enemy.setManaCap(enemy.getMana());
        setOriginalStats(player);

        while(player.getHp() > 0 && enemy.getHp() > 0){
            // Player's turn
            System.out.println("\nPlayer HP: " + player.getHp() + " | Player Mana: " + player.getMana() + " || Enemy HP: " + enemy.getHp() + " | Enemy Mana: " + enemy.getMana());
            System.out.println("+--------------------------------------------------------------------------+");
            System.out.println("Choose your attack:");
            System.out.println();
            System.out.println("1. Basic Attack");
            System.out.println("2. Skill 1 - "+ player.getSkill1() + " (Mana Cost: " + player.scaledCost(player.getManaCostSkill1()) + ") (Cooldown: " + player.getCooldown1() + ")");
            System.out.println("3. Skill 2 - "+ player.getSkill2() + " (Mana Cost: " + player.scaledCost(player.getManaCostSkill2()) + ") (Cooldown: " + player.getCooldown2() + ")");
            System.out.println("4. Ultimate - "+ player.getUltimate() + " (Mana Cost: " + player.scaledCost(player.getManaCostUltimate()) + ") (Cooldown: " + player.getCooldownU() + ")");
            System.out.println("+--------------------------------------------------------------------------+");
            System.out.print(">>> ");
            int choice = scanner.nextInt();

            System.out.println();

            boolean valid = castAttack(player, enemy, choice);

            if (!valid) {
                // Skip enemy turn, let player retry instead
                continue;
            }

            reducePlayerCooldown(player);
            
            // Check if enemy is defeated
            if(enemy.getHp() <= 0){
                System.out.println();
                System.out.println("┌────────────────────────────────────────┐");
                System.out.println("│        Enemy have been defeated!       │");
                System.out.println("└────────────────────────────────────────┘");
                System.out.println();

                restoreStats(player);
                return true;
            }

            System.out.println();

            // Enemy's turn
            enemyCastAttack(player, enemy);
            
            reduceEnemyCooldown(enemy);

            // Check if player is defeated
            if(player.getHp() <= 0){
                System.out.println();
                System.out.println("┌────────────────────────────────────────┐");
                System.out.println("│        You have been defeated!         │");
                System.out.println("└────────────────────────────────────────┘");
                System.out.println();

                restoreStats(player);
            }
        }
        return false;
    }

    public static boolean castAttack (Hero player, Entity enemy, int choice) {
        System.out.println("Player's Turn:");

        switch (choice) {
            case 1:
                player.basicAttack(player, enemy);
                break;

            case 2:
                if (player.getMana() < player.scaledCost(player.getManaCostSkill1())) {
                    System.out.println("Not enough mana!");
                    return false;
                }
                if (player.getCooldown1() == 0) {
                    player.skill1(player, enemy);
                } else {
                    System.out.println("Skill 1 (" + player.getSkill1() + ") is on cooldown for " + player.getCooldown1() + " more turn(s).");
                    return false;
                }
                break;

            case 3:
                if (player.getMana() < player.scaledCost(player.getManaCostSkill2())) {
                    System.out.println("Not enough mana!");
                    return false;
                }
                if (player.getCooldown2() == 0) {
                    player.skill2(player, enemy);
                } else {
                    System.out.println("Skill 2 (" + player.getSkill2() + ") is on cooldown for " + player.getCooldown2() + " more turn(s).");
                    return false;
                }
                break;

            case 4:
                if (player.getMana() < player.scaledCost(player.getManaCostUltimate())) {
                    System.out.println("Not enough mana!");
                    return false;
                }
                if (player.getCooldownU() == 0) {
                    player.ultimate(player, enemy);
                } else {
                    System.out.println("Ultimate (" + player.getUltimate() + ") is on cooldown for " + player.getCooldownU() + " more turn(s).");
                    return false;
                }
                break;

            default:
                System.out.println("Invalid choice. Please select a valid attack.");
                return false;
        }

        return true; // valid attack
    }

    public static void enemyCastAttack(Hero player, Entity enemy) { 
        System.out.println("Enemy's turn:");

        while (true) { // loop until enemy does a valid action
            int choice = rand.nextInt(1, 4); // Random choice between 1 and 3

            switch (choice) {
                case 1:
                    // Basic attack is always valid
                    enemy.basicAttack(enemy, player);
                    break;

                case 2:
                    if (enemy.getMana() >= enemy.getManaCostSkill1() && enemy.getCooldown1() == 0) {
                        enemy.skill1(enemy, player);
                    } else {
                        continue; // try another choice
                    }
                    break;

                case 3:
                    if (enemy.getMana() >= enemy.getManaCostSkill2() && enemy.getCooldown2() == 0) {
                        enemy.skill2(enemy, player);
                    } else {
                        continue;
                    }
                    break;

            }

            // If we reached here → attack was valid
            break;
        }
    }

    public static void reducePlayerCooldown(Hero player){
        if (player.getCooldown1() > 0) player.setCooldown1(player.getCooldown1() - 1);
        if (player.getCooldown2() > 0) player.setCooldown2(player.getCooldown2() - 1);
        if (player.getCooldownU() > 0) player.setCooldownU(player.getCooldownU() - 1);
    }
  
    public static void reduceEnemyCooldown(Entity enemy){
        if (enemy.getCooldown1() > 0) enemy.setCooldown1(enemy.getCooldown1() - 1);
        if (enemy.getCooldown2() > 0) enemy.setCooldown2(enemy.getCooldown2() - 1);
    }

    public static void setOriginalStats(Hero player) {
        origHp = player.getHp();
        origMana = player.getMana();
        player.setManaCap(player.getMana());
    }

    public static void restoreStats(Hero player){
        player.setHp(origHp);
        player.setMana(origMana);
        player.setCooldown1(0);
        player.setCooldown2(0);
        player.setCooldownU(0);
    }
}