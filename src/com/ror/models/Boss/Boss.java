package com.ror.models.Boss;

import java.util.Random;

import com.ror.models.*;

public class Boss extends Entity {
    private int hp;
    private int attack;
    private int mana;
    private int defense;
    private int speed;
    private String name;
    private String skill1, skill2, skill3, ultimate;
    private int manaCostSkill1, manaCostSkill2, manaCostSkill3, manaCostUltimate;
    private int cooldown1 = 0;
    private int cooldown2 = 0;
    private int cooldown3 = 0;
    private int cooldownU = 0; 
    private int skillCd1, skillCd2, skillCd3, skillCdU;
    private int manaCap;
    private int hpCap;
    private int stunned = -1;
    private boolean rebirth = false;
    private boolean revive = false;
    private int disabled = 0;

    public Boss(int hp, int attack, int mana, int defense, int speed, String name, String skill1, String skill2, String skill3, String ultimate, int manaCostSkill1, int manaCostSkill2, int manaCostSkill3, int manaCostUltimate){
        this.hp = hp;
        this.attack = attack;
        this.mana = mana;
        this.defense = defense;
        this.speed = speed;
        this.name = name;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.ultimate = ultimate;
        this.manaCostSkill1 = manaCostSkill1;
        this.manaCostSkill2 = manaCostSkill2;
        this.manaCostSkill3 = manaCostSkill3;
        this.manaCostUltimate = manaCostUltimate;
    }

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

        System.out.println("Basic Attack deals " + damageDealt + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    public void skill1(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + skill1 + "!");

        setCooldown1(skillCd1);
        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.5;

        int manaReduce = getMana() - manaCostSkill1;
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(skill1 + " deals " + damageDealt + " damage!");


        hero.setHp(hero.getHp() - damageDealt);
    }

    public void skill2(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + skill2 + "!");

        setCooldown2(skillCd2);
        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.8;

        int manaReduce = getMana() - manaCostSkill2;
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;


        System.out.println(skill2 + " deals " + damageDealt + " damage!");
        
        hero.setHp(hero.getHp() - damageDealt);
    }

    public void skill3(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + skill3 + "!");

        setCooldown3(skillCd3);
        if(hero.dodgeEnemyAtk(hero, enemy)) return;

        double damage = getAttack() * 1.9;

        int manaReduce = getMana() - manaCostSkill3;
        setMana(manaReduce);

        int damageDealt = (int) Math.round(damage) - hero.getDefense()/2;

        System.out.println(skill3 + " deals " + damageDealt + " damage!");

        hero.setHp(hero.getHp() - damageDealt);
    }

    public void ultimate(Entity enemy, Hero hero){
        System.out.println(getName() + " used " + ultimate + "!");

        setCooldownU(skillCdU);

        double damage = getAttack() * 2.3;

        int manaReduce = getMana() - manaCostUltimate;
        setMana(manaReduce);

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

    // Setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCooldown1(int cd1) {
        cooldown1 = cd1;
    }

    public void setCooldown2(int cd2) {
        cooldown2 = cd2;
    }

    public void setCooldown3(int cd3) {
        cooldown3 = cd3;
    }

    public void setCooldownU(int cdU) {
        cooldownU = cdU;
    }

    public void setManaCap(int manaCap) {
        this.manaCap = manaCap;
    }

    public void setStun(int stunned) {
        this.stunned = stunned;
    }

    public void setHpCap(int hpCap) {
        this.hpCap = hpCap;
    }

    public void setRebirth(boolean rebirth) {
        this.rebirth = rebirth;
    }

    public void setRevive(boolean revive) {
        this.revive = revive;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public void setSkill3Cd(int skillCd3) {
        this.skillCd3 = skillCd3;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    // Getters
    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getMana() {
        return mana;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public String getSkill1() {
        return skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public String getUltimate() {
        return ultimate;
    }

    public int getManaCostSkill1(){
        return manaCostSkill1;
    }

    public int getManaCostSkill2(){
        return manaCostSkill2;
    }

    public int getManaCostSkill3(){
        return manaCostSkill3;
    }

    public int getManaCostUltimate(){
        return manaCostUltimate;
    }

    public int getCooldown1() {
        return cooldown1;
    }

    public int getCooldown2() {
        return cooldown2;
    }

    public int getCooldown3() {
        return cooldown3;
    }

    public int getCooldownU() {
        return cooldownU;
    }

    public int getManaCap() {
        return manaCap;
    }

    public int getStunned() {
        return stunned;
    }

    public int getHpCap() {
        return hpCap;
    }

    public boolean getRebirth() {
        return rebirth;
    }

    public boolean getRevive() {
        return revive;
    }

    public int getDisabled() {
        return disabled;
    }
}
