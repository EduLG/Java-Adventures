package RPG.Resources;

public class Enemy {


    int id;
    String name;
    int health;
    int attack;

    //Constructor
    public Enemy (int id, String name, int health, int attack) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.attack = attack;
    }




    //GETSETTERS
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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




    //METHODS
    public void takeDamage(int dmg){
        health = health - dmg;
    }

}

