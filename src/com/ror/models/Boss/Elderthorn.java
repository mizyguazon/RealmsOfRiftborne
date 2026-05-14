package com.ror.models.Boss;

import java.text.DecimalFormat;

import com.ror.models.*;

public class Elderthorn extends Boss {
    private int skillCd1, skillCd2, skillCd3, skillCdU;
    DecimalFormat df = new DecimalFormat("#,##0");

    public Elderthorn() {
        super(11200, 685, 950, 230, 90, "Elderthorn",
              "Thorn Slash", "Thorn Cage", "Ancient Renewal", "Eternal Wildstorm",
              75, 200, 325, 500);
        this.skillCd1 = 4;
        this.skillCd2 = 6;
        this.skillCd3 = 9;
        this.skillCdU = 11;
    }

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


        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Entity enemy, Hero hero) {
        System.out.println(getName() + " used " + getSkill2() + "!");

        setCooldown2(skillCd2);
        int manaReduce = getMana() - getManaCostSkill2();
        setMana(manaReduce);

        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.8;
        int damageDealt = (int)Math.round(damage) - hero.getDefense() / 2;

        hero.setHp(hero.getHp() - damageDealt);
        System.out.println(getSkill2() + " deals " + df.format(damageDealt) + " damage!");

        if (hero.getStunned() > 0) {
            System.out.println("(Stun overridden) " + hero.getName() + "'s previous stun duration is refreshed! (Stun 1)");
        } else {
            System.out.println(hero.getName() + " has been stunned! (Stun 1)");
        }

        hero.setStun(1);
    }

    @Override
    public void skill3(Entity enemy, Hero hero) {
        System.out.println(getName() + " used " + getSkill3() + "!");

        setCooldown3(skillCd3);
        int manaReduce = getMana() - getManaCostSkill3();
        setMana(manaReduce);

        int healAmount = (int)(getHpCap() * 0.20);
        if(healAmount + getHp() >= getHpCap()){
            setHp(getHpCap());
        } else {
            int newHp = Math.min(getHp() + healAmount, getHpCap());
            setHp(newHp);
        }

        System.out.println(getName() + " regenerates " + df.format(healAmount) + " HP!");
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
