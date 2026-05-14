package com.ror.models.Inventory;

import java.util.*;
import com.ror.models.*;
import com.ror.engine.design.*;
import com.ror.engine.narration.*;
import java.text.DecimalFormat;

public class Inventory {
    Scanner sc = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,##0");
    ShopRelated shopHandler = new ShopRelated();
    Narration separatorHandler = new Narration();

    private int capacity;
    private int smallHealthPotion;
    private int mediumHealthPotion;
    private int largeHealthPotion;
    private int smallManaPotion;
    private int mediumManaPotion;
    private int largeManaPotion;

    public Inventory() {
        this.capacity = 99;
        this.smallHealthPotion = 0;
        this.mediumHealthPotion = 0;
        this.largeHealthPotion = 0;
        this.smallManaPotion = 0;
        this.mediumManaPotion = 0;
        this.largeManaPotion = 0;
    }

    public void inventory() {
        System.out.println();
        System.out.println();

        if (!isEmpty()) {
            separatorHandler.promptSeparatorResized();
            shopHandler.potion();
            System.out.println();
        }

        if (smallHealthPotion > 0)
            System.out.println("[ = ] Small Health Potions: " + smallHealthPotion);
        if (mediumHealthPotion > 0)
            System.out.println("[ = ] Medium Health Potions: " + mediumHealthPotion);
        if (largeHealthPotion > 0)
            System.out.println("[ = ] Large Health Potions: " + largeHealthPotion);

        if (smallManaPotion > 0)
            System.out.println("[ = ] Small Mana Potions: " + smallManaPotion);
        if (mediumManaPotion > 0)
            System.out.println("[ = ] Medium Mana Potions: " + mediumManaPotion);
        if (largeManaPotion > 0)
            System.out.println("[ = ] Large Mana Potions: " + largeManaPotion);

        if (isEmpty()) {
            shopHandler.empty();
            System.out.println();
            System.out.println("┌──────────────────────────────┐");
            System.out.println("│ + Your inventory is empty! + │");
            System.out.println("└──────────────────────────────┘");
        }

        System.out.println();
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│  Press ENTER to continue...  │");
        System.out.println("└──────────────────────────────┘");

        sc.nextLine();

        System.out.println();
        System.out.println();
    }

    public void useItem(Hero hero) {
        if (isEmpty()) {
            shopHandler.empty();
            System.out.println();
            System.out.println("┌──────────────────────────────┐");
            System.out.println("│ + Your inventory is empty! + │");
            System.out.println("└──────────────────────────────┘");

            return;
        }

        String input = "";
        while (!input.equals("0")) {
            while (true) {

                System.out.println("┌─────────────────────────────────┐");
                System.out.println("│ >>>  Select an item to use  <<< │");
                System.out.println("└─────────────────────────────────┘");
                System.out.println();
                try {

                    if (smallHealthPotion > 0)
                        System.out.println("[ SH ] Small Health Potion (" + smallHealthPotion + ")");
                    if (mediumHealthPotion > 0)
                        System.out.println("[ MH ] Medium Health Potion (" + mediumHealthPotion + ")");
                    if (largeHealthPotion > 0)
                        System.out.println("[ LH ] Large Health Potion (" + largeHealthPotion + ")");

                    if (smallManaPotion > 0)
                        System.out.println("[ SM ] Small Mana Potion (" + smallManaPotion + ")");
                    if (mediumManaPotion > 0)
                        System.out.println("[ MM ] Medium Mana Potion (" + mediumManaPotion + ")");
                    if (largeManaPotion > 0)
                        System.out.println("[ LM ] Large Mana Potion (" + largeManaPotion + ")");

                    System.out.println("[ 0 ] Exit");
                    System.out.print("-->| ");
                    input = sc.nextLine().trim().toUpperCase();

                    switch (input) {
                        case "SH":
                            if (smallHealthPotion > 0) {
                                useSmallHealthPotion(hero);
                            } else {
                                System.out.println("┌───────────────────────────────────────────┐");
                                System.out.println("│ + You don't have a Small Health Potion! + │");
                                System.out.println("└───────────────────────────────────────────┘");
                            }
                            break;

                        case "MH":
                            if (mediumHealthPotion > 0) {
                                useMediumHealthPotion(hero);
                            } else {
                                System.out.println("┌────────────────────────────────────────────┐");
                                System.out.println("│ + You don't have a Medium Health Potion! + │");
                                System.out.println("└────────────────────────────────────────────┘");
                            }
                            break;

                        case "LH":
                            if (largeHealthPotion > 0) {
                                useLargeHealthPotion(hero);
                            } else {
                                System.out.println("┌───────────────────────────────────────────┐");
                                System.out.println("│ + You don't have a Large Health Potion! + │");
                                System.out.println("└───────────────────────────────────────────┘");
                            }
                            break;

                        case "SM":
                            if (smallManaPotion > 0) {
                                useSmallManaPotion(hero);
                            } else {
                                System.out.println("┌─────────────────────────────────────────┐");
                                System.out.println("│ + You don't have a Small Mana Potion! + │");
                                System.out.println("└─────────────────────────────────────────┘");
                            }
                            break;

                        case "MM":
                            if (mediumManaPotion > 0) {
                                useMediumManaPotion(hero);
                            } else {
                                System.out.println("┌──────────────────────────────────────────┐");
                                System.out.println("│ + You don't have a Medium Mana Potion! + │");
                                System.out.println("└──────────────────────────────────────────┘");
                            }
                            break;

                        case "LM":
                            if (largeManaPotion > 0) {
                                useLargeManaPotion(hero);
                            } else {
                                System.out.println("┌─────────────────────────────────────────┐");
                                System.out.println("│ + You don't have a Large Mana Potion! + │");
                                System.out.println("└─────────────────────────────────────────┘");
                            }

                            break;

                        case "0":
                            System.out.println("┌──────────────────┐");
                            System.out.println("│  >>> Exited <<<  │");
                            System.out.println("└──────────────────┘");

                            break;

                        default:
                            System.out.println("┌──────────────────────────────────────┐");
                            System.out.println("│   Invalid input! Please try again.   │");
                            System.out.println("└──────────────────────────────────────┘");

                    }

                    break;
                } catch (Exception e) {
                    System.out.println("┌──────────────────────────────────────────┐");
                    System.out.println("│   An error occurred. Please try again.   │");
                    System.out.println("└──────────────────────────────────────────┘");

                    sc.nextLine();
                }
            }
        }
    }

