package RPG.Control;

import java.util.Random;

public class Dice {


    public static boolean fleeDice() {
        Random fleechance = new Random();
        return fleechance.nextBoolean();
    }

}