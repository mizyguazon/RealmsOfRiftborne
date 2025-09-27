public class Swordsman extends Character{
    private int hp;
    private int attack;
    private int mana;
    private int defense;
    private int speed;
    private int level;
    private int experience;
    private String name;
    private String charClass;
    private String weapon;

    public Swordsman() {
        this.hp = 4000;
        this.attack = 500;
        this.mana = 700;
        this.defense = 350;
        this.speed = 100;
        this.level = 1;
        this.experience = 0;
        this.name = "Kael Solmere";
        this.charClass = "Swordsman";
        this.weapon = "Wooden Sword";
    }
    
}