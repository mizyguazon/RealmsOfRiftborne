package com.ror.models;

import java.text.DecimalFormat;

public class Test extends Hero {
    private int skillCd1, skillCd2, skillCdU;

    public Test() {
        super(999999, 999999, 999999, 999999, 100, 60, 
        "Unknown", "Unknown", "Unknown", 
        "Delete", "Perish", "Judgement", 
        0, 0, 0, 
        999999, 999999, 999999);
        setMaxHp(999999);
        setGold(99999999);
        this.skillCd1 = 0;
        this.skillCd2 = 0;
        this.skillCdU = 0;
    }

    DecimalFormat df = new DecimalFormat("#,##0");

   @Override
    public void basicAttack(Hero hero, Entity enemy) {
        System.out.println(getName() + " used Basic Attack!");

        int damage = multiplierB(getAttack(), getLevel());
        
        double manaRecovery = getManaCap() * 0.2;

        if(manaRecovery+getMana() > getManaCap()){
            setMana(getManaCap());
        } else {
            int addMana = (int) manaRecovery + getMana();
            setMana(addMana);
        }

        if(enemy.dodgeHeroAtk(enemy, hero)) return;

        int damageDealt = damage - enemy.getDefense()/2;

        System.out.println("Basic Attack deals " + df.format(damageDealt) + " damage!");

        enemy.setHp(enemy.getHp() - damageDealt);
    }

    @Override
    public void skill1(Hero hero, Entity enemy){
        System.out.println(getName() + " used " + getSkill1() + "!");
        setCooldown1(skillCd1);

        int damage = multiplier1(getAttack(), getLevel());

        int manaReduce = getMana() - scaledCost(getManaCostSkill1());
        setMana(manaReduce);

        if(enemy.dodgeHeroAtk(enemy, hero)) return;

        int damageDealt = damage - enemy.getDefense()/2;

        System.out.println(getSkill1() + " deals " + df.format(damageDealt) + " damage!");

        enemy.setHp(enemy.getHp() - damageDealt);
    }

    @Override
    public void skill2(Hero hero, Entity enemy){
        System.out.println(getName() + " used " + getSkill2() + "!");
        setCooldown2(skillCd2);

        int damage = multiplier2(getAttack(), getLevel());

        int manaReduce = getMana() - scaledCost(getManaCostSkill2());
        setMana(manaReduce);

        if(enemy.dodgeHeroAtk(enemy, hero)) return;

         int damageDealt = damage - enemy.getDefense()/2;

        System.out.println(getSkill2() + " deals " + df.format(damageDealt) + " damage!");

        enemy.setHp(enemy.getHp() - damageDealt);
    }

    @Override
    public void ultimate(Hero hero, Entity enemy){
        System.out.println(getName() + " used " + getUltimate() + "!");
        setCooldownU(skillCdU);

        int damage = multiplierU(getAttack(), getLevel());

        int manaReduce = getMana() - scaledCost(getManaCostUltimate());
        setMana(manaReduce);

        int damageDealt = damage - enemy.getDefense()/2;

        System.out.println(getUltimate() + " deals " + df.format(damageDealt) + " damage!");

        enemy.setHp(enemy.getHp() - damageDealt);
    }

    @Override
    public int multiplierB(int atk, int L) {
        double multiplier = 0.9 + 0.7 * ((L - 1) / 59.0); // up to ~1.6x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplier1(int atk, int L) {
        double multiplier = 1.1 + 1.5 * ((L - 1) / 59.0); // up to ~2.6x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplier2(int atk, int L) {
        double multiplier = 1.3 + 2.3 * ((L - 1) / 59.0); // up to ~3.6x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplierU(int atk, int L) {
        double multiplier = 1.6 + 3.2 * ((L - 1) / 59.0); // up to ~4.8x
        return (int) Math.round(atk * multiplier);
    }

}
