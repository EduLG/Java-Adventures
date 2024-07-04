import java.rmi.server.Skeleton;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome to your new adventure.\n" +
            "You'll be facing an infinite succesion of enemies.\n" +
            "Lets see how many of them you can handle.\n" +
            "Press enter to continue...");

        sc.nextLine();
        Player user = new Player("User", 50);
        Enemy skull = new Enemy("Skull", 100, 100);
        while (true) {
            System.out.println(skull.getName() + " has appeared:\n" +
                    "Enemy Health: " + skull.getHealth() + "\n");

            while (skull.getHealth() > 0) {
                System.out.println("What would you like to do\n" +
                        "1- Attack\n" +
                        "2- Flee\n");

                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    skull.takeDamage(user.getAttack());
                    System.out.println("You attack the " + skull.getName() + ". " + skull.getName() + "'s health is now " + skull.getHealth() + ".");
                } else if (choice.equals("2")) {
                    System.out.println("You flee like the piece of sh*t you are. No one likes you.");
                    return;

                } else {
                    System.out.println("Please, choose between the options given.");
                }
            }
            System.out.println("You defeated " + skull.getName() + "! Congratulations!");
            skull.recover();
        }
    }
}
