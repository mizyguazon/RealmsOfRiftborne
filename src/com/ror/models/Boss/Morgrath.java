package com.ror.models.Boss;

import java.text.DecimalFormat;
import com.ror.models.*;

public class Morgrath extends Boss {
    private int skillCd1, skillCd2, skillCd3, skillCdU;
    DecimalFormat df = new DecimalFormat("#,##0");

    public Morgrath() {
        super(18500,   
            900,     
            2500,   
            420,    
            95,     
            "Morgrath",
            "Swamp Maw", "Pestilent Ooze", "Putrid Renewal", "Crown of the Mire",
            275, 360, 425, 800
        );

        this.skillCd1 = 5;
        this.skillCd2 = 7;
        this.skillCd3 = 8;
        this.skillCdU = 10;
    }

    @Override public void basicAttack(Entity enemy, Hero hero) { 
        System.out.println(getName() + " used Basic Attack!"); 

        double damage = getAttack() * 1.1; 
        double manaRecovery = getManaCap() * 0.2; 
        
        if(manaRecovery+getMana() > getManaCap()){ 
            setMana(getManaCap()); 
        } else { 
            int addMana = (int) manaRecovery + getMana(); setMana(addMana); 
        } 
        
        if(hero.dodgeEnemyAtk(hero, enemy)) return; 
        
        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2; 
        
        System.out.println("Basic Attack deals " + df.format(damageDealt) + " damage!"); 
        
        hero.setHp(hero.getHp() - damageDealt); 
    }

    @Override
    public void skill1(Entity enemy, Hero hero) {
        System.out.println(getName() + " used " + getSkill1() + "!");
        setCooldown1(skillCd1);
        setMana(getMana() - getManaCostSkill1());

        if (hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.5;
        int damageDealt = (int)Math.round(damage) - hero.getDefense() / 2;
        System.out.println(getSkill1() + " deals " + df.format(damageDealt) + " damage!");
        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Entity enemy, Hero hero) {
        System.out.println(getName() + " used " + getSkill2() + "!");
        setCooldown2(skillCd2);
        setMana(getMana() - getManaCostSkill2());

        if (hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.8;
        int damageDealt = (int)Math.round(damage) - hero.getDefense() / 2;
        hero.setHp(hero.getHp() - damageDealt);
        System.out.println(getSkill2() + " deals " + df.format(damageDealt) + " damage!");

        int prevPoison = hero.getPoison();
        hero.setPoison(prevPoison + 3); // stack 3 turns of poison
        int totalPoison = hero.getPoison();

        if (prevPoison > 0) {
            System.out.println("Poison intensifies! (" + hero.getName() + " now has Poison " + totalPoison + ")");
        } else {
            System.out.println(hero.getName() + " is poisoned! (Poison 3)");
            System.out.println(hero.getName() + " will take poison damage over time!");
        }

    }

    @Override
    public void skill3(Entity enemy, Hero hero) {
        System.out.println(getName() + " used " + getSkill3() + "!");
        setCooldown3(skillCd3);
        setMana(getMana() - getManaCostSkill3());

        int healAmount = (int)(getHpCap() * 0.25);
        if(healAmount + getHp() >= getHpCap()){
            setHp(getHpCap());
        } else {
            int newHp = Math.min(getHp() + healAmount, getHpCap());
            setHp(newHp);
        }

        System.out.println(getName() + " heals " + df.format(healAmount) + " HP!");
    }

    @Override
    public void ultimate(Entity enemy, Hero hero) {
        System.out.println(getName() + " used " + getUltimate() + "!");
        setCooldownU(skillCdU);
        setMana(getMana() - getManaCostUltimate());

        double damage = getAttack() * 2.4;
        int damageDealt = (int)Math.round(damage) - hero.getDefense() / 2;
        hero.setHp(hero.getHp() - damageDealt);
        System.out.println(getUltimate() + " deals " + df.format(damageDealt) + " damage!");

        int prevPoison = hero.getPoison();
        hero.setPoison(prevPoison + 5); // stack another 5 turns
        int totalPoison = hero.getPoison();

        if (prevPoison > 0) {
            System.out.println("The toxic corruption deepens! (" + hero.getName() + " now has Poison " + totalPoison + ")");
        } else {
            System.out.println(hero.getName() + " is engulfed in toxic mists! (Poison 5)");
            System.out.println(hero.getName() + " will take poison damage over time!");
        }
    }
}
