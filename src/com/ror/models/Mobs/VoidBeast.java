package com.ror.models.Mobs;

import com.ror.models.Entity;
import com.ror.models.Hero;
import java.text.DecimalFormat;

public class VoidBeast extends Mobs {
    private int skillCd1 = 2;
    private int skillCd2 = 4;

    public VoidBeast() {
        super("Void Beast", 14500, 900, 1000, 50,1000, "Rift Claw", "Gravity Crush", "Unknown", 400, 750, 0);
        
        this.skillCd1 = 2;
        this.skillCd2 = 4;

    }

    DecimalFormat df = new DecimalFormat("#,##0");

    @Override
    public void basicAttack(Entity enemy, Hero hero) {
        System.out.println(getName() + " used Basic Attack!");
        

        double damage = getAttack() * 1.3;

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

        double damage = getAttack() * 1.8;

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
        
        
        double damage = getAttack() * 2.1;

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
        
    }

    
}
