package cardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<>();
	private static ArrayList<Card> playerCards = new ArrayList<>();
	private static ArrayList<Card> dealerCards = new ArrayList<>();

	public static void main(String[] args) {
		playPairCheckMode();
	}

	public static void shuffle() {
		java.util.Collections.shuffle(deckOfCards);
	}

	public static boolean checkFor2Kind() {
		int count = 0;
		for (int i = 0; i < playerCards.size(); i++) {
			for (int j = i + 1; j < playerCards.size(); j++) {
				if (playerCards.get(i).getCardValue() == playerCards.get(j).getCardValue()) {
					count++;
				}
			}
		}
		return count >= 1;
	}

	public static void playPairCheckMode() {
    	Scanner scanner = new Scanner(System.in);
		boolean playing = true;
		
		while(playing){
			loadDeck();
    		shuffle();
    		playerCards.clear();

    // Deal 4 cards
    		for (int i = 0; i < 4; i++) {
        		playerCards.add(deckOfCards.remove(0));
    		}

    		System.out.println("\nPlayer's cards:");
    		for (Card c : playerCards) {
        		System.out.println(c);
    		}
			boolean hasPair = checkFor2Kind();
			if(hasPair){
				System.out.println("You have found a pair! You win!");
			}else{
				System.out.println("No pairs found. You lose.");
				System.out.println("Would you like to play again? (yes/no)");
				String response = scanner.nextLine().trim().toLowerCase();
				if (!(response.equals("yes") || response.equals("y"))) { 
				// Exit the game if the user does not want to play again
					playing = false;
					System.out.println("Thanks for playing!");
				}
			}
		}
		scanner.close();
	}

	public static void loadDeck() { // Load the deck of cards from a file and reset the deck
    	deckOfCards.clear();
    	try (Scanner input = new Scanner(new File("cards.txt"))) {
     		while (input.hasNext()) {
            	String[] fields = input.nextLine().split(",");
            	Card newCard = new Card(fields[0], fields[1].trim(),
                    Integer.parseInt(fields[2].trim()), fields[3]);
            	deckOfCards.add(newCard);
        	}
    } 	catch (FileNotFoundException e) {
        	System.out.println("Could not load deck: cards.txt not found.");
    	}
	}

	 public static void playBlackjack() {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            loadDeck(); // this clears the deck in case the user is starting after another game
            shuffle();
            playerCards.clear(); // clears the player's cards so they can start fresh
            dealerCards.clear();

            System.out.println("Welcome to Blackjack!");

            // Initial deal to bothe player and dealer
            singleDeal(playerCards);
            singleDeal(playerCards);
            singleDeal(dealerCards);
            singleDeal(dealerCards);

            boolean playerTurn = true;

            while (playerTurn) {
                System.out.println("\nYour cards:");
                for (Card c : playerCards) {
                    System.out.println(c);
                }

                int playerScore = scoreHand(playerCards);
                System.out.println("Your score: " + playerScore);

                if (checkBust(playerCards)) {
                    System.out.println("You busted! Dealer wins.");
                    playerTurn = false;
                    break;
                }

                System.out.print("Hit or stand? (h/s): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("h")) {
                    singleDeal(playerCards);
                } else if (choice.equals("s")) {
                    playerTurn = false;
                } else {
                    System.out.println("Invalid input. Try again.");
                }
            }

            // Dealer's turn to play
            if (!checkBust(playerCards)) {
                System.out.println("\nDealer's turn:");
                while (scoreHand(dealerCards) < 17) {
                    singleDeal(dealerCards);
                }

                System.out.println("Dealer's cards:");
                for (Card c : dealerCards) {
                    System.out.println(c);
                }
                int dealerScore = scoreHand(dealerCards);
                System.out.println("Dealer score: " + dealerScore);

                if (checkBust(dealerCards)) {
                    System.out.println("Dealer busted! You win!");
                } else {
                    int playerScore = scoreHand(playerCards);

                    if (playerScore > dealerScore) {
                        System.out.println("You win!");
                    } else if (playerScore < dealerScore) {
                        System.out.println("Dealer wins!");
                    } else {
                        System.out.println("It's a tie!");
                    }
                }
            }

            // Ask to play again
            System.out.print("\nPlay again? (yes/no): ");
            String again = scanner.nextLine().trim().toLowerCase();
            if (!again.equals("yes") && !again.equals("y")) {
                playing = false;
                System.out.println("Thanks for playing!");
            }
        }
        scanner.close();
    }

    // helper methods for Blackjack

    public static void singleDeal(ArrayList<Card> hand) {
        hand.add(deckOfCards.remove(0));
    }

    public static int scoreHand(ArrayList<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            score += card.getCardValue();
        }
        return score;
    }

    public static boolean checkBust(ArrayList<Card> hand) {
        return scoreHand(hand) > 21;
    }
}
		

