package scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;

public class Settings extends GameScene implements SceneMethods{

	private MyButton bMenu;
	public Settings(Game game) {
		super(game);
		
		initButton();
	}

	private void initButton() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 640, 640);
		
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
