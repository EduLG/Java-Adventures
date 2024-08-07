public class Enemy {

    String name;
    int initialHealth;
    int health;
    int attack;

    //Constructor
    public Enemy (String name, int initialHealth, int health, int attack) {
        this.name = name;
        this.initialHealth = initialHealth;
        this.health = health;
        this.attack = attack;
    }



    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
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

    //Methods
    public void takeDamage(int dmg){
        health = health - dmg;
    }

    public void recover() {
        this.health = initialHealth;
    }
}

