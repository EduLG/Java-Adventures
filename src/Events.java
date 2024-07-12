import java.util.Scanner;


public class Events {

    Scanner sc = new Scanner(System.in);
    Player user = new Player("User", 100, 25, 15);
    Enemy skull = new Enemy("Skull", 100, 100, 20);
    Item potion = new Item("Potion", 25);
    boolean gameOngoing = true;

    public void startGame() {

        System.out.println("""
                Welcome to your new adventure.
                You'll be facing an infinite succession of enemies.
                Lets see how many of them you can handle.
                Press enter to continue...""");
        sc.nextLine();

        while (gameOngoing) {
            battle();
        }
    }

    public void battle(){

        skull.recover();
        System.out.println(skull.getName() + " has appeared:\n" +
                "Health: " + skull.getHealth() + "\n");

        boolean battleOngoing = true;
        while (battleOngoing) {

            System.out.println("""
                Choose your action:
                1- Attack
                2- Defend
                3- Item
                4- Flee""");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    attack();
                }
                case "2" -> {
                   defend();
                }
                case "3" -> {
                    item();
                }
                case "4" -> {
                    flee();
                }
                default -> System.out.println("Please, choose between the options given.\n");
            }
        }
    }

    public void attack() {

        skull.takeDamage(user.getAttack());

        System.out.println("\nYou attack the " + skull.getName() + ". " + skull.getName() +
                "'s health is now " + skull.getHealth() + ".\n");

        if (skull.getHealth() > 0) {

            user.takeDamage(skull.getAttack());

            System.out.println("You've been attacked by " + skull.getName() + ". " + "you loose " + skull.getAttack() + " points of health.\n" +
                    "Your health is " + user.getHealth() + " points.\n");

            if (user.getHealth()<=0) {
                deadPlayer();
            }
        }else if(skull.getHealth()<=0) {
            afterBattle();;
        }
    }

    public void defend() {

        System.out.println("You crouch behind your shield.\n");

        user.takeDamage(skull.getAttack() - user.getDefense());

        System.out.println("You've been attacked by " + skull.getName() + ", " + "but you only loose " + (skull.getAttack() - user.getDefense()) + " points of health.\n" +
                "Now your health is " + user.getHealth() + "\n");

        if(user.getHealth()<=0) {
            deadPlayer();
        }
    }

    public void item() {

        System.out.println("You use a potion. You gain " + potion.getCureHP() + " HP.");

        user.setHealth(user.getHealth() + potion.getCureHP());

        System.out.println("Your Health level is now " + user.getHealth() + ".\n");

    }

    public void flee() {

        boolean diceResult = Dice.fleeDice();

        if (!diceResult) {
            System.out.println("Couldn't flee...\n");
            user.takeDamage(skull.getAttack());
            System.out.println("You've been attacked by " + skull.getName() + ". " + "you lose " + skull.getAttack() + " points of health.\n" +
                    "Now your health is " + user.getHealth() + "\n");
            if (user.getHealth()<=0) {
                deadPlayer();
            }

        } else {
            System.out.println("You flee like the piece of sh*t you are. No one likes you.");
            System.exit(0);
        }
    }

    public void afterBattle() {
        System.out.println("You defeated " + skull.getName() + "! Congratulations!\n");
        System.out.println("""
                        Would you like to use a potion to heal yourself?
                        (Y)es
                        (N)o""");
        String choice2 = sc.nextLine();
        if(choice2.equalsIgnoreCase("Y")){
            System.out.println("You drink a potion. You recover 25 points of health.");
            user.setHealth(user.getHealth() + potion.getCureHP());
            System.out.println("Your Health level is now " + user.getHealth() + ".\n");
        }
        else if(choice2.equalsIgnoreCase("N")){
            System.out.println("Then we'll just keep going with your nightmare. " + "Your Health level is " + user.getHealth() + "\n");
        }
        skull.recover();

        battle();
    }

    public void deadPlayer(){

        System.out.println("You are dead... Thank you for playing.");
        System.exit(0);
    }
}