    public boolean isEmpty() {
        return smallHealthPotion == 0 &&
                mediumHealthPotion == 0 &&
                largeHealthPotion == 0 &&
                smallManaPotion == 0 &&
                mediumManaPotion == 0 &&
                largeManaPotion == 0;
    }

    // ==== POTION USE WITH FEEDBACK ====

    // ==== POTION USE WITH SAFE HEALING/RESTORE ====

    private int calculateHeal(int cap, double percent) {
        // Rounds to nearest int and ensures minimum of 1
        return Math.max(1, (int) Math.round(cap * percent));
    }

    public void useSmallHealthPotion(Hero hero) {
        int healAmount = calculateHeal(hero.getHpCap(), 0.20);
        hero.setHp(Math.min(hero.getHp() + healAmount, hero.getHpCap()));
        smallHealthPotion--;

        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│ + You used a Small Health Potion (+20%) + │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.println("│  [ HP : healed ] " + df.format(healAmount) + " -> " + df.format(hero.getHp()));
    }

    public void useMediumHealthPotion(Hero hero) {
        int healAmount = calculateHeal(hero.getHpCap(), 0.45);
        hero.setHp(Math.min(hero.getHp() + healAmount, hero.getHpCap()));
        mediumHealthPotion--;

        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│ + You used a Medium Health Potion (+45%) + │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.println("| [ HP : healed ] " + df.format(healAmount) + " -> " + df.format(hero.getHp()));
    }

    public void useLargeHealthPotion(Hero hero) {
        int healAmount = calculateHeal(hero.getHpCap(), 0.70);
        hero.setHp(Math.min(hero.getHp() + healAmount, hero.getHpCap()));
        largeHealthPotion--;

        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│ + You used a Large Health Potion (+70%) + │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.println("| [ HP : healed ] " + df.format(healAmount) + " -> " + df.format(hero.getHp()));
    }

    public void useSmallManaPotion(Hero hero) {
        int manaAmount = calculateHeal(hero.getManaCap(), 0.20);
        hero.setMana(Math.min(hero.getMana() + manaAmount, hero.getManaCap()));
        smallManaPotion--;

        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│ + You used a Small Mana Potion (+20%) + │");
        System.out.println("└─────────────────────────────────────────┘");
        System.out.println("| [ Mana : restored ] " + df.format(manaAmount) + " -> " + df.format(hero.getMana()));
    }

    public void useMediumManaPotion(Hero hero) {
        int manaAmount = calculateHeal(hero.getManaCap(), 0.45);
        hero.setMana(Math.min(hero.getMana() + manaAmount, hero.getManaCap()));
        mediumManaPotion--;

        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│ + You used a Medium Mana Potion (+45%) + │");
        System.out.println("└──────────────────────────────────────────┘");
        System.out.println("| [ Mana : restored ] " + df.format(manaAmount) + " -> " + df.format(hero.getMana()));
    }

    public void useLargeManaPotion(Hero hero) {
        int manaAmount = calculateHeal(hero.getManaCap(), 0.70);
        hero.setMana(Math.min(hero.getMana() + manaAmount, hero.getManaCap()));
        largeManaPotion--;

        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│ + You used a Large Mana Potion (+70%) + │");
        System.out.println("└─────────────────────────────────────────┘");
        System.out.println("| [ Mana : restored ] " + df.format(manaAmount) + " -> " + df.format(hero.getMana()));
    }

    // ==== SETTERS ====

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSmallHealthPotion(int x) {
        this.smallHealthPotion = Math.min(x, capacity);
    }

    public void setMediumHealthPotion(int x) {
        this.mediumHealthPotion = Math.min(x, capacity);
    }

    public void setLargeHealthPotion(int x) {
        this.largeHealthPotion = Math.min(x, capacity);
    }

    public void setSmallManaPotion(int x) {
        this.smallManaPotion = Math.min(x, capacity);
    }

    public void setMediumManaPotion(int x) {
        this.mediumManaPotion = Math.min(x, capacity);
    }

    public void setLargeManaPotion(int x) {
        this.largeManaPotion = Math.min(x, capacity);
    }

    // ==== GETTERS ====

    public int getCapacity() {
        return capacity;
    }

    public int getSmallHealthPotion() {
        return smallHealthPotion;
    }

    public int getMediumHealthPotion() {
        return mediumHealthPotion;
    }

    public int getLargeHealthPotion() {
        return largeHealthPotion;
    }

    public int getSmallManaPotion() {
        return smallManaPotion;
    }

    public int getMediumManaPotion() {
        return mediumManaPotion;
    }

    public int getLargeManaPotion() {
        return largeManaPotion;
    }
}
