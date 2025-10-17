package Boss;

import Hero.*;

public class Elderthorn extends Boss {
    private String skill1, skill2, skill3, ultimate;
    private int manaCostSkill1, manaCostSkill2, manaCostSkill3, manaCostUltimate;
    private int skillCd1, skillCd2, skillCd3, skillCdU;

    public Elderthorn() {
        super(12500, 450, 1000, 150, 20, "Elderthorn");
        this.skill1 = "Thorn Slash";
        this.skill2 = "Thorn Cage";
        this.skill3 = "Ancient Renewal";
        this.ultimate = "Eternal Wildstorm";
        this.manaCostSkill1 = 50;
        this.manaCostSkill2 = 250;
        this.manaCostSkill3 = 350;
        this.manaCostUltimate = 750;
        this.skillCd1 = 4;
        this.skillCd2 = 6;
        this.skillCd3 = 6;
        this.skillCdU = 8;
    }


    @Override
    public void basicAttack(Hero hero) {
        double damage = getAttack() * 1.1;

        double manaRecovery = getManaCap() * 0.2;

        if(manaRecovery+getMana() > getManaCap()){
            setMana(getManaCap());
        } else {
            int addMana = (int) manaRecovery + getMana();
            setMana(addMana);
        }

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getName() + " used Basic Attack!");
        System.out.println("Basic Attack deals " + damageDealt + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill1(Hero hero){
        setCooldown1(skillCd1);

        double damage = getAttack() * 1.5;

        int manaReduce = getMana() - manaCostSkill1;
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;


        System.out.println(getName() + " used " + skill1 + "!");
        System.out.println(skill1 + " deals " + damageDealt + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Hero hero){
        setCooldown2(skillCd2);

        double damage = getAttack() * 1.8;

        int manaReduce = getMana() - manaCostSkill2;
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;


        System.out.println(getName() + " used " + skill2 + "!");
        System.out.println(skill2 + " deals " + damageDealt + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill3(Hero hero){
        setCooldown3(skillCd3);

        double damage = getAttack() * 1.9;

        int manaReduce = getMana() - manaCostSkill3;
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;


        System.out.println(getName() + " used " + skill3 + "!");
        System.out.println(skill3 + " deals " + damageDealt + " damage!");

        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void ultimate(Hero hero){
        setCooldownU(skillCdU);

        double damage = getAttack() * 2.3;

        int manaReduce = getMana() - manaCostUltimate;
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getName() + " used " + ultimate + "!");
        System.out.println(ultimate + " deals " + damageDealt + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

}