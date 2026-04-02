package com.ror.models.Mobs;

import com.ror.models.Entity;
import com.ror.models.Hero;
import java.text.DecimalFormat;

public class FadingWarden extends Mobs {
    private int skillCd1 = 4;
    private int skillCd2 = 6;
    private int skillCdU = 8;
 
    public FadingWarden() {
        super("Fading Warden", 10500, 900, 1000, 50,1000, "Ethereal Slash", "Ghost Step", "Oblivion Veil", 300, 450, 600);

        this.skillCd1 = 4;
        this.skillCd2 = 6;  
        this.skillCdU = 8;
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


        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getSkill2() + "!");

        setCooldown2(skillCd2);
        
        
        double damage = getAttack() * 1.9;

        int manaReduce = getMana() - getManaCostSkill2();
        setMana(manaReduce);

        if(hero.dodgeEnemyAtk(hero, enemy)) return;
        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getSkill2() + " deals " + df.format(damageDealt) + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill3(Entity enemy, Hero hero){

    }

    @Override
    public void ultimate(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getUltimate() + "!");
        setCooldownU(skillCdU);

        double damage = getAttack() * 2.5;

        int manaReduce = getMana() - getManaCostUltimate();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getUltimate() + " deals " + df.format(damageDealt) + " damage!");

        if(hero.getStunned() > 0) {
            System.out.println("Paralysis renewed! You remain stunned for another turn. (Stun refreshed to 1 turn)");
        } else {
            System.out.println("You've been stunned! (Stun 1)");
        }
        hero.setStun(1);
        
        hero.setHp(hero.getHp() - damageDealt);
    }


    
}
