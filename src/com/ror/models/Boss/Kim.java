package com.ror.models.Boss;

import com.ror.models.*;
import java.text.DecimalFormat;

public class Kim extends Boss {
    private int skillCd1, skillCd2, skillCd3, skillCdU;

    public Kim() {
        super(60000, 3000, 2500, 800, 30, "Kim Morvain - The Vengeful Shadow", "Oblivion Slash", "Abyssal Grasp", "Dark Pulse", "Eternal Shroud", 300, 400, 350, 800);
        
        this.skillCd1 = 6;
        this.skillCd2 = 8;  
        this.skillCd3 = 8;
        this.skillCdU = 12;
    }

    DecimalFormat df = new DecimalFormat("#,##0");

    @Override
    public void basicAttack(Entity enemy, Hero hero) {
        System.out.println(getName() + " used Basic Attack!");

        double damage = getAttack() * 1.1;

        double manaRecovery = getManaCap() * 0.2;

        if(manaRecovery+getMana() > getManaCap()){
            setMana(getManaCap());
        } else {
            int addMana = (int) manaRecovery + getMana();
            setMana(addMana);
        }

        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println("Basic Attack deals " + df.format(damageDealt) + " damage!");

        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill1(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getSkill1() + "!");

        setCooldown1(skillCd1);

        double damage = getAttack() * 1.5;

        int manaReduce = getMana() - getManaCostSkill1();
        setMana(manaReduce);

        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getSkill1() + " deals " + df.format(damageDealt) + " damage!");

        if (Math.random() < 1) {
            if (hero.getStunned() > 0) {
                System.out.println("Kneel, mortal… your resistance ends now. (Stun refreshed to 1 turn)");
            } else {
                System.out.println("Be still before your end. (Stun 1)");
            }
            hero.setStun(1);
        }


        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getSkill2() + "!");

        setCooldown2(skillCd2);
        
        double damage = getAttack() * 1.8;

        int manaReduce = getMana() - getManaCostSkill2();
        setMana(manaReduce);

        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getSkill2() + " deals " + df.format(damageDealt) + " damage!");

        if (Math.random() < 1) {
            if (hero.getStunned() > 0) {
                System.out.println("Struggle all you want. It changes nothing. (Stun refreshed to 1 turn)");
            } else {
                System.out.println("Do not move. You are beneath me. (Stun 1)");
            }
            hero.setStun(1);
        }
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill3(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getSkill3() + "!");

        setCooldown3(skillCd3);

        double damage = getAttack() * 1.9;

        int manaReduce = getMana() - getManaCostSkill3();
        setMana(manaReduce);

        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getSkill3() + " deals " + df.format(damageDealt) + " damage!");

        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void ultimate(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getUltimate() + "!");
        setCooldownU(skillCdU);

        double damage = getAttack() * 2.3;

        int manaReduce = getMana() - getManaCostUltimate();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getUltimate() + " deals " + df.format(damageDealt) + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    
}
