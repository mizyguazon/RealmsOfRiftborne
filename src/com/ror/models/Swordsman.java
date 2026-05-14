package com.ror.models;

import java.text.DecimalFormat;

public class Swordsman extends Hero {
    private int skillCd1, skillCd2, skillCdU;

    public Swordsman() {
        super(4000, 500, 700, 350, 100, 1, 
            "Neo, the Timeblade", "Swordsman", "Shining Sword", 
            "Blade Dance", "Blinding Silhouette", "Shattered Sun", 
            285, 345, 565, 
            1450, 1700, 725);
        // setGold(99999999); // test purpose
        this.skillCd1 = 5;
        this.skillCd2 = 8;
        this.skillCdU = 10;
        // levelUp(101); // test purpose

        setSwordmanCharacterChosen(true); 
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
            System.out.println(enemy.getName() + " is already stunned! (Stun refreshed to 1 turn)");
        } else {
            System.out.println(enemy.getName() + " has been stunned! (Stun 1)");
        }
        enemy.setStun(1);

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
        
        if (enemy.getStunned() > 0) {
            System.out.println(enemy.getName() + " is already stunned! (Stun refreshed to 2 turns)");
        } else {
            System.out.println(enemy.getName() + " has been blinded by the sun! (Stun 2)");
        }
        enemy.setStun(2);

        enemy.setHp(enemy.getHp() - damageDealt);
    }

    @Override
    public int multiplierB(int atk, int L) {
        double multiplier = 1.0 + 0.8 * ((L - 1) / 59.0); // 1.0x → 1.8x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplier1(int atk, int L) {
        double multiplier = 1.5 + 1.3 * ((L - 1) / 59.0); // 1.5x → 2.8x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplier2(int atk, int L) {
        double multiplier = 2.0 + 1.8 * ((L - 1) / 59.0); // 2.0x → 3.8x
        return (int) Math.round(atk * multiplier);
    }

    @Override
    public int multiplierU(int atk, int L) {
        double multiplier = 2.5 + 2.5 * ((L - 1) / 59.0); // 2.5x → 5.0x
        return (int) Math.round(atk * multiplier);
    }

}
