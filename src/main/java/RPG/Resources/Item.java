package RPG.Resources;

public class Item {
    String name;
    int cureHP;

    public Item (String name, int cureHP){
        this.name = name;
        this.cureHP = cureHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCureHP() {
        return cureHP;
    }

    public void setCureHP(int cureHP) {
        this.cureHP = cureHP;
    }
}