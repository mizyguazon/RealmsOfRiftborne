package com.ror.models.Boss;

import java.text.DecimalFormat;
import com.ror.models.*;

public class Azrael extends Boss {
    private int skillCd1, skillCd2, skillCd3, skillCdU;
    DecimalFormat df = new DecimalFormat("#,##0");

    public Azrael() {
        super(40000, 
            2400,  
            5000,  
            500,  
            50,   
            "Azrael - Annihilation of Realms",
            "Eclipse Blades", "Eclipse Bind", "Umbral Rebirth", "Oblivion Embrace",
            350, 550, 600, 1000
        );
        this.skillCd1 = 5;
        this.skillCd2 = 8;
        this.skillCd3 = 3;
        this.skillCdU = 11;
    }

    @Override
    public void basicAttack(Entity enemy, Hero hero) {
        if(!getRevive()){
            if(getHp() < getHpCap() * 0.3) {
                skill3(enemy, hero);
                return;
            }
        }
        System.out.println(getName() + " used Basic Attack!");

        double damage = getAttack() * 1.05;
        double manaRecovery = getManaCap() * 0.15;

        // Mana gain
        setMana((int)Math.min(getManaCap(), getMana() + manaRecovery));

        if (hero.dodgeEnemyAtk(hero, enemy)) return;

        int damageDealt = (int)Math.max(500, Math.round(damage) - hero.getDefense()/2);

        System.out.println("Basic Attack deals " + df.format(damageDealt) + " damage!");
        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill1(Entity enemy, Hero hero) {
        if(!getRevive()){
            if(getHp() < getHpCap() * 0.3) {
                skill3(enemy, hero);
                return;
            }
        }
        System.out.println(getName() + " used " + getSkill1() + "!");

        setCooldown1(skillCd1);
        double damage = getAttack() * 1.4;
        setMana(getMana() - getManaCostSkill1());

        if (hero.dodgeEnemyAtk(hero, enemy)) return;

        int damageDealt = (int)Math.max(700, Math.round(damage) - hero.getDefense()/2);
        System.out.println(getSkill1() + " slashes through darkness, dealing " + df.format(damageDealt) + " damage!");

        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Entity enemy, Hero hero) {
        if(!getRevive()){
            if(getHp() < getHpCap() * 0.3) {
                skill3(enemy, hero);
                return;
            }
        }
        System.out.println(getName() + " used " + getSkill2() + "!");

        setCooldown2(skillCd2);
        double damage = getAttack() * 1.6;
        setMana(getMana() - getManaCostSkill2());

        if (hero.dodgeEnemyAtk(hero, enemy)) return;

        int damageDealt = (int)Math.max(1000, Math.round(damage) - hero.getDefense()/2);
        System.out.println(getSkill2() + " binds the hero in shadows, dealing " + df.format(damageDealt) + " damage!");

        // 25% chance to stun
        if (Math.random() < 0.25) {
            if (hero.getStunned() > 0) {
                System.out.println("Dark energy intensifies your paralysis! (Stun refreshed to 1 turn)");
            } else {
                System.out.println("You are binded by darkness! (Stun 1)");
            }
            hero.setStun(1);
        }

        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill3(Entity enemy, Hero hero) {
        System.out.println(getName() + " used " + getSkill3() + "!");

        setCooldown3(skillCd3);
        setMana(getMana() - getManaCostSkill3());
        
        if(!getRevive()){
            if(getHp() < getHpCap() * 0.3) {
                setRebirth(true);
                setRevive(true); // this is to allow only 1 rebirth per fight
            } else {
                int healAmount = (int) (getHpCap() * 0.1);
                setHp(Math.min(getHpCap(), getHp() + healAmount));
                System.out.println(getName() + " regenerates " + df.format(healAmount) + " HP!");
                return;
            }
        }

        if(getRebirth()){
            // Heal Azrael by max
            int healAmount = getHpCap();
            setHp(getHpCap());
            System.out.println(getName() + " has been reborn and restores " + df.format(healAmount) + " HP!");
            System.out.println(getName() + " is exhausted and regaining his strength ATTACK NOW!!!");
            setDisabled(5);
            setCooldown3(7);
            setSkill3("Umbral Chaos");
            setRebirth(false);
        } else {
            // after heal turns into offensive attack
            double damage = getAttack() * 1.3;

            if (hero.dodgeEnemyAtk(hero, enemy)) return;

            int damageDealt = (int)Math.max(800, Math.round(damage) - hero.getDefense()/2);
            System.out.println(getSkill3() + " engulfs your mind in chaos, dealing " + df.format(damageDealt) + " damage!");
            hero.setHp(hero.getHp() - damageDealt);
        }
    }

    @Override
    public void ultimate(Entity enemy, Hero hero) {
        if(!getRevive()){
            if(getHp() < getHpCap() * 0.3) {
                skill3(enemy, hero);
                return;
            }
        }
        System.out.println(getName() + " used " + getUltimate() + "!");
        setCooldownU(skillCdU);
        setMana(getMana() - getManaCostUltimate());

        double damage = getAttack() * 2.2;
        int damageDealt = (int)Math.max(1500, Math.round(damage) - hero.getDefense()/2);

        System.out.println(getUltimate() + " unleashes pure annihilation, dealing " + df.format(damageDealt) + " damage!");

        if (Math.random() < 0.5) {
            if (hero.getStunned() > 0) {
                System.out.println("Oblivion deepens your torment! (Stun refreshed to 2 turns)");
            } else {
                System.out.println("You are overwhelmed by oblivion! (Stun 2)");
            }
            hero.setStun(2);
        }

        hero.setHp(hero.getHp() - damageDealt);
    }
}
