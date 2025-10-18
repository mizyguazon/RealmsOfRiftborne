package Mobs;

import Hero.Entity;
import Hero.Hero;

public abstract class Mobs extends Entity {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int level;
    private int mana;
    private int manaCap;
    private String skill1, skill2, ultimate;
    private int manaCostSkill1, manaCostSkill2, manaCostUltimate;
    private int cooldown1 = 0;
    private int cooldown2 = 0;
    private int cooldownU = 0;
    private int skillCd1, skillCd2, skillCdU;
    

    public Mobs(String name, int hp, int attack, int defense, int speed, int level, int mana, int manaCap, String skill1, String skill2, String ultimate, int manaCostSkill1, int manaCostSkill2, int manaCostUltimate) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.level = level;
        this.mana = mana;
        this.manaCap = manaCap;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.ultimate = ultimate;
        this.manaCostSkill1 = manaCostSkill1;
        this.manaCostSkill2 = manaCostSkill2;
        this.manaCostUltimate = manaCostUltimate;
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

        int manaReduce = getMana() - getManaCostSkill1();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;


        System.out.println(getName() + " used " + getSkill1() + "!");
        System.out.println(getSkill1() + " deals " + damageDealt + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill2(Hero hero){
        setCooldown2(skillCd2);

        double damage = getAttack() * 1.8;

        int manaReduce = getMana() - getManaCostSkill2();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;


        System.out.println(getName() + " used " + getSkill2() + "!");
        System.out.println(getSkill2() + " deals " + damageDealt + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    @Override
    public void skill3(Hero hero){
        
    }

    @Override
    public void ultimate(Hero hero){
        setCooldownU(skillCdU);

        double damage = getAttack() * 2.3;

        int manaReduce = getMana() - getManaCostUltimate();
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(getName() + " used " + getUltimate() + "!");
        System.out.println(getUltimate() + " deals " + damageDealt + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }
    
    //setters
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    //getters
    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getSpeed() {
        return speed;
    }
    public int getLevel() {
        return level;
    }
    public int getMana() {
        return mana;
    }
}