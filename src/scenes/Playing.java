package scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helps.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;
import static helps.Constants.Tiles.GRASS_TILE;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private ActionBar actionBar;
	private int mouseX, mouseY;
	private EnemyManager enemyManager;
	private PathPoint start, end;
	private TowerManager towerManager;
	private ProjectileManager projectileManager;
	private Tower selectedTower;


	public Playing(Game game) {
		super(game);

		loadDefaultLevel();

		actionBar = new ActionBar(0, 640, 640, 160, this);
		enemyManager = new EnemyManager(this, start, end);
		towerManager = new TowerManager(this);

		projectileManager = new ProjectileManager(this);

	}

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");
		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
//		points.add(1);
		start = points.get(0);
		end = points.get(1);
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	public void update() {
		updateTick();
		enemyManager.update();
		towerManager.update();
		projectileManager.update();
	}
	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}
	@Override
	public void render(Graphics g) {

		drawLevel(g);
		actionBar.draw(g);
		enemyManager.draw(g);
		towerManager.draw(g);
		projectileManager.draw(g);

		drawSelectedTower(g);
		drowHightLight(g);

	}

	private void drowHightLight(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(mouseX, mouseY, 32,32);
	}

	private void drawSelectedTower(Graphics g) {
		if(selectedTower != null)
			g.drawImage(towerManager.getTowerImg()[selectedTower.getTowerType()], mouseX, mouseY, null);
	}

	private void drawLevel(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if (isAnimation(id)) {
					g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
				} else
					g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}

	public int getTileType(int x, int y) {
		int xCord = x / 32;
		int yCord = y / 32;

		if (xCord < 0 || xCord > 19)
			return 0;
		if (yCord < 0 || yCord > 19)
			return 0;

		int id = lvl[y / 32][x / 32];
		return game.getTileManager().getTile(id).getTileType();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640)
			actionBar.mouseClicked(x, y);
		else {
			if(selectedTower != null){
				if(isTileGrass(mouseX, mouseY)){
					if(getTowerAt(mouseX, mouseY) == null){
						towerManager.addTower(selectedTower, mouseX, mouseY);
						selectedTower = null;
					}
				}
			}else{
				Tower t = getTowerAt(mouseX, mouseY);
				actionBar.displayTower(t);
			}
		}
	}

	private Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x, y);
	}

	private boolean isTileGrass(int x, int y) {
		int id = lvl[y/32][x/32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			selectedTower = null;
		}
	}
	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			actionBar.mouseMoved(x, y);
		else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			actionBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		actionBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {

	}
	public TowerManager getTowerManager(){
		return towerManager;
	}
	public EnemyManager getEnemyManager(){
		return enemyManager;
	}


	public void shootEnemy(Tower t, Enemy e) {
		projectileManager.newProjectile(t, e);
	}
}