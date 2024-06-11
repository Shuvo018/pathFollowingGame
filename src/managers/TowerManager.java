package managers;

import enemies.Enemy;
import helps.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helps.Constants.Towers.*;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImg;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing){
        this.playing = playing;

        loadTowerImg();
    }

    private void loadTowerImg() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImg = new BufferedImage[3];
        for(int i=0; i<3; i++)
            towerImg[i] = atlas.getSubimage((i+4)*32, 32, 32, 32);
    }
    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }
    public void update(){
        for (Tower t: towers){
            t.update();
            attackEnemyIfClose(t);
        }
    }

    private void attackEnemyIfClose(Tower t) {
            for (Enemy e: playing.getEnemyManager().getEnemies()){
                if(e.isAlive()){
                    if(isEnemyInRange(t, e)){
                        if(t.isCooldownOver()){
                            playing.shootEnemy(t, e);
                            t.resetCooldown();
                        }
                    }else{

                    }
                }

            }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = helps.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }

    public void draw(Graphics g){
        for (Tower t: towers){
            g.drawImage(towerImg[t.getTowerType()], t.getX(), t.getY(), null);
        }
//        g.drawImage(towerImg[ARCHER], tower.getX(), tower.getY(), null);
    }
    public Tower getTowerAt(int x, int y) {
        for (Tower t: towers){
            if(t.getX() == x && t.getY() == y)
                return t;
        }
        return null;
    }
    public BufferedImage[] getTowerImg(){
        return towerImg;
    }


}
