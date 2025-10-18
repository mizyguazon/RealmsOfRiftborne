package Boss;

import java.text.DecimalFormat;

import Hero.*;

public class Azrael extends Boss {
    private int skillCd1, skillCd2, skillCd3, skillCdU;

    public Azrael() {
        super(30000, 5000, 4500, 1000, 40, "Azrael - Annihilation of Realms", "Eclipse Blades", "Eclipse Bind", "Umbral Rebirth", "Oblivion Embrace", 350, 500, 450, 900);
        this.skillCd1 = 4;
        this.skillCd2 = 6;
        this.skillCd3 = 7;
        this.skillCdU = 9;
    }
    
    DecimalFormat df = new DecimalFormat("#,##0");

    @Override
    public void basicAttack(Entity enemy, Hero hero) {
        System.out.println(getName() + " used Basic Attack!");
        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.1;

        double manaRecovery = getManaCap() * 0.2;

        if(manaRecovery+getMana() > getManaCap()){
            setMana(getManaCap());
        } else {
            int addMana = (int) manaRecovery + getMana();
            setMana(addMana);
        }

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println("Basic Attack deals " + df.format(damageDealt) + " damage!");

        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill1(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getSkill1() + "!");

        setCooldown1(skillCd1);
        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.5;

        int manaReduce = getMana() - getManaCostSkill1();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getSkill1() + " deals " + df.format(damageDealt) + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getSkill2() + "!");

        setCooldown2(skillCd2);
        if(hero.dodgeEnemyAtk(hero, enemy)) return;
        
        double damage = getAttack() * 1.8;

        int manaReduce = getMana() - getManaCostSkill2();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getSkill2() + " deals " + df.format(damageDealt) + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill3(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + getSkill3() + "!");

        setCooldown3(skillCd3);
        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.9;

        int manaReduce = getMana() - getManaCostSkill3();
        setMana(manaReduce);

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