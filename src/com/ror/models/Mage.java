package com.ror.models;

import java.text.DecimalFormat;

public class Mage extends Hero {
    private int skillCd1, skillCd2, skillCdU;

    public Mage() {
        super(4000, 550, 1000, 180, 90, 1, 
            "Selene Arclight", "Mage", "Wooden Staff", 
            "Stellar Shard", "Chains of Starlight", "Astral Cataclysm", 
            275, 545, 850, 
            2000, 2500, 570);
        this.skillCd1 = 6;
        this.skillCd2 = 8;
        this.skillCdU = 12;

        setMageCharacterChosen(true); // Javines | plot
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

        if (enemy.getStunned() > 0) {
            System.out.println(enemy.getName() + " is already bound by celestial chains! (Stun refreshed to 2 turn)");
        } else {
            System.out.println(enemy.getName() + " has been immobilized by starlight! (Stun 2)");
        }
        enemy.setStun(2);

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
        double multiplier = 0.8 + 0.8 * ((L - 1) / 59.0); // up to ~1.6x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplier1(int atk, int L) {
        double multiplier = 1.2 + 2.0 * ((L - 1) / 59.0); // up to ~3.2x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplier2(int atk, int L) {
        double multiplier = 1.5 + 3.0 * ((L - 1) / 59.0); // up to ~4.5x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplierU(int atk, int L) {
        double multiplier = 1.8 + 4.0 * ((L - 1) / 59.0); // up to ~5.8x
        return (int) Math.round(atk * multiplier);
    }

}
