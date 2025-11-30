package Mobs;

import Hero.Entity;
import Hero.Hero;
import java.util.Random;

public class Mobs extends Entity {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int mana;
    private int manaCap;
    private String skill1, skill2, ultimate;
    private int manaCostSkill1, manaCostSkill2, manaCostUltimate;
    private int skillCd1, skillCd2, skillCdU;
    private int cooldown1 = 0;
    private int cooldown2 = 0;
    private int cooldownU = 0;
    

    public Mobs(String name, int hp, int attack, int defense, int speed, int mana, String skill1, String skill2, String ultimate, int manaCostSkill1, int manaCostSkill2, int manaCostUltimate) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.mana = mana;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.ultimate = ultimate;
        this.manaCostSkill1 = manaCostSkill1;
        this.manaCostSkill2 = manaCostSkill2;
        this.manaCostUltimate = manaCostUltimate;
    }
    
    
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

        System.out.println("Basic Attack deals " + damageDealt + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    public void skill1(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + skill1 + "!");

        enemy.setCooldown1(skillCd1);
        

        double damage = getAttack() * 1.5;

        int manaReduce = getMana() - manaCostSkill1;
        enemy.setMana(manaReduce);

        if(hero.dodgeEnemyAtk(hero, enemy)) return;
        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(skill1 + " deals " + damageDealt + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    public void skill2(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + skill2 + "!");

        enemy.setCooldown2(skillCd2);
        

        double damage = getAttack() * 1.8;

        int manaReduce = getMana() - manaCostSkill2;
        enemy.setMana(manaReduce);

        if(hero.dodgeEnemyAtk(hero, enemy)) return;
        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;


        System.out.println(skill2 + " deals " + damageDealt + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    public void skill3(Entity enemy, Hero hero){
        
    }

    public void ultimate(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + ultimate + "!");

        enemy.setCooldownU(skillCdU);

        double damage = getAttack() * 2.3;

        int manaReduce = getMana() - manaCostUltimate;
        enemy.setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(ultimate + " deals " + damageDealt + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    // Dodge Hero attack method || Enemy dodges
    public boolean dodgeHeroAtk(Entity defender, Hero attacker){
        Random rand = new Random();
        double dodgeChance = (double) defender.getSpeed() / (defender.getSpeed() + attacker.getSpeed()) * 0.5;
        double roll = rand.nextDouble(0.0, 1.0);

        if (roll < dodgeChance) {
            System.out.println(defender.getName() + " dodged the attack!");
            return true;
        }

        return false;
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
    public void setManaCap(int manaCap){
        this.manaCap = manaCap;
    }
    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }
    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }
    public void setUltimate(String ultimate) {
        this.ultimate = ultimate;
    }
    public void setManaCostSkill1(int manaCostSkill1){
        this.manaCostSkill1 = manaCostSkill1;
    }
    public void setManaCostSkill2(int manaCostSkill2){
        this.manaCostSkill2 = manaCostSkill2;
    }
    public void setManaCostUltimate(int manaCostUltimate){
        this.manaCostUltimate = manaCostUltimate;
    }
    public void setCooldown1(int cd1) {
        cooldown1 = cd1;
    }

    public void setCooldown2(int cd2) {
        cooldown2 = cd2;
    }
    public void setCooldownU(int cdU) {
        cooldownU = cdU;
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
    public int getManaCap() {
        return manaCap;
    }
    public int getManaCostSkill1(){
        return manaCostSkill1;
    }
    public int getManaCostSkill2(){
        return manaCostSkill2;
    }
    public int getManaCostUltimate(){
        return manaCostUltimate;
    }
    public String getSkill1() {
        return skill1;
    }
    public String getSkill2() {
        return skill2;
    }
    public String getUltimate() {
        return ultimate;
    }
    public int getCooldown1() {
        return cooldown1;
    }

    public int getCooldown2() {
        return cooldown2;
    }
    public int getCooldownU() {
        return cooldownU;
    }
}