package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;

import resources.Card.Suit;


public class BlackJack {
	ArrayList<Stack <Card>> columns;
	Stack<Card> deck;
	Queue<Card> playerHand;
	Queue<Card> dealerHand;
	int gameRunning;

		
	

	// pre-condition: player has already been dealt cards
	// post-condition: Deals the player another card, increasing their sum by x.
	public Card hit() {
		Card hitCard = deck.pop();
		playerHand.add(hitCard);
		int playerSum = checkSumPlayer(); 
		if (playerSum > 21) { 
			gameRunning = 3; 
		}
		return hitCard;
	}

	// pre-condition: cheskSumDealer is initialized
	// post-condition: if = or > 17, player stands.
	public int stand() {
		if (checkSumDealer()>=17) {
			return getStage();
		}
		else if (checkSumDealer()<17 && checkSumPlayer()>checkSumDealer()) {
		while (checkSumDealer()<17 && checkSumPlayer()>checkSumDealer()) {
			dealerHit();
		}
		
		}
		dealerHand.peek().show();
		return getStage();
	}

	// pre-condition: player has hit stand
	// post-condition: Deals the dealer another card, increasing their sum by x.
	public int dealerHit() {
		Card hitCard = deck.pop();
		dealerHand.add(hitCard);
		return checkSumDealer();
	}

	// pre-condition: none
	// post-condition: Checks if the sum of the players cards has exceeded 21 or is equal to 21. If either one of these occur, the round ends and endGame() is called.
	public int checkSumPlayer() {
		int sum = 0;
		int aceCount = 0;
		for (Card card : playerHand) {
			if (card.getValue() == 11) { // Ace has a value of 11
				aceCount++;
				sum += 11; 
			} else {
				sum += card.getValue();
			}
		}
		// Adjust for aces if the sum is over 21
		while (sum > 21 && aceCount > 0) {
			sum -= 10; // Treat one ace as 1
			aceCount--;
		}
		return sum;
	}



	// pre-condition: dealDealer() has been called
	// post-condition: Checks the the sum of the dealer’s cards  
	public int checkSumDealer() {
		int dealerSum = 0;
		int numAces = 0;
		for (Card card : dealerHand) {
			dealerSum += card.getValue();
			if (card.getValue() == 1) {
				numAces++;
			}
		}
		while (dealerSum <= 11 && numAces > 0) {
			dealerSum += 10;
			numAces--;
		}
		return dealerSum;
	}

	// pre-condition: none
	// post-condition: // Resets variables and calls the deal functions for the player to play another game.
	public void setupGame(){
		initializeDeck();
		shuffleDeck();
		dealDealer();
		dealPlayer();
	}

	// pre-condition: deck is shuffled and no cards are in the players hand
	// post-condition: // Deals the cards to the player
	public void dealPlayer(){
		playerHand= new LinkedList<Card>();
		playerHand.add(deck.pop());
		playerHand.add(deck.pop());
		
	}

	// pre-condition: deck is shuffled and no cards are in dealers hand
	// post-condition: Deal the cards to the dealer
	public void dealDealer(){
		dealerHand = new LinkedList<Card>();
		dealerHand.add(deck.pop());
		dealerHand.add(deck.pop());
		dealerHand.peek().hide();
	}

	//pre-conditon: deck has been created 
	//post-condtion: deck is given all 52 diffrent cards 
	public void initializeDeck() {
		deck = new Stack<Card>();
		for(int i = 1; i<=13;i++) {
			deck.push(new Card(i,Suit.Hearts));
		}
		for(int i = 1; i<=13;i++) {
			deck.push(new Card(i,Suit.Spades));
		}
		for(int i = 1; i<=13;i++) {
			deck.push(new Card(i,Suit.Diamonds));
		}
		for(int i = 1; i<=13;i++) {
			deck.push(new Card(i,Suit.Clubs));
		}
	}
		
	//pre-condtion: none
	//post-condtion: returns if the game ended
	public int getStage() {
		int gameRunning = 0;
		if ((checkSumPlayer()==21 && checkSumDealer() != 21)|| (checkSumDealer() > 21 && checkSumPlayer()<21) || checkSumPlayer()>checkSumDealer()) {
					
			gameRunning = 2;
		}
		else if (checkSumPlayer()>21|| checkSumPlayer()<checkSumDealer()) {
			gameRunning = 3; 
		}
		else {
			//still running
			gameRunning =1;
		}
		return gameRunning;
	}
		
	//pre-condtion: deck has been initiaized
	//post-condtion: deck has been shuffled
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

}