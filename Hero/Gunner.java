package Hero;

import java.text.DecimalFormat;

public class Gunner extends Hero {
    private int skillCd1, skillCd2, skillCdU;

    public Gunner() {
        super(4000, 535, 600, 125, 125, 20, "Aria Caelith", "Gunner", "Rusty Dual Pistol", "Crimson Barrage", "Frostwind Bullet", "Judgement Phantom", 35, 85, 325, 1500, 1530, 650);
        this.skillCd1 = 3;
        this.skillCd2 = 5;
        this.skillCdU = 8;
    }

    DecimalFormat df = new DecimalFormat("#,##0");

   @Override
    public void basicAttack(Hero hero, Entity enemy) {
        System.out.println(getName() + " used Basic Attack!");
        if(enemy.dodgeHeroAtk(enemy, hero)) return;

        int damage = multiplierB(getAttack(), getLevel());
        
        double manaRecovery = getManaCap() * 0.2;

        if(manaRecovery+getMana() > getManaCap()){
            setMana(getManaCap());
        } else {
            int addMana = (int) manaRecovery + getMana();
            setMana(addMana);
        }

        int damageDealt = damage - enemy.getDefense()/2;

        System.out.println("Basic Attack deals " + df.format(damageDealt) + " damage!");

        enemy.setHp(enemy.getHp() - damageDealt);
    }

    @Override
    public void skill1(Hero hero, Entity enemy){
        System.out.println(getName() + " used " + getSkill1() + "!");
        setCooldown1(skillCd1);
        if(enemy.dodgeHeroAtk(enemy, hero)) return;

        int damage = multiplier1(getAttack(), getLevel());

        int manaReduce = getMana() - scaledCost(getManaCostSkill1());
        setMana(manaReduce);

        int damageDealt = damage - enemy.getDefense()/2;

        System.out.println(getSkill1() + " deals " + df.format(damageDealt) + " damage!");

        enemy.setHp(enemy.getHp() - damageDealt);
    }

    @Override
    public void skill2(Hero hero, Entity enemy){
        System.out.println(getName() + " used " + getSkill2() + "!");
        setCooldown2(skillCd2);
        if(enemy.dodgeHeroAtk(enemy, hero)) return;

        int damage = multiplier2(getAttack(), getLevel());

        int manaReduce = getMana() - scaledCost(getManaCostSkill2());
        setMana(manaReduce);

         int damageDealt = damage - enemy.getDefense()/2;

        System.out.println(getSkill2() + " deals " + df.format(damageDealt) + " damage!");

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

        enemy.setHp(enemy.getHp() - damageDealt);
    }
    
}