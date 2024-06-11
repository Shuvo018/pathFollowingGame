package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

public class GameScreen extends JPanel{

	private Dimension size;
	private Game game;

	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;

	public GameScreen(Game game) {
		this.game = game;

		setPanelSize();

		System.out.println();
	}

	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener(game);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus();
	}

	private void setPanelSize() {

		size = new Dimension(640, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.getRender().render(g);


//		BufferedImage subImg = img.getSubimage(32*0, 32*1, 32, 32);
//		g.drawImage(img.getSubimage(32*0, 32*0, 32, 32), 0, 0, null);


//		for(int y=0; y<20; y++) {
//			for(int x=0; x<20; x++) {
//				g.setColor(getRndColor());
//				g.fillRect( x*32, y*32, 32, 32);
//			}
//		}

//		int i=0;
//		for(int y=0; y<3; y++) {
//			for(int x=0; x<10; x++) {
//				g.drawImage(sprites.get(i++), x*32, 32*y, null);
//			}
//		}



	}


}
