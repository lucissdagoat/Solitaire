package resources;
import javax.imageio.ImageIO;
import javax.swing.*;

import resources.Card.Suit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;



public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	BlackJack game;
	private  JPanel gameArea = new JPanel();
	private  JPanel north = new JPanel();
	private  JPanel south = new JPanel();
	private  JPanel east = new JPanel();
	private JPanel panel;

	public GUI(BlackJack game) {

		this.game = game;

		game.setupGame();

		// setWindow();
		// Create and set up the window.
		setTitle("BlackJack");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			System.out.println(getClass().toString());
			Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
			setContentPane(new ImagePanel(blackImg));
		} catch (IOException e) {
			e.printStackTrace();
		}

		getContentPane().setLayout(new BorderLayout());

		gameArea.setOpaque(false);
		// gameArea.add(card);

		// Setting up the diffrent sections
		gameArea.setLayout(new BorderLayout());

		JPanel north = new JPanel();
		north.setOpaque(false);
		north.setPreferredSize(new Dimension(400, 200));
		gameArea.add(north, BorderLayout.NORTH);

		east.setOpaque(false);
		east.setPreferredSize(new Dimension(200, 400));
		gameArea.add(east, BorderLayout.EAST);

		south.setOpaque(false);
		south.setPreferredSize(new Dimension(400, 200));
		gameArea.add(south, BorderLayout.SOUTH);

		// this.revalidate();
		this.getContentPane().add(gameArea);

		// shows the players cards
		showPlayerCards();

		// Card card1 = game.playerHand.remove();
		// Card card2 = game.playerHand.remove();
		// card1.setPreferredSize(new Dimension(100,140));
		// card2.setPreferredSize(new Dimension(100,140));
		// south.add(card1);
		// south.add(card2);

		// Shows the dealers hand
		Card dcard1 = game.dealerHand.remove();
		Card dcard2 = game.dealerHand.remove();
		game.dealerHand.add(dcard1);
		game.dealerHand.add(dcard2);
		dcard1.setPreferredSize(new Dimension(100, 140));
		dcard2.setPreferredSize(new Dimension(100, 140));
		north.add(dcard1);
		north.add(dcard2);

		// shows the deck
		Card fakeDeck = new Card(1, Suit.Clubs);
		fakeDeck.hide();
		fakeDeck.setPreferredSize(new Dimension(170, 270));
		east.add(fakeDeck);

		// Makes the diffrent buttons
		JButton hit = new JButton("Hit");
		hit.setBounds(130, 100, 200, 80); // x axis, y axis, width, height
		south.add(hit); // adding button in JFrame
		hit.setBackground(Color.GREEN);
		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card newCard = game.hit();
				newCard.setPreferredSize(new Dimension(100, 140));
				south.add(newCard);
				revalidate();
				int sum = game.checkSumPlayer();
				System.out.println(sum);
				if (sum > 21) {
					ended(3);
				} else if (sum == 21) {
					ended(2);
				}
			}
		});

		JButton stand = new JButton("Stand");
		stand.setBounds(130, 100, 200, 80);// x axis, y axis, width, height
		south.add(stand);// adding button in JFrame
		stand.setBackground(Color.GRAY);
		stand.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Got to the button");
				int result = game.stand();
				System.out.println(result);
				ended(result);

			}
		});

		/*******
		 * This is just a test to make sure images are being read correctly on your
		 * machine. Please replace once you have confirmed that the card shows up
		 * properly. The code below should allow you to play the solitare game once it's
		 * fully created.
		 */
		System.out.println("here?");
		this.setVisible(true);
	}

	// pre-condition: pressed play again after a round is played
	// post-condition: window is reset up after hitting play again
	public void reset() {
		
		game.setupGame();
		showPlayerCards();
		
		Card dcard1 = game.dealerHand.remove();
		Card dcard2 = game.dealerHand.remove();
		game.dealerHand.add(dcard1);
		game.dealerHand.add(dcard2);
		dcard1.setPreferredSize(new Dimension(100, 140));
		dcard2.setPreferredSize(new Dimension(100, 140));
		north.add(dcard1);
		north.add(dcard2);

		

		Card fakeDeck = new Card(1, Suit.Clubs);
		fakeDeck.hide();
		fakeDeck.setPreferredSize(new Dimension(170, 270));
		east.add(fakeDeck);

		JButton hit = new JButton("Hit");
		hit.setBounds(130, 100, 200, 80); // x axis, y axis, width, height
		south.add(hit); // adding button in JFrame
		hit.setBackground(Color.GREEN);
		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card newCard = game.hit();
				newCard.setPreferredSize(new Dimension(100, 140));
				south.add(newCard);
				revalidate();
				int sum = game.checkSumPlayer();
				System.out.println(sum);
				if (sum > 21) {
					ended(3);
				} else if (sum == 21) {
					ended(2);
				}
			}
		});

		JButton stand = new JButton("Stand");
		stand.setBounds(130, 100, 200, 80);// x axis, y axis, width, height
		south.add(stand);// adding button in JFrame
		stand.setBackground(Color.GRAY);
		stand.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Got to the button");
				int result = game.stand();
				System.out.println(result);
				ended(result);
			}
		});

		revalidate();
		repaint();

	}

	

	// pre-condition: a move has been made
	// post-condition: tells you if you won lost or still playing
	public void ended(int result) {

		if (result == 2 || result == 3) {
			System.out.println("We have won or lost");
			north.removeAll();
			south.removeAll();
			east.removeAll();
			panel = new JPanel();
			JLabel label = new JLabel();

			if (result == 2) {
				label.setText("You Won!");
			} else if (result == 3) {
				label.setText("You Lost!");
			}

			panel.add(label);
			gameArea.revalidate();

			JButton pa = new JButton("Play Again");
			pa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BlackJack game = new BlackJack();
					gameArea.remove(panel);
					System.out.println("update() runs");
					reset();
					revalidate();
					repaint();
								}
			});

			panel.add(pa);
			panel.setPreferredSize(new Dimension(200, 200));
			gameArea.add(panel);
			// this.revalidate();
			// this.repaint();
		} else if (result == 1) {
			System.out.println("It returned 1");
		}
	}

	// pre-condition: cards is not empty
	// post-condition: new JLayeredPane is returned
	public JLayeredPane drawPile(Stack<Card> stackIn) {
		JLayeredPane layered = new JLayeredPane();
		Object cards[];
		cards = stackIn.toArray();
		int offsety = 50;
		int offsetx = 25;
		for (int i = cards.length - 1; i >= 0; i--) {
			Card c = (Card) cards[i];
			c.setBounds(offsetx, offsety, 100, 100);
			offsetx += 5;
			offsety += 20;
			layered.add(c);
		}
		return layered;
	}

	// pre-condition: game.playerHand is a list of Card objects
	// post-condition: all game.playerHand cards are removed and added to south
	public void showPlayerCards() {
		System.out.println(game.playerHand.size());
		for (int i = 0; i <= game.playerHand.size(); i++) {
			Card card1 = game.playerHand.remove();
			game.playerHand.add(card1);
			card1.setPreferredSize(new Dimension(100, 140));
			south.add(card1);
		}
	}
	

	// pre-condition: game.playerHand is not empty
	// post-condition: top card is revealed
	public void revealDealerCards() {
		// for (int i =0; i<=game.dealerHand.size();i++){
		// game.dealerHand.remove().show();
		game.dealerHand.peek().show();
		game.checkSumPlayer();
	}

	// Mouse movement classes

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	// Calls the respective function that the mouse clicked on.
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
