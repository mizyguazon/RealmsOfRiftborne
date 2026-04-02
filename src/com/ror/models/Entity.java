package com.ror.models;

public abstract class Entity {

    // === Common Stats ===
    protected String name;
    protected int level;
    protected int hp;
    protected int hpCap;
    protected int mana;
    protected int manaCap;
    protected int attack;
    protected int defense;
    protected int speed;

    // === Cooldowns ===
    protected int cooldown1;
    protected int cooldown2;
    protected int cooldown3;
    protected int cooldownU;

    // === Skill Information ===
    protected String skill1;
    protected String skill2;
    protected String skill3;
    protected String ultimate;

    // === Mana Costs ===
    protected int manaCostSkill1;
    protected int manaCostSkill2;
    protected int manaCostSkill3;
    protected int manaCostUltimate;

    // === Battle Debuffs ===
    protected int stunned = -1;
    protected int disabled = -1;

    // === Constructors ===
    public Entity() {}

    public Entity(String name, int level, int hp, int mana, int attack, int defense) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.mana = mana;
        this.manaCap = mana;
        this.attack = attack;
        this.defense = defense;
    }

    // === Abstract Methods ===
    public abstract boolean dodgeHeroAtk(Entity defender, Hero attacker);
    public abstract void basicAttack(Entity enemy, Hero hero);
    public abstract void skill1(Entity enemy, Hero hero);
    public abstract void skill2(Entity enemy, Hero hero);
    public abstract void skill3(Entity enemy, Hero hero);
    public abstract void ultimate(Entity enemy, Hero hero);

    // === Getters ===
    public String getName() { 
        return name; 
    }

    public int getLevel() { 
        return level; 
    }

    public int getHp() { 
        return hp; 
    }

    public int getMana() { 
        return mana; 
    }

    public int getManaCap() { 
        return manaCap; 
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

    public int getManaCostSkill1() { 
        return manaCostSkill1; 
    }

    public int getManaCostSkill2() { 
        return manaCostSkill2; 
    }

    public int getManaCostSkill3() { 
        return manaCostSkill3; 
    }

    public int getManaCostUltimate() { 
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

    public int getStunned() {
        return stunned;
    }

    public int getHpCap() {
        return hpCap;
    }

    public int getDisabled() {
        return disabled;
    }

    // === Setters ===
    public void setName(String name) { 
        this.name = name; 
    }

    public void setLevel(int level) { 
        this.level = level; 
    }

    public void setHp(int hp) { 
        this.hp = hp; 
    }

    public void setMana(int mana) { 
        this.mana = mana; 
    }

    public void setManaCap(int manaCap) { 
        this.manaCap = manaCap; 
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

    public void setSkill1(String skill1) { 
        this.skill1 = skill1; 
    }

    public void setSkill2(String skill2) { 
        this.skill2 = skill2; 
    }

    public void setSkill3(String skill3) { 
        this.skill3 = skill3; 
    }

    public void setUltimate(String ultimate) { 
        this.ultimate = ultimate; 
    }

    public void setManaCostSkill1(int cost) { 
        this.manaCostSkill1 = cost; 
    }

    public void setManaCostSkill2(int cost) { 
        this.manaCostSkill2 = cost; 
    }

    public void setManaCostSkill3(int cost) { 
        this.manaCostSkill3 = cost; 
    }

    public void setManaCostUltimate(int cost) { 
        this.manaCostUltimate = cost; 
    }

    public void setCooldown1(int cooldown1) { 
        this.cooldown1 = cooldown1; 
    }

    public void setCooldown2(int cooldown2) { 
        this.cooldown2 = cooldown2; 
    }

    public void setCooldown3(int cooldown3) { 
        this.cooldown3 = cooldown3; 
    }

    public void setCooldownU(int cooldownU) { 
        this.cooldownU = cooldownU; 
    }

    public void setStun(int stunned) {
        this.stunned = stunned;
    }

    public void setHpCap(int hpCap) {
        this.hpCap = hpCap;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
}
