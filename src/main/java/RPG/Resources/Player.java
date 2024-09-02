package RPG.Resources;

public class Player {


    String name;
    int health;
    int attack;
    int defense;

    public Player (String name, int health, int attack, int defense){
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    //GETSETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }


    //METHODS

    public void takeDamage(int enemyDmg){
        health = health - enemyDmg;
    }


}