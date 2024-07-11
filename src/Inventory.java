public class Inventory {
    private int potionCount;

    public Inventory(int initialPotionCount) {
        this.potionCount = initialPotionCount;
    }

    public int getPotionCount() {
        return potionCount;
    }

    public boolean usePotion() {
        if (potionCount > 0) {
            potionCount--;
            return true;
        }
        return false;
    }

    public void addPotion() {
        potionCount++;
    }
}
