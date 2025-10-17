package BattleMechanics;

import Hero.*;
import Boss.*;

import java.util.*;

public class BattleMechanic {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();
    static private int origHp;
    static private int origMana;
    static private int enemyOrigHp;
    static private int enemyOrigMana;

    public void fight(Hero player, Boss enemy){
        setOriginalStats(player, enemy);

        while(player.getHp() > 0 && enemy.getHp() > 0){
            // Player's turn
            System.out.println("\nPlayer HP: " + player.getHp() + " | Player Mana: " + player.getMana() + " || Enemy HP: " + enemy.getHp() + " | Enemy Mana: " + enemy.getMana());
            System.out.println("+--------------------------------------------------------------------------+");
            System.out.println("Choose your attack:");
            System.out.println();
            System.out.println("1. Basic Attack");
            System.out.println("2. Skill 1 - "+ player.getSkill1() + " (Mana Cost: " + player.getManaCostSkill1() + ") (Cooldown: " + player.getCooldown1() + ")");
            System.out.println("3. Skill 2 - "+ player.getSkill2() + " (Mana Cost: " + player.getManaCostSkill2() + ") (Cooldown: " + player.getCooldown2() + ")");
            System.out.println("4. Ultimate - "+ player.getUltimate() + " (Mana Cost: " + player.getManaCostUltimate() + ") (Cooldown: " + player.getCooldownU() + ")");
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

                restoreStats(player, enemy);
                return;
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

                restoreStats(player, enemy);
                return;
            }
        }
    }

    public static boolean castAttack (Hero player, Boss enemy, int choice) {
        int damage = 0;
        System.out.println("Player's Turn:");

        switch (choice) {
            case 1:
                damage = player.basicAttack() - enemy.getDefense()/2;
                System.out.println(player.getName() + " used Basic Attack!");
                System.out.println("Basic Attack deals " + damage + " damage!");
                break;

            case 2:
                if (player.getMana() < player.getManaCostSkill1()) {
                    System.out.println("Not enough mana!");
                    return false;
                }
                if (player.getCooldown1() == 0) {
                    damage = player.skill1() - enemy.getDefense()/2;
                    System.out.println(player.getName() + " used " + player.getSkill1() + "!");
                    System.out.println(player.getSkill1() + " deals " + damage + " damage!");
                } else {
                    System.out.println("Skill 1 (" + player.getSkill1() + ") is on cooldown for " + player.getCooldown1() + " more turn(s).");
                    return false;
                }
                break;

            case 3:
                if (player.getMana() < player.getManaCostSkill2()) {
                    System.out.println("Not enough mana!");
                    return false;
                }
                if (player.getCooldown2() == 0) {
                    damage = player.skill2() - enemy.getDefense()/2;
                    System.out.println(player.getName() + " used " + player.getSkill2() + "!");
                    System.out.println(player.getSkill2() + " deals " + damage + " damage!");
                } else {
                    System.out.println("Skill 2 (" + player.getSkill2() + ") is on cooldown for " + player.getCooldown2() + " more turn(s).");
                    return false;
                }
                break;

            case 4:
                if (player.getMana() < player.getManaCostUltimate()) {
                    System.out.println("Not enough mana!");
                    return false;
                }
                if (player.getCooldownU() == 0) {
                    damage = player.ultimate() - enemy.getDefense()/2;
                    System.out.println(player.getName() + " used " + player.getUltimate() + "!");
                    System.out.println(player.getUltimate() + " deals " + damage + " damage!");
                } else {
                    System.out.println("Ultimate (" + player.getUltimate() + ") is on cooldown for " + player.getCooldownU() + " more turn(s).");
                    return false;
                }
                break;

            default:
                System.out.println("Invalid choice. Please select a valid attack.");
                return false;
        }

        // Apply damage
        enemy.setHp(enemy.getHp() - damage);

        return true; // valid attack
    }

    public static void enemyCastAttack(Hero player, Boss enemy) { 
        System.out.println("Enemy's turn:");

        while (true) { // loop until enemy does a valid action
            int choice = rand.nextInt(1, 6); // Random choice between 1 and 5

            switch (choice) {
                case 1:
                    // Basic attack is always valid
                    enemy.basicAttack(player);
                    break;

                case 2:
                    if (enemy.getMana() >= enemy.getManaCostSkill1() && enemy.getCooldown1() == 0) {
                        enemy.skill1(player);
                    } else {
                        continue; // try another choice
                    }
                    break;

                case 3:
                    if (enemy.getMana() >= enemy.getManaCostSkill2() && enemy.getCooldown2() == 0) {
                        enemy.skill2(player);
                    } else {
                        continue;
                    }
                    break;

                case 4:
                    if (enemy.getMana() >= enemy.getManaCostSkill3() && enemy.getCooldown3() == 0) {
                        enemy.skill3(player);
                    } else {
                        continue;
                    }
                    break;

                case 5:
                    if (enemy.getMana() >= enemy.getManaCostUltimate() && enemy.getCooldownU() == 0) {
                        enemy.ultimate(player);
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
  
    public static void reduceEnemyCooldown(Boss enemy){
        if (enemy.getCooldown1() > 0) enemy.setCooldown1(enemy.getCooldown1() - 1);
        if (enemy.getCooldown2() > 0) enemy.setCooldown2(enemy.getCooldown2() - 1);
        if (enemy.getCooldown3() > 0) enemy.setCooldown3(enemy.getCooldown3() - 1);
        if (enemy.getCooldownU() > 0) enemy.setCooldownU(enemy.getCooldownU() - 1);
    }

    public static void setOriginalStats(Hero player, Boss enemy) {
        origHp = player.getHp();
        origMana = player.getMana();
        enemyOrigHp = enemy.getHp();
        enemyOrigMana = enemy.getMana();
        player.setManaCap(player.getMana());
        enemy.setManaCap(enemy.getMana());
    }

    public static void restoreStats(Hero player, Boss enemy){
        player.setHp(origHp);
        player.setMana(origMana);
        player.setCooldown1(0);
        player.setCooldown2(0);
        player.setCooldownU(0);

        enemy.setHp(enemyOrigHp);
        enemy.setMana(enemyOrigMana);
        enemy.setCooldown1(0);
        enemy.setCooldown2(0);
        enemy.setCooldown3(0);
        enemy.setCooldownU(0);
    }
}