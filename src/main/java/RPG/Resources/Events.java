package RPG.Resources;

import RPG.Control.Dice;
import RPG.Resources.Item;
import RPG.DBConnection.DatabaseHandler;

import java.util.Scanner;


public class Events {

    //ATRIBUTES
    private DatabaseHandler dbHandler;
    //METHOD
    public Events() {
        dbHandler = new DatabaseHandler();
    }


    Scanner sc = new Scanner(System.in);
    Player user = new Player("User", 100, 25, 15);
    Item potion = new Item("Potion", 25);
    boolean gameOngoing = true;

    public void startGame() {

        //PRESENTATION
        System.out.println("""
                Welcome to your new adventure.
                You'll be facing a succession of 91 enemies.
                Lets see how many of them you can handle.
                Press enter to continue...""");
        sc.nextLine();

        //BATTLE BEGIN
        while (gameOngoing) {
            choices();
        }
    }

    public void choices(){
        Enemy enemy = dbHandler.getEnemy();

        if(enemy != null) {


            //COMMAND SELECTION
            System.out.println("A new enemy has appeared:\n");
            System.out.println("""
                Choose your action:
                1- Attack
                2- Defend
                3- Item
                4- Flee""");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {attack();}
                case "2" -> {defend();}
                case "3" -> {item();}
                case "4" -> {flee();}
                default -> System.out.println("Please, choose between the options given.\n");
            }
        }else{
            System.out.println("No enemies found in DB");
        }
    }

    public void attack() {

        //PLAYER ATTACKS
        dbHandler.getEnemy().takeDamage(user.getAttack());
        System.out.println("\nYou attack the " + dbHandler.getEnemy().getName() + ". " + dbHandler.getEnemy().getName() +
                "'s health is now " + dbHandler.getEnemy().getHealth() + ".\n");

        //ENEMY ATTACKS BACK
        if (dbHandler.getEnemy().getHealth() > 0) {
            user.takeDamage(dbHandler.getEnemy().getAttack());
            System.out.println("You've been attacked by " + dbHandler.getEnemy().getName() + ". " + "you loose " + dbHandler.getEnemy().getAttack() + " points of health.\n" +
                    "Your health is " + user.getHealth() + " points.\n");

            //PLAYER DIES?
            if (user.getHealth()<=0) {
                deadPlayer();
            }

            //ENEMY DIES?
        }else if(dbHandler.getEnemy().getHealth()<=0) {
            afterBattle();;
        }
    }

    public void defend() {

        //DEFENDS (DAMAGE - DEFENSE)
        System.out.println("You crouch behind your shield.\n");
        user.takeDamage(dbHandler.getEnemy().getAttack() - user.getDefense());
        //ENEMY ATTACKS BACK
        System.out.println("You've been attacked by " + dbHandler.getEnemy().getName() + ", " + "but you only loose " + (dbHandler.getEnemy().getAttack() - user.getDefense()) + " points of health.\n" +
                "Now your health is " + user.getHealth() + "\n");
        //PLAYER DIES?
        if(user.getHealth()<=0) {
            deadPlayer();
        }
    }

    public void item() {
        user.setHealth(user.getHealth() + potion.getCureHP());
        System.out.println("You use a potion. You gain " + potion.getCureHP() +
                " HP. " + "Your Health is now " + user.getHealth() + ".\n");
    }

    public void flee() {

        boolean diceResult = Dice.fleeDice();

        if (!diceResult) {

            //CAN'T FLEE
            System.out.println("Couldn't flee...\n");
            user.takeDamage(dbHandler.getEnemy().getAttack());
            System.out.println("You've been attacked by " + dbHandler.getEnemy().getName() + ". " + "you lose " + dbHandler.getEnemy().getAttack() + " points of health.\n" +
                    "Now your health is " + user.getHealth() + "\n");

            //PLAYER DIES?
            if (user.getHealth()<=0) {
                deadPlayer();
            }

            //SUCCESSFULLY FLEES
        } else {
            System.out.println("You flee like the piece of sh*t you are. No one likes you.");
            System.exit(0);
        }
    }

    public void afterBattle() {
        System.out.println("You defeated " + dbHandler.getEnemy().getName() + "! Congratulations!\n");
        System.out.println("""
                        Would you like to use a potion to heal yourself?
                        (Y)es
                        (N)o""");
        String choice2 = sc.nextLine();
        //DRINKS POTION
        if(choice2.equalsIgnoreCase("Y")){
            user.setHealth(user.getHealth() + potion.getCureHP());
            System.out.println("You use a potion. You gain " + potion.getCureHP() +
                    " HP. " + "Your Health is now " + user.getHealth() + ".\n");
        }
        //DOESN'T DRINK
        else if(choice2.equalsIgnoreCase("N")){
            System.out.println("Then we'll just keep going with your nightmare. " + "Your Health level is " + user.getHealth() + "\n");
        }

        //BATTLE RESET
        choices();
    }

    public void deadPlayer(){

        System.out.println("You are dead... Thank you for playing.");
        System.exit(0);
    }
}