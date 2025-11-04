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

	Solitaire game;
   public GUI(Solitaire game){

	this.game= game;

		
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(900,700);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	   try {
		System.out.println(getClass().toString());
		Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
		setContentPane(new ImagePanel(blackImg));

       }catch(IOException e) {
    	   e.printStackTrace();
       }
	   getContentPane().setLayout(new BorderLayout());
	//    Card card = new Card(2, Card.Suit.Diamonds);
	//    System.out.println(card);
	// 	card.setPreferredSize(new Dimension(200,200));

      JPanel gameArea = new JPanel();
	   gameArea.setOpaque(false);
	//    gameArea.add(card);
	  gameArea.setLayout(new BorderLayout()); 
	   gameArea.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.PINK));

    JPanel north = new JPanel(); 
	north.setOpaque(false);
	north.setPreferredSize(new Dimension(400,200)); 
	north.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
    gameArea.add(north,BorderLayout.NORTH);
	  

	  JPanel east = new JPanel();
	  east.setOpaque(false);
	  east.setPreferredSize(new Dimension(200,400));
	east.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
	gameArea.add(east,BorderLayout.EAST);

	 JPanel south = new JPanel();
	 south.setOpaque(false);
	 south.setPreferredSize(new Dimension(400,200));
	   south.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.YELLOW));
      gameArea.add(south,BorderLayout.SOUTH);
	   
		// this.revalidate();
		this.getContentPane().add(gameArea);
  
		Stack<Card> stackIn = new Stack<Card>();
		stackIn.push(new Card(1, Suit.Hearts));
		stackIn.push(new Card(2, Suit.Spades));
        stackIn.push(new Card(3, Suit.Diamonds));
		stackIn.push(new Card(4, Suit.Clubs));
		gameArea.add(drawPile(stackIn));
       // this supplies the background

       
       /*******
        * This is just a test to make sure images are being read correctly on your machine. Please replace
        * once you have confirmed that the card shows up properly. The code below should allow you to play the solitare
        * game once it's fully created.
        */
		System.out.println("here?");
        this.setVisible(true);
		
    }
    
	public JLayeredPane drawPile(Stack<Card> stackIn) {
		JLayeredPane layered = new JLayeredPane();
		Object cards[];
		cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	   int offsety = 50;
	   int offsetx = 25;
		for (int i = cards.length-1;i>=0;i--) {
			Card c = (Card) cards[i];
			c.setBounds(offsetx, offsety, 100, 100);
			offsetx +=5;
			offsety +=20;
			layered.add(c);
		}
		
		return layered;
	}
	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
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
