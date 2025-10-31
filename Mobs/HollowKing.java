package Mobs;

import Hero.Entity;
import Hero.Hero;
import java.text.DecimalFormat;

public class HollowKing extends Mobs {
    private int skillCd1 = 3;
    private int skillCd2 = 5;
    private int skillCdU = 8;

    public HollowKing() {
        super("Hollow King", 20000, 1100, 2000, 60, 1000, 1000, "Cursed Command", "Soul Drain", "King's Despair", 200, 500, 1200);

        this.skillCd1 = 3;
        this.skillCd2 = 5;  
        this.skillCdU = 8;

    }

    DecimalFormat df = new DecimalFormat("#,##0");

    @Override
    public void basicAttack(Entity enemy, Hero hero) {
        System.out.println(getName() + " used Basic Attack!");
        

        double damage = getAttack() * 1.4;

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

        double damage = getAttack() * 2.0;

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
        
        
        double damage = getAttack() * 2.4;

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

        double damage = getAttack() * 2.8;

        int manaReduce = getMana() - getManaCostUltimate();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getUltimate() + " deals " + df.format(damageDealt) + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }
    
}