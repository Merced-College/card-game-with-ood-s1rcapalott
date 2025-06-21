package cardGame;


import java.util.Scanner;

public class GameSelector {

    public static void main(String[] args) {
        int choice = getGameChoice();

        switch (choice) {
            case 1:
                System.out.println("You selected Two of a Kind.");
                CardGame.playPairCheckMode();
                break;
            case 2:
                System.out.println("You selected Blackjack.");
                CardGame.playBlackjack();
                break;
            case 3:
                System.out.println("You selected War.");
                System.out.println("War game is not implemented yet.");
                // Placeholder for War game implementation
                // CardGame.playWar();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static int getGameChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game!");
        System.out.println("Please select a game:");
        System.out.println("1. Two of a Kind");
        System.out.println("2. Blackjack");
        System.out.println("3. War");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.close();
        return choice;
    }
}

